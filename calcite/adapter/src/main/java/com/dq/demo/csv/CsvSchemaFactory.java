package com.dq.demo.csv;

import org.apache.calcite.model.ModelHandler;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

import java.io.File;
import java.util.Map;

public class CsvSchemaFactory implements SchemaFactory {

    /**
     * 这个工厂是要通过元数据获取具体的schema信息
     *
     * @param parentSchema Parent schema
     * @param name         Name of this schema
     * @param operand      json结构的元数据信息
     * @return Csv Schema对象
     */
    @Override
    public Schema create(SchemaPlus parentSchema, String name, Map<String, Object> operand) {
        // 对于csv来讲，主要是要获取到csv的文件地址
        String directory = (String) operand.get("directory");
        // 这里获取的逻辑就用calcite代码中自己的逻辑，不进行自己的实现
        File base = (File) operand.get(ModelHandler.ExtraOperand.BASE_DIRECTORY.camelName);
        // 判断是否是绝对路径，不是就进行补齐
        File directoryFile = new File(directory);
        if (base != null && !directoryFile.isAbsolute()) {
            directoryFile = new File(base, directory);
        }
        return new CsvSchema(directoryFile);
    }
}
