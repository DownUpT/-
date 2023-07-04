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

### 2.1 SQL是怎么转为SqlNode对象的？
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

## Avatica源码

### 3.1 Avatica架构
Avatica是一个数据服务管理框架，Avatica 处于 Calcite 和调用程序中间，是 Calcite 最贴近用户的一个部分：
- 接收客户端的 SQL 请求；
- 校验用户的配置信息（例如校验用户名和密码）；
- 转发给 calcite-core 模块执行；
- 封装结果请求并返回。

## Calcite源码

### Calcite解析层
将SQL语句转换为SqlNode。
SqlNode的子类说明：
sqlNode有三个直接子类，分别是SqlCall，SqlLiteral，SqlIdentifier，其中：
- SqlCall：SqlCall是对运算符的调用。（SQL解析树中的每个非叶节点都是某种类型的SqlCall）。
- SqlLiteral: SqlLiteral是一个常量，是不可变的。
- SqlIdentifier：Sql中的表示符号，比如'*'。

![](../img/calcite/SqlNode.png)

Sql解析为SqlNode是由Calcite的javacc完成解析的，此处不做javacc语法研究，直接快进校验层。

### Calcite校验层
经过SQL解析，我们的SQL已经被解析为SqlNode；校验层做的事情有两点：
1. 判断sql是否正确
2. 将sqlNode转为relNode

校验执行的实际在Sql的Parpare阶段，在calcite中，由CalcitePrepare中的prepareSql方法负责，其实现为：
```java

```

```java
    if (needsValidation) {
      query = validator().validate(query);
    }
```


