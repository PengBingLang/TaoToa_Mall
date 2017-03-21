package taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taotao.mapper.TbItemMapper;
import taotao.pojo.TbItem;
import taotao.pojo.TbItemExample;
import taotao.pojo.TbItemExample.Criteria;
import taotao.service.ItemService;

/**
 * 商品管理Service
 * 
 * @author 彭秉浪
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Override
	public TbItem getItemById(long itemId) {
		// 第一种方法：
		// itemMapper.selectByPrimaryKey(itemId);

		// 第二种方法：
		TbItemExample example = new TbItemExample();
		// 添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
