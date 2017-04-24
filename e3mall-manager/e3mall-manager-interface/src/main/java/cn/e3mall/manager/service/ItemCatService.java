package cn.e3mall.manager.service;

import java.util.List;

import cn.e3mall.utils.EasyUITreeNode;

public interface ItemCatService {
	
	/**
	 * 需求：根据父Id查询此父Id下子节点（加载树形菜单）
	 * 参数：Long parentId
	 * 返回值：List<EasyUITreeNode>
	 * 方法名：findItemCatListWithParentId
	 */
	public List<EasyUITreeNode> findItemCatListWithParentId(Long parentId);

}
