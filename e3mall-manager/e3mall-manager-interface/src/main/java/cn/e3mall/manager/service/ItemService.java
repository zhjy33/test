package cn.e3mall.manager.service;

import cn.e3mall.domain.TbItem;
import cn.e3mall.domain.TbItemDesc;
import cn.e3mall.utils.DatagridPageBean;
import cn.e3mall.utils.E3mallResult;

public interface ItemService {
	
	/**
	 * 需求：根据Id查询商品
	 * 参数：Long id
	 * 返回值：TbItem
	 * 方法名：findItemByID
	 */
	public TbItem findItemByID(Long id);
	/**
	 * 需求：查询商品列表，分页展示
	 * 参数：Integer page,Integer rows
	 * 返回值：DatagridPageBean
	 */
	public DatagridPageBean findItemList(Integer page,Integer rows);
	/**
	 * 需求：保存商品
	 * 参数：Tbitem,TbitemDesc
	 * 返回值:E3mallResult
	 */
	public E3mallResult saveItem(TbItem item,TbItemDesc itemDesc);

	
	public TbItemDesc findDescById(Long itemId);
}
