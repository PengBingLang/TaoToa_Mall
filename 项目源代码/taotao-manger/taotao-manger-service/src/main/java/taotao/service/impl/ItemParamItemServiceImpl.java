package taotao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taotao.common.utils.JsonUtils;
import taotao.mapper.TbItemParamItemMapper;
import taotao.pojo.TbItemParamItem;
import taotao.pojo.TbItemParamItemExample;
import taotao.pojo.TbItemParamItemExample.Criteria;
import taotao.service.ItemParamItemService;

/**
 * 展示商品规格参数
 * 
 * @author 彭秉浪
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public String getItemParamByItemId(Long itemId) {
		// 根据商品ID查询商品的规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		// 执行查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.size() == 0) {
			return "";
		}
		// 不为空就取出参数信息;
		TbItemParamItem itemParamItem = list.get(0);
		String paramData = itemParamItem.getParamData();

		// 生成HTML
		// 把规格参数json数据转换成java对象
		List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding='0' cellspacing='1' width='100%' border='1'");
		sb.append("<tbody>");
		for (Map m1 : jsonList) {
			sb.append("<tr>");
			sb.append("<th class='tdTitle' colspan='2'>" + m1.get("group") + "</th>");
			sb.append("</tr>");
			List<Map> list2 = (List<Map>) m1.get("params");
			for (Map m2 : list2) {
				sb.append("<tr>");
				sb.append("<td class='tdTitle'>" + m2.get("k") + "</td>");
				sb.append("<td>" + m2.get("v") + "</td>");
				sb.append("</tr>");
			}
		}
		sb.append("</tbody>");
		sb.append("</table>");
		return sb.toString();
	}

}
