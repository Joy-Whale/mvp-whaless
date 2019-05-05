package me.whaless.app.presentation.bm.common;

import me.whaless.app.common.utils.MapperImpl;
import me.whaless.app.domain.model.common.TestModel;
import me.whaless.app.presentation.bean.common.Test;

/**
 * Author: Ji
 * Date:   2019/5/5
 */
public class TestMapper extends MapperImpl<TestModel, Test> {

    @Override
    public Test transform(TestModel o2) {
        return super.transform(o2);
    }
}
