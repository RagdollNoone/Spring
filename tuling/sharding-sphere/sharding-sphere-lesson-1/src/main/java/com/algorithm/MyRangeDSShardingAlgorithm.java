package com.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

public class MyRangeDSShardingAlgorithm implements RangeShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        System.out.println("collection: " + collection);
        System.out.println("MyRangeDSShardingAlgorithm: " + rangeShardingValue);

        return collection;
    }
}
