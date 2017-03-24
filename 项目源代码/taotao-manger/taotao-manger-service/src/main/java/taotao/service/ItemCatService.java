package taotao.service;

import java.util.List;

import taotao.common.pojo.EasyUiTreeNode;

public interface ItemCatService {

	List<EasyUiTreeNode> getItemCatList(long parentId);
}
