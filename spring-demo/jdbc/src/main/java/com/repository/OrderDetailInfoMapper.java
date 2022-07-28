package com.repository;

import com.model.OrderDetailInfo;
import com.model.OrderDetailInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDetailInfoMapper {
    long countByExample(OrderDetailInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetailInfo record);

    int insertSelective(OrderDetailInfo record);

    List<OrderDetailInfo> selectByExample(OrderDetailInfoExample example);

    OrderDetailInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderDetailInfo record, @Param("example") OrderDetailInfoExample example);

    int updateByExample(@Param("record") OrderDetailInfo record, @Param("example") OrderDetailInfoExample example);

    int updateByPrimaryKeySelective(OrderDetailInfo record);

    int updateByPrimaryKey(OrderDetailInfo record);
}