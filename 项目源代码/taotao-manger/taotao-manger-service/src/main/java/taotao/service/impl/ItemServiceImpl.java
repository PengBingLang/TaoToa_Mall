package taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import taotao.common.pojo.EasyUiDataGrid;
import taotao.common.utils.IDUtils;
import taotao.common.utils.TaotaoResult;
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

	/**
	 * 商品列表查询
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * @author 彭秉浪
	 */
	@Override
	public EasyUiDataGrid getItemList(int page, int rows) {
		// 查询商品列表
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows); // 分页处理
		List<TbItem> list = itemMapper.selectByExample(example);

		// 创建返回值对象
		EasyUiDataGrid result = new EasyUiDataGrid();
		result.setRows(list);

		// 取出数据总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item) {
		// 将tbItem补全
		// 生成商品ID
		Long id = IDUtils.genItemId();
		item.setId(id);
		// 商品状态：1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 插入到数据库
		itemMapper.insert(item);
		return TaotaoResult.ok();
	}

}
