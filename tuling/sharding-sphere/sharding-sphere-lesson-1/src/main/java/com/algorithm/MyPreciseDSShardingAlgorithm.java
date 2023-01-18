package com.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;

public class MyPreciseDSShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        System.out.println("collection: " + collection);
        System.out.println("MyPreciseDSShardingAlgorithm preciseShardingValue: " + preciseShardingValue);

        BigInteger shardingValue = BigInteger.valueOf(preciseShardingValue.getValue());
        BigInteger res = (shardingValue.mod(new BigInteger("2"))).add(new BigInteger("1"));
        String key =  "m" + res;

        if(collection.contains(key)){
            return key;
        }

        throw new UnsupportedOperationException("route " + key + " is not supported. please check your config");
    }
}
