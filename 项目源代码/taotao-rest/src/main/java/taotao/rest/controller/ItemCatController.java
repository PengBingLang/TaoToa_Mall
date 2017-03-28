package taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import taotao.common.utils.JsonUtils;
import taotao.rest.pojo.CatResult;
import taotao.rest.service.ItemCatService;

/**
 * 商品分类列表
 * 
 * @author 彭秉浪
 */
@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping(value = "/itemcat/all", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		// 把这个pojo对象转换为一个字符串
		String json = JsonUtils.objectToJson(catResult);
		// 拼装返回值
		return callback + "(" + json + ");";
	}
}
