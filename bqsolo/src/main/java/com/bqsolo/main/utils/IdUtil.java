package com.bqsolo.main.utils;
/**
 * id生成工具类
 * @author deng
 *
 */
public class IdUtil {
		//商品id
		private static Integer goodsId=1000;
		//商品类型id(改为标签id)
		private static Integer goodsTypeId=1;
		//商品采集url id
		private static Integer inUrlsId=1;
		//商品类型id
		private static Integer itemTypeId=1;
		/**
		 * 获取商品id,每次调用都自增+1	
		 * @return
		 */
		public static synchronized Integer getGoodsId() {
			return goodsId=goodsId+1;
		}

		public static void setGoodsId(Integer goodsId) {
			if(goodsId!=null && goodsId>IdUtil.goodsId){
				IdUtil.goodsId = goodsId;
			}
		}

		public static synchronized Integer getGoodsTypeId() {
			return goodsTypeId=goodsTypeId+1;
		}

		public static void setGoodsTypeId(Integer goodsTypeId) {
			if(goodsTypeId !=null && goodsTypeId>0){
				IdUtil.goodsTypeId = goodsTypeId;
			}
		}

		public static synchronized Integer getInUrlsId() {
			return inUrlsId=inUrlsId+1;
		}

		public static void setInUrlsId(Integer inUrlsId) {
			if(inUrlsId !=null && inUrlsId>0){
				IdUtil.inUrlsId = inUrlsId;
			}
		}

		public static synchronized Integer getItemTypeId() {
			return itemTypeId=itemTypeId+1;
		}

		public static void setItemTypeId(Integer itemTypeId) {
			if(itemTypeId!=null && itemTypeId>0){
				IdUtil.itemTypeId = itemTypeId;
			}
		}
		
		
}
