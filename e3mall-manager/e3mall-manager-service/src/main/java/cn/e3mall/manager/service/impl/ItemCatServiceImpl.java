package cn.e3mall.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.domain.TbItemCat;
import cn.e3mall.domain.TbItemCatExample;
import cn.e3mall.domain.TbItemCatExample.Criteria;
import cn.e3mall.manager.service.ItemCatService;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.utils.EasyUITreeNode;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;

	public List<EasyUITreeNode> findItemCatListWithParentId(Long parentId) {
		
		List<EasyUITreeNode> treeNodeList = new ArrayList<EasyUITreeNode>();
		TbItemCatExample example = new TbItemCatExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		List<TbItemCat> catList = itemCatMapper.selectByExample(example);
		for (TbItemCat tbItemCat : catList) {
			EasyUITreeNode treeNode = new EasyUITreeNode();
			treeNode.setId(tbItemCat.getId());
			treeNode.setText(tbItemCat.getName());
			treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			treeNodeList.add(treeNode);
		}
		return treeNodeList;
	}

}
