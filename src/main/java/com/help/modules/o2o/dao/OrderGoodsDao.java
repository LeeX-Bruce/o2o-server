package com.help.modules.o2o.dao;

import com.help.modules.o2o.entity.OrderGoodsEntity;
import com.help.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 *
 */
@Mapper
@Repository
public interface OrderGoodsDao extends BaseDao<OrderGoodsEntity> {



//    static OrderGoodsEntity find(Integer id) {
//        return null;
//
    public OrderGoodsEntity find(Integer id);

    public  OrderGoodsEntity findorderid(Integer id);
}
