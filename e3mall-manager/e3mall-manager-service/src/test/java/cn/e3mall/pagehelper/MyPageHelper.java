package cn.e3mall.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.domain.TbItem;
import cn.e3mall.domain.TbItemExample;
import cn.e3mall.mapper.TbItemMapper;

public class MyPageHelper {

	/**
	 * 需求：测试分页查询
	 */
	@Test
	public void testPageHelper() {
		// 加载dao配置文件
		ApplicationContext app = new ClassPathXmlApplicationContext(
				"classpath*:applicationContext-dao.xml");
		//获取商品接口代理对象
		TbItemMapper itemMapper = app.getBean(TbItemMapper.class);
		//创建Example对象
		TbItemExample example = new TbItemExample();
		//执行查询之前，必须设置分页
		PageHelper.startPage(0, 10);
		//查询
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//创建分页插件包装对象PageInfo,获取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		//获取总记录数
		long total = pageInfo.getTotal();
		
		System.out.println("商品总记录数:"+total);
		
	}

}
