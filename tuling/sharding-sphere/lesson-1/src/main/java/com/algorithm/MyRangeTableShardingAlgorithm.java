package com.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

// 范围查找自定义算法
// 实现按照 Between 进行范围分片。
// 例如 select * from course where cid between 2000 and 3000;
public class MyRangeTableShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    // collection 实际表的集合
    // rangeShardingValue sql语句中查询参数
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        System.out.println("collection: " + collection);
        System.out.println("rangeShardingValue: " + rangeShardingValue);

        // 获取范围的方式
        Long lowerEndpoint = rangeShardingValue.getValueRange().lowerEndpoint();//2000
        Long upperEndpoint = rangeShardingValue.getValueRange().upperEndpoint();//3000

        // 从course_1和course_2当各自执行对应的range查询最后归并
        return Arrays.asList(rangeShardingValue.getLogicTableName() + "_1", rangeShardingValue.getLogicTableName() + "_2");
    }
}
