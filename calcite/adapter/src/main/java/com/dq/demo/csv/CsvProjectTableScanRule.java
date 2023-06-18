package com.dq.demo.csv;

import org.apache.calcite.plan.RelOptRuleCall;
import org.apache.calcite.plan.RelRule;
import org.apache.calcite.rel.logical.LogicalProject;
import org.apache.calcite.rex.RexInputRef;
import org.apache.calcite.rex.RexNode;
import org.immutables.value.Value;

import java.util.List;

@Value.Enclosing
public class CsvProjectTableScanRule extends RelRule<CsvProjectTableScanRule.Config> {

    /**
     * Creates a CsvProjectTableScanRule.
     */
    protected CsvProjectTableScanRule(Config config) {
        super(config);
    }

    @Override
    public void onMatch(RelOptRuleCall call) {
        final LogicalProject project = call.rel(0);
        final CsvTableScan scan = call.rel(1);
        int[] fields = getProjectFields(project.getProjects());
        if (fields == null) {
            // Project contains expressions more complex than just field references.
            return;
        }
        call.transformTo(
            new CsvTableScan(
                scan.getCluster(),
                scan.getTable(),
                scan.csvTable,
                fields));
    }

    private static int[] getProjectFields(List<RexNode> exps) {
        final int[] fields = new int[exps.size()];
        for (int i = 0; i < exps.size(); i++) {
            final RexNode exp = exps.get(i);
            if (exp instanceof RexInputRef) {
                fields[i] = ((RexInputRef) exp).getIndex();
            } else {
                return null; // not a simple projection
            }
        }
        return fields;
    }

    /**
     * Rule configuration.
     */
    @Value.Immutable(singleton = true)
    public interface Config extends RelRule.Config {
        Config DEFAULT = ImmutableCsvProjectTableScanRule
            .Config
            .builder()
            .operandSupplier(b0 -> b0
                .operand(LogicalProject.class)
                .oneInput(b1 -> b1
                    .operand(CsvTableScan.class)
                    .noInputs()))
            .build();

        @Override
        default CsvProjectTableScanRule toRule() {
            return new CsvProjectTableScanRule(this);
        }
    }
}