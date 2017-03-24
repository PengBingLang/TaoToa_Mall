package taotao.service;

import taotao.common.pojo.EasyUiDataGrid;
import taotao.common.utils.TaotaoResult;
import taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long id);

	EasyUiDataGrid getItemList(int page, int rows);

	TaotaoResult createItem(TbItem item);
}
