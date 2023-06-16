package csv;

import com.dq.demo.csv.CsvSchema;
import com.dq.demo.csv.CsvSchemaFactory;
import org.apache.calcite.config.Lex;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CsvTest {

    @Test
    public void testSelect() throws SQLException {
        Properties info = new Properties();
        info.setProperty("caseSensitive", "false");
        info.setProperty("conformance", SqlConformanceEnum.MYSQL_5.toString());
        info.setProperty("lex", Lex.MYSQL.toString());

        Connection connection = DriverManager.getConnection("jdbc:calcite:", info);
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        SchemaPlus rootSchema = calciteConnection.getRootSchema();
        Map<String, Object> map = new HashMap<>();
        map.put("directory", "D:\\project\\-\\calcite\\adapter\\src\\test\\resources\\sales");

        CsvSchema schema = (CsvSchema) new CsvSchemaFactory().create(rootSchema, "sales", map);
        rootSchema.add("sales", schema);

        Statement statement = calciteConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from EMPS");
        ResultSetMetaData metaData = resultSet.getMetaData();

        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; ; i++) {
                System.out.print(resultSet.getString(i));
                if (i < columnCount) {
                    System.out.print(", ");
                } else {
                    System.out.println();
                    break;
                }
            }
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
