package me.whaless.app.presentation.dagger.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * User: Joy
 * DateModel: 2016/11/1
 * Time: 17:23
 */
@Scope
@Retention(RUNTIME)
public @interface PerApp {

}
