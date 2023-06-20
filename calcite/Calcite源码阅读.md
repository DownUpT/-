## 配置
1. Lex枚举：命名的内置词法策略。词汇策略描述了标识符是如何被引用的，在读取它们时是转换为大写还是小写，以及它们是否区分大小写匹配
2. NullCollation枚举：在没有指定NULLS FIRST或NULLS LAST，如何对NULL值进行排序的策略。
3. CharLiteralStyle枚举: 文字字符的样式，怎么标识转义。


## 查询执行流程
查询流程归纳可以分为以下几个步骤：
1. 将SQL转化为SqlNode对象
2. 把查询请求转化为逻辑计划
3. 优化逻辑计划，生成物理计划

上述步骤中有哪些地方需要明白：
- SQL是怎么转为SqlNode对象的，这个过程中的需要哪些参数信息？
- 查询请求怎么转化为逻辑计划的？
- 用户自定义的处理流程时什么时候加进来的
- 优化逻辑计划的过程时怎么样的，对应的哪一块的代码，是如何执行的？
- 物理计划是如何生产对应的代码？
- 最终的计算是哪个模块做的？
- 怎么做到部分扫描数据而不是整表扫描？
- 视图的执行流程是什么样子的？

### 1. SQL是怎么转为SqlNode对象的？
在Calcite中，一条查询语句的执行流程是这样的：
1. 初始化Driver驱动
   - 这里的drive驱动可以是用户自定义的jdbc连接，
   - 也可以用calcite自己的jdbc驱动：`jdbc:calcite:`
   - 或者是用calcite avatica提供的驱动: `jdbc:avatica:remote:`,avatica主要是用于提供远程jdbc调用的服务
   - 初始化好驱动之后，获取到对应的connection信息
   - 通过参数获取到对应连接的元数据信息，库（Schema）、表（Table）、字段等信息。
   - 传入SQL进行校验、解析、优化、查询；
2. 获取Connection，statement
3. 查询prepare
4. 获取结果

SQL转为SqlNode的过程是在prepare阶段完成的，这里有个问题 什么时候调用RemoteMeta，什么时候调用CalciteMetaImpl

