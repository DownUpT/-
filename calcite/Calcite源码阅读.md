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
  <T> CalciteSignature<T> prepareSql(Context context, Query<T> query, Type elementType, long maxRowCount);
```

```java
    if (needsValidation) {
      query = validator().validate(query);
    }
```
RelOptCluster：
Apache Calcite中的 RelOptCluster 类是用于表示关系表达式的集群，它在查询优化过程中扮演重要角色。它作为一个容器，用于存储和管理各种关系表达式，并提供执行优化和转换的上下文。 
 
 RelOptCluster 类的主要职责包括： 
1. 维护集群中的关系表达式及其相互依赖关系。 
2. 提供有关集群的元数据和统计信息。 
3. 管理优化规则，并将其应用于集群中的关系表达式。 
4. 跟踪关系表达式的成本和基数估计。 
5. 基于优化规则，进行关系表达式的转换和重写。 
 
总而言之，在Apache Calcite中， RelOptCluster 类通过管理关系表达式、应用优化规则以及提供优化上下文，对查询优化过程起着关键作用。

RelOptPlanner
执行优化器

RexBuilder
`row expressions`工厂，生成RexNode

SqlToRelConverter
将一个SQLnode解析树转换为关系代数表达式（RelNode）

optimize优化查询逻辑代码
```java
  /**
   * Optimizes a query plan.
   *
   * @param root Root of relational expression tree
   * @param materializations Tables known to be populated with a given query
   * @param lattices Lattices
   * @return an equivalent optimized relational expression
   */
 protected RelRoot optimize(RelRoot root,
      final List<Materialization> materializations,
      final List<CalciteSchema.LatticeEntry> lattices) {
    
    // 获取执行优化器
    final RelOptPlanner planner = root.rel.getCluster().getPlanner();

    final DataContext dataContext = context.getDataContext();
    planner.setExecutor(new RexExecutorImpl(dataContext));

    final List<RelOptMaterialization> materializationList =
        new ArrayList<>(materializations.size());
    for (Materialization materialization : materializations) {
      List<String> qualifiedTableName = materialization.materializedTable.path();
      materializationList.add(
          new RelOptMaterialization(
              castNonNull(materialization.tableRel),
              castNonNull(materialization.queryRel),
              materialization.starRelOptTable,
              qualifiedTableName));
    }
    // 获取RootTrait集合
    final List<RelOptLattice> latticeList = new ArrayList<>(lattices.size());
    for (CalciteSchema.LatticeEntry lattice : lattices) {
      final CalciteSchema.TableEntry starTable = lattice.getStarTable();
      final JavaTypeFactory typeFactory = context.getTypeFactory();
      final RelOptTableImpl starRelOptTable =
          RelOptTableImpl.create(catalogReader,
              starTable.getTable().getRowType(typeFactory), starTable, null);
      latticeList.add(
          new RelOptLattice(lattice.getLattice(), starRelOptTable));
    }

    final RelTraitSet desiredTraits = getDesiredRootTraitSet(root);

    final Program program = getProgram();
    final RelNode rootRel4 =
        program.run(planner, root.rel, desiredTraits, materializationList,
            latticeList);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Plan after physical tweaks:\n{}",
          RelOptUtil.toString(rootRel4, SqlExplainLevel.ALL_ATTRIBUTES));
    }

    return root.withRel(rootRel4);
  }
```