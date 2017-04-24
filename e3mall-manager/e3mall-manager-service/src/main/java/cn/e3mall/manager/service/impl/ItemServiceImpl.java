package cn.e3mall.manager.service.impl;

import java.util.Date;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.domain.TbItem;
import cn.e3mall.domain.TbItemDesc;
import cn.e3mall.domain.TbItemDescExample;
import cn.e3mall.domain.TbItemExample;
import cn.e3mall.domain.TbItemExample.Criteria;
import cn.e3mall.manager.service.ItemService;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.utils.DatagridPageBean;
import cn.e3mall.utils.E3mallResult;
import cn.e3mall.utils.IDUtils;
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private JmsTemplate template;
	@Autowired
	private Destination destination;

	public TbItem findItemByID(Long id) {
		TbItemExample example = new TbItemExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIdEqualTo(id);
		List<TbItem> list = itemMapper.selectByExample(example);
		TbItem item = null;
		if(list!=null && list.size()>0){
			item =list.get(0);
		}
		return item;
	}


	public DatagridPageBean findItemList(Integer page, Integer rows) {
		DatagridPageBean pageBean = new DatagridPageBean();
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		pageBean.setRows(list);
		pageBean.setTotal(pageInfo.getTotal());
		return pageBean;
	}


	public E3mallResult saveItem(TbItem item, TbItemDesc itemDesc) {
		final long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte)1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);		
		itemMapper.insert(item);
		itemDesc.setItemId(itemId);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		
		itemDescMapper.insert(itemDesc);
		
		template.send(destination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createTextMessage(itemId+"");
			}
		});
		return E3mallResult.ok();
	}


	@Override
	public TbItemDesc findDescById(Long itemId) {
		// TODO Auto-generated method stub
		TbItemDescExample example=new TbItemDescExample();
		cn.e3mall.domain.TbItemDescExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andItemIdEqualTo(itemId);
		List<TbItemDesc> selectByExample = itemDescMapper.selectByExample(example);
		TbItemDesc desc=null;
		if(selectByExample!=null||selectByExample.size()>0){
			desc=selectByExample.get(0);
		}
		return desc;
	}

}
