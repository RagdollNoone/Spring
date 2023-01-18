package com.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;

// 精确查找自定义算法
// 实现按照 = 或 IN 进行精确分片。
// 例如 select * from course where cid = 1 or cid in (1,3,5)
public class MyPreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    // collection 实际表的集合
    // preciseShardingValue sql语句中查询参数
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        System.out.println("collection: " + collection);
        System.out.println("preciseShardingValue: " + preciseShardingValue);

        BigInteger shardingValue = BigInteger.valueOf(preciseShardingValue.getValue());
        BigInteger res = (shardingValue.mod(new BigInteger("2"))).add(new BigInteger("1"));
        String key =  preciseShardingValue.getLogicTableName() + "_" + res;

        if(collection.contains(key)){
            return key;
        }

        throw new UnsupportedOperationException("route " + key + " is not supported. please check your config");
    }
}
