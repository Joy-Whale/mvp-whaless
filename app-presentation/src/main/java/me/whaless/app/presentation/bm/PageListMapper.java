package me.whaless.app.presentation.bm;

import me.whaless.app.common.utils.MapperImpl;
import me.whaless.app.domain.model.PageListModel;
import me.whaless.app.presentation.bean.PageList;

import java.lang.reflect.ParameterizedType;


/**
 * User: JiYu
 * Date: 2016-09-19
 * Time: 16-49
 */

public abstract class PageListMapper<TM, TP, TML extends PageListModel<TM>, TPL extends PageList<TP>, TLM extends MapperImpl<TM, TP>> extends MapperImpl<TML, TPL> {

	private TLM listItemMapper;

	protected PageListMapper(TLM listItemMapper) {
		this.listItemMapper = listItemMapper;
	}

	@Override
	public TPL transform(TML tml) {
		TPL tpl = createPageList();
		tpl.setCurrentPage(tml.getCurrentPage());
		tpl.setTotalPage(tml.getTotalPage());
		tpl.setTotal(tml.getTotal());
		tpl.setPageNumber(tml.getPageNumber());
		tpl.setItems(listItemMapper.transform(tml.getItems()));
		tpl.setEnd(tml.isEnd());
		return tpl;
	}

	@Override
	public TML transform2(TPL tpl) {
		TML tml = createPageListModel();
		tml.setCurrentPage(tpl.getCurrentPage());
		tml.setTotalPage(tpl.getTotalPage());
		tml.setTotal(tpl.getTotal());
		tml.setPageNumber(tpl.getPageNumber());
		tml.setItems(listItemMapper.transform2(tpl.getItems()));
		tml.setEnd(tpl.isEnd());
		return tml;
	}

	protected TPL createPageList() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<TPL> clazz = (Class) pt.getActualTypeArguments()[3];
			// 通过反射创建model的实例
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected TML createPageListModel() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[2];
			// 通过反射创建model的实例
			return (TML) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	protected TLM getListItemMapper() {
		return listItemMapper;
	}
}
