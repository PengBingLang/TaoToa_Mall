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
import taotao.mapper.TbItemDescMapper;
import taotao.mapper.TbItemMapper;
import taotao.mapper.TbItemParamItemMapper;
import taotao.pojo.TbItem;
import taotao.pojo.TbItemDesc;
import taotao.pojo.TbItemExample;
import taotao.pojo.TbItemExample.Criteria;
import taotao.pojo.TbItemParam;
import taotao.pojo.TbItemParamItem;
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
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	/**
	 * 根据ID查询单个商品
	 * 
	 * @param itemId
	 * @return
	 * @author 彭秉浪
	 */
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

	/**
	 * 添加一个新商品
	 * 
	 * @param item
	 * @param desc
	 * @return
	 * @author 彭秉浪
	 */
	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
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

		// 添加商品描述信息
		TaotaoResult result = insertItemDesc(id, desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}

		// 添加规格参数
		result = insertItemParamItem(id, itemParam);
		return TaotaoResult.ok();
	}

	/**
	 * 添加商品描述
	 * 
	 * @param itemId
	 * @param desc
	 * @return
	 * @author 彭秉浪
	 */
	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}

	/**
	 * 添加商品的规格参数
	 * 
	 * @param itemId
	 * @param itemParam
	 * @return
	 * @author 彭秉浪
	 */
	private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		// 创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		// 向数据库中插入数据
		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}
}
