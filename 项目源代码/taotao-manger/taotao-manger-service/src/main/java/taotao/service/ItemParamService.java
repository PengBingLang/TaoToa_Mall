package taotao.service;

import taotao.common.pojo.EasyUiDataGrid;
import taotao.common.utils.TaotaoResult;
import taotao.pojo.TbItemParam;

public interface ItemParamService {

	TaotaoResult getItemParamByCid(long cid);

	TaotaoResult insertItemParam(TbItemParam itemParam);

	EasyUiDataGrid getItemParamList(int page, int rows);
}
