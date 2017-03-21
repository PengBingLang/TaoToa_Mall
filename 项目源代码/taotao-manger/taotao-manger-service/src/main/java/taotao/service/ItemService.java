package taotao.service;

import taotao.common.pojo.EasyUiDataGrid;
import taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long id);

	EasyUiDataGrid getItemList(int page, int rows);
}
