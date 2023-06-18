package com.dq.demo.csv;

import org.apache.calcite.DataContext;
import org.apache.calcite.adapter.file.CsvEnumerator;
import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.linq4j.AbstractEnumerable;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelProtoDataType;
import org.apache.calcite.schema.ScannableTable;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.util.ImmutableIntList;
import org.apache.calcite.util.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CsvScannableTable extends AbstractTable implements ScannableTable {

    private final Source source;
    private final RelProtoDataType protoRowType;
    private RelDataType rowType;
    private List<RelDataType> fieldTypes;

    /**
     * Creates a CsvTable.
     */
    public CsvScannableTable(Source source, RelProtoDataType protoRowType) {
        this.source = source;
        this.protoRowType = protoRowType;
    }

    @Override
    public Enumerable<Object[]> scan(DataContext root) {
        JavaTypeFactory typeFactory = root.getTypeFactory();
        List<RelDataType> fieldTypes = getFieldTypes(typeFactory);
        List<Integer> fields = ImmutableIntList.identity(fieldTypes.size());
        AtomicBoolean cancelFlag = DataContext.Variable.CANCEL_FLAG.get(root);

        return new AbstractEnumerable<Object[]>() {
            @Override
            public Enumerator<Object[]> enumerator() {
                return new CsvEnumerator<>(source, cancelFlag,
                        false, null, CsvEnumerator.arrayConverter(fieldTypes, fields, false));
            }
        };
    }

    @Override
    public RelDataType getRowType(RelDataTypeFactory typeFactory) {
        if (protoRowType != null) {
            return protoRowType.apply(typeFactory);
        }

        if (rowType == null) {
            // 打开csv文件，读csv
            // 如果数据库，这里可能就需要做一下查询表结构和字段，并且定义数据库字段类型与calcite支持的语法类型
            rowType = CsvEnumerator.deduceRowType((JavaTypeFactory) typeFactory, source, fieldTypes, isStream());
        }

        return rowType;
    }

    public List<RelDataType> getFieldTypes(RelDataTypeFactory typeFactory) {
        if (fieldTypes == null) {
            fieldTypes = new ArrayList<>();
            CsvEnumerator.deduceRowType((JavaTypeFactory) typeFactory, source,
                    fieldTypes, isStream());
        }
        return fieldTypes;
    }

    private boolean isStream() {
        return false;
    }
}
