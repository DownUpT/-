package com.dq.collector.hbase;

import com.dq.collector.hbase.point.TraceRowKeyDecoderV2;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GetTraceDataFromHbase {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetTraceDataFromHbase.class);

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "10.241.3.201");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        Configuration hbaseConfig = HBaseConfiguration.create(conf);
        try (Connection connection = ConnectionFactory.createConnection(hbaseConfig)) {
            TableName[] tableNames = connection.getAdmin().listTableNames();
            for (TableName tableName : tableNames) {
                LOGGER.info("tableNames {}", tableName);
            }

            Table table = connection.getTable(TableName.valueOf("TraceV2"));
            ResultScanner scanner = table.getScanner(new Scan());
            TraceRowKeyDecoderV2 decoder = new TraceRowKeyDecoderV2();
            for (Result result : scanner) {
                for (Cell cell : result.rawCells()) {
                    LOGGER.info("column family: {}", new String(CellUtil.cloneFamily(cell)));

                    LOGGER.info("column row: {}", decoder.decodeRowKey((CellUtil.cloneRow(cell))));

                    LOGGER.info("column qualifier: {}", decoder.readQualifier(CellUtil.cloneQualifier(cell)));

                    LOGGER.info("column value: {}", decoder.readSpanValue2(CellUtil.cloneValue(cell)));

                    LOGGER.info("column tags: {}", new String(CellUtil.cloneTags(cell)));
                }
            }

            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
