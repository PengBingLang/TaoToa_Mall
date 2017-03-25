package taotao.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import taotao.common.pojo.EasyUiDataGrid;
import taotao.common.utils.TaotaoResult;
import taotao.pojo.TbItemParam;
import taotao.service.ItemParamService;

/**
 * 商品参数模板管理的controller
 * 
 * @author 彭秉浪
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/list")
	@ResponseBody
	public EasyUiDataGrid getItemParam(Integer page, Integer rows) {
		return itemParamService.getItemParamList(page, rows);
	}

	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) {
		return itemParamService.getItemParamByCid(itemCatId);
	}

	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
		// 创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		return itemParamService.insertItemParam(itemParam);
	}
}
