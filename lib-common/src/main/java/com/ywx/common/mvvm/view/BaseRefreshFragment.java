package com.ywx.common.mvvm.view;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ywx.common.mvvm.viewmodel.BaseRefreshViewModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * @author : WX.Y
 * date : 2021/3/12 17:15
 * description :
 */
public abstract class BaseRefreshFragment<DB extends ViewDataBinding, VM extends BaseRefreshViewModel, T>
        extends BaseMvvmFragment<DB, VM>
        implements OnRefreshLoadMoreListener {

    private WrapRefresh mWrapRefresh;

    @CallSuper
    @Override
    public void bindListener() {
        super.bindListener();
        mWrapRefresh = onBindWrapRefresh();
        mWrapRefresh.refreshLayout.setOnRefreshLoadMoreListener(this);
    }

    @NonNull
    protected abstract WrapRefresh onBindWrapRefresh();

    @Override
    protected void initBaseViewObservable() {
        super.initBaseViewObservable();
        mViewModel.getFinishRefreshEvent().observe(this, (Observer<List<T>>) list -> {
            if (list == null) {
                mWrapRefresh.refreshLayout.finishRefresh(false);
                return;
            }
            if (list.size() == 0) {
                mWrapRefresh.refreshLayout.finishRefresh(true);
                return;
            }
            mWrapRefresh.refreshLayout.finishRefresh(true);
            onRefreshSuccess(list);
        });
        mViewModel.getFinishLoadMoreEvent().observe(this, (Observer<List<T>>) list -> {
            if (list == null) {
                mWrapRefresh.refreshLayout.finishLoadMore(false);
                return;
            }
            if (list.size() == 0) {
                mWrapRefresh.refreshLayout.finishLoadMoreWithNoMoreData();
                return;
            }
            mWrapRefresh.refreshLayout.finishLoadMore(true);
            onLoadMoreSuccess(list);
        });
    }

    protected void onRefreshSuccess(List<T> list) {
        if (mWrapRefresh.quickAdapter != null) {
            mWrapRefresh.quickAdapter.setNewData(list);
        }
    }

    protected void onLoadMoreSuccess(List<T> list) {
        if (mWrapRefresh.quickAdapter != null) {
            mWrapRefresh.quickAdapter.addData(list);
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mViewModel.onViewLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mViewModel.onViewRefresh();
    }

    protected class WrapRefresh {
        SmartRefreshLayout refreshLayout;
        BaseQuickAdapter<T, BaseViewHolder> quickAdapter;

        public WrapRefresh(@NonNull SmartRefreshLayout refreshLayout, BaseQuickAdapter<T, BaseViewHolder> quickAdapter) {
            this.refreshLayout = refreshLayout;
            this.quickAdapter = quickAdapter;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mWrapRefresh) {
            mWrapRefresh.refreshLayout.setOnRefreshLoadMoreListener(null);
        }
    }
}
