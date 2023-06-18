package com.dq.demo.csv;

import org.immutables.value.Value;

import java.util.List;
import java.util.Set;

@Value.Immutable
public abstract class FoorBarValue {
    public abstract int foo();
    public abstract String bar();
    public abstract List<Integer> buz();
    public abstract Set<Long> crux();
}
