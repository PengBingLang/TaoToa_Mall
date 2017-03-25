package taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import taotao.common.pojo.EasyUiDataGrid;
import taotao.common.utils.TaotaoResult;
import taotao.mapper.TbItemParamMapper;
import taotao.pojo.TbItemParam;
import taotao.pojo.TbItemParamExample;
import taotao.pojo.TbItemParamExample.Criteria;

/**
 * 商品参数模板的管理
 * 
 * @author 彭秉浪
 */
@Service
public class ItemParamServiceImpl implements taotao.service.ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);// 包含大文本类型要用这个查询方法

		// 判断该类目的参数模板是否已经存在
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		// 补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		// 插手到数据库
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

	/**
	 * 商品规格模板信息查询
	 * 
	 * @return
	 * @author 彭秉浪
	 */
	@Override
	public EasyUiDataGrid getItemParamList(int page, int rows) {
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page, rows); // 分页处理
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);

		// 创建返回值对象
		EasyUiDataGrid result = new EasyUiDataGrid();
		result.setRows(list);

		// 取出数据总条数
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
}
