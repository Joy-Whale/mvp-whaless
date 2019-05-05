package me.whaless.app.presentation.dagger.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * User: JiYu
 * DateModel: 2016-08-04
 * Time: 19-15
 *
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the activity to be memorized in the
 * correct component.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {}
