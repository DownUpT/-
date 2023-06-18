package com.dq.demo.csv;

import org.apache.calcite.DataContext;
import org.apache.calcite.adapter.file.CsvEnumerator;
import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.linq4j.AbstractEnumerable;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.QueryProvider;
import org.apache.calcite.linq4j.Queryable;
import org.apache.calcite.linq4j.tree.Expression;
import org.apache.calcite.plan.RelOptTable;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelProtoDataType;
import org.apache.calcite.schema.QueryableTable;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.Schemas;
import org.apache.calcite.schema.TranslatableTable;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.util.ImmutableIntList;
import org.apache.calcite.util.Source;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CsvTranslatableTable extends AbstractTable implements QueryableTable, TranslatableTable {

    private final Source source;
    private final RelProtoDataType protoRowType;
    private RelDataType rowType;
    private List<RelDataType> fieldTypes;

    public CsvTranslatableTable(Source source, RelProtoDataType protoRowType) {
        this.source = source;
        this.protoRowType = protoRowType;
    }

    @SuppressWarnings("unused") // called from generated code
    public Enumerable<Object> project(final DataContext root,
                                      final int[] fields) {
        final AtomicBoolean cancelFlag = DataContext.Variable.CANCEL_FLAG.get(root);
        return new AbstractEnumerable<Object>() {
            @Override
            public Enumerator<Object> enumerator() {
                JavaTypeFactory typeFactory = root.getTypeFactory();
                return new CsvEnumerator<>(
                    source,
                    cancelFlag,
                    getFieldTypes(typeFactory),
                    ImmutableIntList.of(fields));
            }
        };
    }

    @Override
    public <T> Queryable<T> asQueryable(QueryProvider queryProvider, SchemaPlus schema, String tableName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Type getElementType() {
        return Object[].class;
    }

    @Override
    public Expression getExpression(SchemaPlus schema, String tableName, Class clazz) {
        return Schemas.tableExpression(schema, getElementType(), tableName, clazz);
    }

    @Override
    public RelNode toRel(RelOptTable.ToRelContext context, RelOptTable relOptTable) {
        int fieldCount = relOptTable.getRowType().getFieldCount();
        int[] fields = CsvEnumerator.identityList(fieldCount);
        return new CsvTableScan(context.getCluster(), relOptTable, this, fields);
    }

    @Override
    public RelDataType getRowType(RelDataTypeFactory typeFactory) {
        if (protoRowType != null) {
            return protoRowType.apply(typeFactory);
        }
        if (rowType == null) {
            rowType = CsvEnumerator.deduceRowType((JavaTypeFactory) typeFactory, source, null, false);
        }
        return rowType;
    }

    public List<RelDataType> getFieldTypes(RelDataTypeFactory typeFactory) {
        if (fieldTypes == null) {
            fieldTypes = new ArrayList<>();
            CsvEnumerator.deduceRowType((JavaTypeFactory) typeFactory, source,
                fieldTypes, false);
        }
        return fieldTypes;
    }

}
