package csv;

import com.dq.demo.csv.CsvSchema;
import com.dq.demo.csv.CsvSchemaFactory;
import org.apache.calcite.config.Lex;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public void testSelectWhere() throws SQLException {
        query("select GENDER from sales.EMPS where NAME = 'Fred'");
    }

    @Test
    public void testName() {
        String decode = URLDecoder.decode("https://paas.bkpaasdev2.com/o/cw_uac_saas/alarm/api/active/list/?query={%22status%22:[%22received%22,%22dispatched%22,%22abnormal%22,%22pending_execute%22,%22executing%22,%22autoorder_executing%22,%22autoexecute_executing%22,%22autoexecuting_failure%22]}");
        System.out.println(decode);
    }

    @Test
    public void testSelect() throws SQLException {
        query("select * from sales.DEPTS");
//        query("select t1.GENDER from (select * from sales.DEPTS) as t1");
    }

    private void query(String sql) throws SQLException {
        Properties info = new Properties();
        info.setProperty("caseSensitive", "false");
        info.setProperty("conformance", SqlConformanceEnum.MYSQL_5.toString());
        info.setProperty("lex", Lex.MYSQL.toString());

        Connection connection = DriverManager.getConnection("jdbc:calcite:", info);
//        Connection connection = DriverManager.getConnection("jdbc:avatica:remote:", info);
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        SchemaPlus rootSchema = calciteConnection.getRootSchema();
        Map<String, Object> map = new HashMap<>();
        String resourcePath = CsvTest.class.getClassLoader().getResource("sales").getPath();
        map.put("directory", resourcePath);

        CsvSchema schema = (CsvSchema) new CsvSchemaFactory().create(rootSchema, "sales", map);
        rootSchema.add("sales", schema);

        Statement statement = calciteConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
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

    @Test
    public void testStatic() {
        B.getMessage();
    }

    @Test
    public void testCreateDir() throws URISyntaxException, IOException {
        File file = new File("./cte");
        System.out.println(file.getAbsoluteFile());
        file.mkdirs();

        System.out.println(file.canWrite());
        System.out.println(file.canRead());
        System.out.println(file.setWritable(true));
        System.out.println(file.canWrite());
        System.out.println(file.canRead());
        Files.move(Paths.get(file.getAbsolutePath()), Paths.get("D:/project/-/calcite/adapter/./cte2"), StandardCopyOption.REPLACE_EXISTING);
        //file.delete();
    }

    @Test
    public void test2() {
        File file = new File("D:/project/-/calcite/adapter/./cte");
        System.out.println(file.exists());
    }
}

class A {
    protected static Map<String, String> AC;

    public A() {
        System.out.println("A 初始化了");
    }
}

class B extends A {
    static {
        AC.put("as", "c");
    }

    public static void getMessage() {
        System.out.println("A");
    }
}