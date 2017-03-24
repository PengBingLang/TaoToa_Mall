package taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import taotao.common.pojo.EasyUiTreeNode;
import taotao.service.ItemCatService;

/**
 * 商品分类管理的Controller
 * 
 * @author 彭秉浪
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUiTreeNode> getItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		return itemCatService.getItemCatList(parentId);
	}
}
