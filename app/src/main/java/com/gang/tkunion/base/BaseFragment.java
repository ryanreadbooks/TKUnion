package com.gang.tkunion.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gang.tkunion.R;

public abstract class BaseFragment extends Fragment {

    private View mRootView; // 根View
    private FrameLayout mBaseContainer; // 基容器
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;

    // 页面的5中状态 {无, 加载中, 成功, 失败, 内容为空}
    public enum Status {NONE, LOADING, SUCCESS, ERROR, EMPTY}

    public Status mCurrentStatus = Status.NONE;

    // 统一加载页面
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加载统一的基容器
        // 用于放置其它view的rootView
        mRootView = loadRootView(inflater, container);
        mBaseContainer = mRootView.findViewById(R.id.baseFragmentContainer);
        mBaseContainer.removeAllViews();
        // 添加各个状态的页面值基容器中
        // 正在加载中的页面
        if (mLoadingView == null) {
            mLoadingView = inflater.inflate(R.layout.layout_loading, container, false);
            mBaseContainer.addView(mLoadingView);
        }
        // 数据为空的页面
        if (mEmptyView == null) {
            mEmptyView = inflater.inflate(R.layout.layout_empty, container, false);
            mBaseContainer.addView(mEmptyView);
        }
        if (mErrorView == null) {
            // 加载出错的页面
            mErrorView = inflater.inflate(R.layout.layout_error, container, false);
            // 点击重新加载重试
            mErrorView.findViewById(R.id.errorLayoutRetry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    retryLoadData();
                }
            });
            mBaseContainer.addView(mErrorView);
        }
        // 加载成功后的页面
        if (mSuccessView == null) {
            mSuccessView = loadSuccessView(inflater, container);
            mBaseContainer.addView(mSuccessView);
        }
        setStatus(Status.NONE);
        return mRootView;
    }

    /**
     * 加载默认根布局
     * 子类可以根据需要重写以实现不同的根布局 但是根布局内必须有 id为 baseFragmentContainer的FrameLayout
     * @param inflater inflater
     * @param container container
     * @return 根布局
     */
    protected View loadRootView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_base_fragment, container, false);
    }

    /**
     * 给子类设置状态用
     *
     * @param newStatus 新状态
     */
    public void setStatus(Status newStatus) {
        mCurrentStatus = newStatus;
        mLoadingView.setVisibility(mCurrentStatus == Status.LOADING ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(mCurrentStatus == Status.EMPTY ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(mCurrentStatus == Status.ERROR ? View.VISIBLE : View.GONE);
        mSuccessView.setVisibility(mCurrentStatus == Status.SUCCESS ? View.VISIBLE : View.GONE);
    }

    /**
     * 子类统一加载成功的布局
     *
     * @param inflater inflater
     * @param container container
     * @return 加载的View
     */
    private View loadSuccessView(LayoutInflater inflater, ViewGroup container) {
        int resId = setSuccessViewId();
        return inflater.inflate(resId, container, false);
    }

    // 页面加载完成后的统一行为

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initEvents();
    }
    // 资源释放的统一行为

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        release();
    }
    /**
     * 子类统一设置加载资源
     * 返回设置的资源id
     *
     * @return 设置的资源id
     */
    protected abstract int setSuccessViewId();

    /**
     * 初始化事件监听
     */
    protected abstract void initEvents();

    /**
     * 初始化view
     * @param view 根view
     */
    protected abstract void initViews(View view);

    /**
     * 初始化presenter
     */
    public abstract void initPresenters();

    /**
     * 加载错误的重试
     */
    protected abstract void retryLoadData();

    /**
     * 加载数据
     */
    public abstract void loadData();

    /**
     * 资源释放
     */
    protected abstract void release();

}
