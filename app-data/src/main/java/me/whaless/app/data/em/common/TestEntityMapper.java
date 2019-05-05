package me.whaless.app.data.em.common;

import me.whaless.app.common.utils.MapperImpl;
import me.whaless.app.data.entity.common.TestEntity;
import me.whaless.app.domain.model.common.TestModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: Joy
 * Date: 2017/5/22
 * Time: 11:33
 */
@Singleton
public class TestEntityMapper extends MapperImpl<TestEntity, TestModel> {

	@Inject
	TestEntityMapper() {

	}

	@Override
	public TestModel transform(TestEntity t2) {
		if (t2 == null)
			return null;
		TestModel t = new TestModel();
		t.setId(t2.getId());
		t.setName(t2.getName());
		return t;
	}
}
