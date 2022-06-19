package com.help.modules.o2o.dao;

import com.help.modules.o2o.entity.GoodsPicEntity;
import com.help.modules.sys.dao.BaseDao;

import org.apache.ibatis.annotations.Mapper;

/**
 * 商品图片
 *
 */
@Mapper
public interface GoodsPicDao extends BaseDao<GoodsPicEntity> {

	GoodsPicEntity queryOneByGoodsId(Integer goodsId);

	String[] queryByGoodsId(Integer goodsId);

	void deleteByGoodsId(Integer goodsId);
	
}
