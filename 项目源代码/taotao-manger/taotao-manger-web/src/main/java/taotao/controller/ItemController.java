package taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import taotao.common.pojo.EasyUiDataGrid;
import taotao.pojo.TbItem;
import taotao.service.ItemService;

/**
 * 商品管理Controller
 * 
 * @author 彭秉浪
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		return itemService.getItemById(itemId);
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUiDataGrid getItemList(Integer page, Integer rows) {
		return itemService.getItemList(page, rows);
	}

}
