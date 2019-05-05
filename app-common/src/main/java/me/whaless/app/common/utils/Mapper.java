package me.whaless.app.common.utils;

/**
 * UserModel: JiYu
 * Date: 2016-08-09
 * Time: 19-39
 */

public interface Mapper<A, B> {

	B transform(A q);

	A transform2(B t);
}
