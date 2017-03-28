package taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taotao.mapper.TbItemCatMapper;
import taotao.pojo.TbItemCat;
import taotao.pojo.TbItemCatExample;
import taotao.pojo.TbItemCatExample.Criteria;
import taotao.rest.pojo.CatNode;
import taotao.rest.pojo.CatResult;
import taotao.rest.service.ItemCatService;

/**
 * 商品分类服务
 * 
 * @author 彭秉浪
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		// 查询分类列表
		catResult.setData(getCatList(0));
		return catResult;
	}

	/**
	 * 查询分类列表
	 * 
	 * @param parentId
	 * @return
	 * @author 彭秉浪
	 */
	private List<?> getCatList(long parentId) {
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 返回List
		List resultList = new ArrayList<>();

		int count = 0;// 记录一级类目的个数
		// 向List中添加节点
		for (TbItemCat itemCat : list) {
			// 判断是否为父节点
			if (itemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
				} else {
					catNode.setName(itemCat.getName());
				}
				catNode.setUrl("/products/" + itemCat.getId() + ".html");
				catNode.setItem(getCatList(itemCat.getId()));
				resultList.add(catNode);

				count++;// 一级类目只取14条
				if (count >= 14) {
					break;
				}
			} else {
				// 是叶子节点时
				resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
			}
		}
		return resultList;
	}
}
