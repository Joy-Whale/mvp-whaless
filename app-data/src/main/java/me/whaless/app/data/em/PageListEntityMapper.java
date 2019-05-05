package me.whaless.app.data.em;

import me.whaless.app.common.utils.MapperImpl;
import me.whaless.app.data.entity.PageListEntity;
import me.whaless.app.domain.model.PageListModel;

import java.lang.reflect.ParameterizedType;


/**
 * User: JiYu
 * Date: 2016-09-19
 * Time: 16-12
 * @param <TM>  domain item
 * @param <TE>  data item
 * @param <TML> domain PageListModel {@link PageListModel}
 * @param <TEL> data PageListEntity {@link PageListEntity}
 * @param <TLM> item mapper
 */

public abstract class PageListEntityMapper<TE, TM, TEL extends PageListEntity<TE>, TML extends PageListModel<TM>, TLM extends MapperImpl<TE, TM>> extends
		MapperImpl<TEL, TML> {

	private TLM listItemMapper;

	protected PageListEntityMapper(TLM listItemMapper) {
		this.listItemMapper = listItemMapper;
	}

	@Override
	public TEL transform2(TML tml) {
		TEL tel = createPageListEntity();
		tel.setTotalPage(tml.getTotalPage());
		tel.setCurrentPage(tml.getCurrentPage());
		tel.setTotal(tml.getTotal());
		tel.setPageNumber(tml.getPageNumber());
		tel.setItems(listItemMapper.transform2(tml.getItems()));
		tel.setEnd(tml.isEnd());
		return tel;
	}

	@Override
	public TML transform(TEL o2) {
		TML o = createPageListModel();
		o.setTotalPage(o2.getTotalPage());
		o.setCurrentPage(o2.getCurrentPage());
		o.setTotal(o2.getTotal());
		o.setPageNumber(o2.getPageNumber());
		o.setItems(listItemMapper.transform(o2.getItems()));
		o.setEnd(o2.isEnd());
		return o;
	}

	//	@NonNull
	//	protected abstract TEL createPageListEntity();
	//
	//	@NonNull
	//	protected abstract TML createPageListModel();

	protected TML createPageListModel() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[3];
			// 通过反射创建model的实例
			return (TML) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected TEL createPageListEntity() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[2];
			// 通过反射创建model的实例
			return (TEL) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected TLM getListItemMapper() {
		return listItemMapper;
	}
}
