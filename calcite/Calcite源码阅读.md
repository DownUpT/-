## 配置
1. Lex枚举：命名的内置词法策略。词汇策略描述了标识符是如何被引用的，在读取它们时是转换为大写还是小写，以及它们是否区分大小写匹配
2. NullCollation枚举：在没有指定NULLS FIRST或NULLS LAST，如何对NULL值进行排序的策略。
3. CharLiteralStyle枚举: 文字字符的样式，怎么标识转义。