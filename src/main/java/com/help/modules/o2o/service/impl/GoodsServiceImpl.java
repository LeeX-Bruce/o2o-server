package com.help.modules.o2o.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.help.modules.o2o.dao.GoodsDao;
import com.help.modules.o2o.dao.GoodsPicDao;
import com.help.modules.o2o.entity.GoodsEntity;
import com.help.modules.o2o.entity.GoodsPicEntity;
import com.help.modules.o2o.service.GoodsService;


@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private GoodsPicDao goodsPicDao;

	//@Autowired
	//OrderGoodsDao orderGoodsDao;

	@Override
	public GoodsEntity queryObject(Integer goodsId){
		String[] picUrls = goodsPicDao.queryByGoodsId(goodsId);
		GoodsEntity goods = goodsDao.queryObject(goodsId);
		goods.setPicUrls(picUrls);
		return goods;
	}
	
	@Override
	public List<GoodsEntity> queryList(Map<String, Object> map){
		List<GoodsEntity> goodsList = goodsDao.queryList(map);
		return goodsList;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return goodsDao.queryTotal(map);
	}
	
	@Override
	public void save(GoodsEntity goods){
		//System.out.println(goods);
	//OrderGoodsEntity orderGoodsEntity = new OrderGoodsEntity();
		//orderGoodsEntity.setGoodsId(goods.getGoodsId());
	//orderGoodsEntity.setQtime(goods.getQtime());
//		orderGoodsEntity.setGoodsName(goods.getGoodsName());
//		orderGoodsEntity.setPrice(goods.getPrice());

	//orderGoodsDao.save(orderGoodsEntity);





		goodsDao.save(goods);
		if (goods.getPicUrls() != null) {
			String[] picUrls = goods.getPicUrls();
			for (String picUrl : picUrls) {
				GoodsPicEntity goodsPic = new GoodsPicEntity();
				goodsPic.setGoodsId(goods.getGoodsId());
				goodsPic.setPicUrl(picUrl);
				goodsPicDao.save(goodsPic);
			}
		}

	}
	
	@Override
	public void update(GoodsEntity goods){
		if (goods.getPicUrls() != null) {
			String[] picUrls = goodsPicDao.queryByGoodsId(goods.getGoodsId());
			boolean e = isQualsPic(picUrls, goods.getPicUrls());
			if (!e) {
				goodsPicDao.deleteByGoodsId(goods.getGoodsId());
				for (String picUrl : goods.getPicUrls()) {
					GoodsPicEntity goodsPic = new GoodsPicEntity();
					goodsPic.setGoodsId(goods.getGoodsId());
					goodsPic.setPicUrl(picUrl);
					goodsPicDao.save(goodsPic);
				}
			}
		}
		goodsDao.update(goods);
	}
	
	private boolean isQualsPic(String[] picUrls, String[] picUrls2) {
		if (picUrls.length == picUrls2.length) {
			for (int i = 0; i < picUrls.length; i++) {
				if (!picUrls[i].equals(picUrls2[i])) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void delete(Integer goodsId){
		goodsDao.delete(goodsId);
	}
	
	@Override
	public void deleteBatch(Integer[] goodsIds){
		goodsDao.deleteBatch(goodsIds);
	}
	
}
