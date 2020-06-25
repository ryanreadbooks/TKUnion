package com.gang.tkunion.presenters.interfaces;

import com.gang.tkunion.base.IBasePresenter;
import com.gang.tkunion.ui.callbacks.IHomeCallback;

public interface IHomePresenter extends IBasePresenter<IHomeCallback> {

    /**
     * presenter对外提供的获取数据的方法
     * 外部的UI注册了callback之后，通过callback中获得返回的数据
     */
    void getCategories();

}
