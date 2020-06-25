package com.gang.tkunion.base;


/**
 * Presenter的基接口
 * 定义公共的注册和取消注册的操作
 */
public interface IBasePresenter<T> {

    /**
     * 给UI注册接口回调
     *
     * @param callback 设置的回调接口
     */
    void registerUICallback(T callback);

    /**
     * 给UI取消注册接口回调
     *
     * @param callback 设置的回调接口
     */
    void unregisterUICallback(T callback);

}
