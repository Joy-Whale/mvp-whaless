package me.whaless.app.data.em.common;

import me.whaless.app.data.em.PageListEntityMapper;
import me.whaless.app.data.entity.common.TestEntity;
import me.whaless.app.data.entity.common.TestListEntity;
import me.whaless.app.domain.model.common.TestListModel;
import me.whaless.app.domain.model.common.TestModel;

import javax.inject.Inject;

/**
 * User: Joy
 * Date: 2017/5/22
 * Time: 11:38
 */

public class TestListEntityMapper extends PageListEntityMapper<TestEntity, TestModel, TestListEntity, TestListModel, TestEntityMapper> {

	@Inject
	TestListEntityMapper(TestEntityMapper listItemMapper) {
		super(listItemMapper);
	}

	public TestListModel transform(TestListEntity testListEntity) {
		return super.transform(testListEntity);
	}
}
