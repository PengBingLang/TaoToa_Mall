package taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taotao.common.pojo.EasyUiTreeNode;
import taotao.mapper.TbItemCatMapper;
import taotao.pojo.TbItemCat;
import taotao.pojo.TbItemCatExample;
import taotao.pojo.TbItemCatExample.Criteria;
import taotao.service.ItemCatService;

/**
 * 商品分类管理的Service
 * 
 * @author 彭秉浪
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 根据parentId查询分类列表
	 * 
	 * @param parentId
	 * @return
	 * @author 彭秉浪
	 */
	@Override
	public List<EasyUiTreeNode> getItemCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		// 设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);

		// 转换为EasyUiTreeNode类型
		List<EasyUiTreeNode> result = new ArrayList<>();
		for (TbItemCat itemCat : list) {
			// 创建一个节点对象
			EasyUiTreeNode node = new EasyUiTreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent() ? "closed" : "open");
			// 添加到节点中
			result.add(node);
		}
		return result;
	}

}
