package com.dq.demo.avatica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AvaticaClient {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:avatica:remote:url=http://localhost:8765;");
    }
}
