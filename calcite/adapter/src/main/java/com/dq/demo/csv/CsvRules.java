package com.dq.demo.csv;

public class CsvRules {
    private CsvRules() {
    }

    /**
     * Rule that matches a {@link org.apache.calcite.rel.core.Project} on
     * a {@link CsvTableScan} and pushes down projects if possible.
     */
    public static final CsvProjectTableScanRule PROJECT_SCAN = CsvProjectTableScanRule.Config.DEFAULT.toRule();
}
