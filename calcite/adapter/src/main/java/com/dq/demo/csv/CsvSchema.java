package com.dq.demo.csv;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import org.apache.calcite.linq4j.tree.Expression;
import org.apache.calcite.rel.type.RelProtoDataType;
import org.apache.calcite.schema.Function;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.SchemaVersion;
import org.apache.calcite.schema.Schemas;
import org.apache.calcite.schema.Table;
import org.apache.calcite.util.Source;
import org.apache.calcite.util.Sources;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class CsvSchema implements Schema {
    /**
     * csv的文件路径
     */
    private final File directoryFile;
    private Map<String, Table> tableMap;

    public CsvSchema(File directoryFile) {
        this.directoryFile = directoryFile;
    }

    private static String trim(String s, String suffix) {
        String trimmed = trimOrNull(s, suffix);
        return trimmed != null ? trimmed : s;
    }

    private static String trimOrNull(String s, String suffix) {
        return s.endsWith(suffix)
                ? s.substring(0, s.length() - suffix.length())
                : null;
    }

    @Override
    public @Nullable Table getTable(String name) {
        return getTableMap().get(name);
    }

    @Override
    public Set<String> getTableNames() {
        return getTableMap().keySet();
    }

    @Override
    public @Nullable RelProtoDataType getType(String name) {
        return getTypeMap().get(name);
    }

    @Override
    public Set<String> getTypeNames() {
        return getTypeMap().keySet();
    }

    @Override
    public Collection<Function> getFunctions(String name) {
        return getFunctionMultimap().get(name);
    }

    @Override
    public Set<String> getFunctionNames() {
        return getFunctionMultimap().keySet();
    }

    @Override
    public @Nullable Schema getSubSchema(String name) {
        return getSubSchemaMap().get(name);
    }

    @Override
    public Set<String> getSubSchemaNames() {
        return getSubSchemaMap().keySet();
    }

    @Override
    public Expression getExpression(@Nullable SchemaPlus parentSchema, String name) {
        requireNonNull(parentSchema, "parentSchema");
        return Schemas.subSchemaExpression(parentSchema, name, getClass());
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Schema snapshot(SchemaVersion version) {
        return this;
    }

    /**
     * 对于csv来讲，table map就是定制目录下的csv文件，这里就是扫描csv文件获取表信息
     * table中包括：
     * |    表的统计信息
     * |    row type
     * |    列是否已聚合
     * |    列是否可以聚合
     *
     * @return key：table name； value：Table信息
     */
    private Map<String, Table> getTableMap() {
        if (tableMap == null) {
            tableMap = createTableMap();
        }
        return tableMap;
    }

    private Map<String, RelProtoDataType> getTypeMap() {
        return ImmutableMap.of();
    }

    private Multimap<String, Function> getFunctionMultimap() {
        return ImmutableMultimap.of();
    }

    private Map<String, Schema> getSubSchemaMap() {
        return ImmutableMap.of();
    }

    private Map<String, Table> createTableMap() {
        // 找 .csv, .csv.gz
        Source baseSource = Sources.of(directoryFile);
        File[] files = directoryFile.listFiles((dir, name) -> {
            final String nameSansGz = trim(name, ".gz");
            return nameSansGz.endsWith(".csv");
        });
        if (files == null) {
            System.out.println("directory " + directoryFile + " not found");
            files = new File[0];
        }
        // 每个文件都是一个表
        ImmutableMap.Builder<String, Table> builder = ImmutableMap.builder();
        for (File file : files) {
            Source source = Sources.of(file);
            Source sourceSansGz = source.trim(".gz");
            Source sourceSansCsv = sourceSansGz.trimOrNull(".csv");
            if (sourceSansCsv != null) {
                // 这里直接创建Scannable的
                final Table table = new CsvScannableTable(source, null);
                builder.put(sourceSansCsv.relative(baseSource).path(), table);
            }
        }
        return builder.build();
    }
}
