package com.dq.demo.avatica;

import org.apache.calcite.avatica.jdbc.JdbcMeta;
import org.apache.calcite.avatica.remote.Driver;
import org.apache.calcite.avatica.remote.LocalService;
import org.apache.calcite.avatica.server.HttpServer;

import java.sql.SQLException;

public class AvaticaServer {
    public static void main(String[] args) throws SQLException, InterruptedException {
        int port = 7890;
        String url = "jdbc:mysql://localhost:3307/example";
        //
        JdbcMeta jdbcMeta = new JdbcMeta(url, "root", "bonree");
        LocalService service = new LocalService(jdbcMeta);
        HttpServer server = new HttpServer.Builder<>()
                .withPort(port)
                .withHandler(service, Driver.Serialization.PROTOBUF)
                .build();

        server.start();
        server.join();
    }
}
