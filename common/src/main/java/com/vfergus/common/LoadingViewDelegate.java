package com.vfergus.common;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.lang.ref.WeakReference;

public class LoadingViewDelegate {
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @Nullable
    public final View getContentView() {
        final View contentView;

        if (this.mContentViewRef != null) {
            contentView = this.mContentViewRef.get();
        } else {
            contentView = null;
        }

        return contentView;
    }

    @Nullable
    public final View getLoadingView() {
        final View loadingView;

        if (mLoadingViewRef != null) {
            loadingView = mLoadingViewRef.get();
        } else {
            loadingView = null;
        }

        return loadingView;
    }

    @Nullable
    public final View getNoContentView() {
        final View noContentView;

        if (mNoContentViewRef != null) {
            noContentView = mNoContentViewRef.get();
        } else {
            noContentView = null;
        }

        return noContentView;
    }

    @CallSuper
    public void hideAll() {
        setContentVisible(false);
        setLoadingVisible(false);
        setErrorVisible(false);
        setNoContentVisible(false);
    }

    @CallSuper
    public void showContent() {
        setContentVisible(true);
        setLoadingVisible(false);
        setErrorVisible(false);
        setNoContentVisible(false);
    }

    @CallSuper
    public void showError() {
        setContentVisible(false);
        setLoadingVisible(false);
        setErrorVisible(true);
        setNoContentVisible(false);
    }

    @CallSuper
    public void showLoading() {
        setContentVisible(false);
        setLoadingVisible(true);
        setErrorVisible(false);
        setNoContentVisible(false);
    }

    @CallSuper
    public void showNoContent() {
        setContentVisible(false);
        setNoContentVisible(true);
        setLoadingVisible(false);
        setErrorVisible(false);
    }

    @Nullable
    final View getErrorView() {
        final View errorView;

        if (mErrorViewRef != null) {
            errorView = mErrorViewRef.get();
        } else {
            errorView = null;
        }

        return errorView;
    }

    @Nullable
    private WeakReference<View> mContentViewRef;

    @Nullable
    private WeakReference<View> mErrorViewRef;

    @Nullable
    private WeakReference<View> mLoadingViewRef;

    @Nullable
    private WeakReference<View> mNoContentViewRef;

    private LoadingViewDelegate(
        @Nullable final View contentView,
        @Nullable final View loadingView,
        @Nullable final View noContentView,
        @Nullable final View errorView) {
        if (errorView == null) {
            mErrorViewRef = null;
        } else {
            mErrorViewRef = new WeakReference<>(errorView);
        }
        if (noContentView == null) {
            mNoContentViewRef = null;
        } else {
            mNoContentViewRef = new WeakReference<>(noContentView);
        }
        if (loadingView == null) {
            mLoadingViewRef = null;
        } else {
            mLoadingViewRef = new WeakReference<>(loadingView);
        }
        if (contentView == null) {
            mContentViewRef = null;
        } else {
            mContentViewRef = new WeakReference<>(contentView);
        }
    }

    private void setContentVisible(final boolean visible) {
        if (mContentViewRef != null) {
            mContentViewRef.get().setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    private void setErrorVisible(final boolean visible) {
        if (mErrorViewRef != null) {
            mErrorViewRef.get().setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    private void setLoadingVisible(final boolean visible) {
        if (mLoadingViewRef != null) {
            mLoadingViewRef.get().setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    private void setNoContentVisible(final boolean visible) {
        if (mNoContentViewRef != null) {
            mNoContentViewRef.get().setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public static class Builder {
        @NonNull
        public LoadingViewDelegate build() {
            return new LoadingViewDelegate(mNoContentView,
                                           mLoadingView,
                                           mNoContentView,
                                           mErrorView);
        }

        @NonNull
        public Builder setContentView(@Nullable final View contentView) {
            mContentView = contentView;

            return this;
        }

        @NonNull
        public Builder setErrorView(@Nullable final View errorView) {
            mErrorView = errorView;

            return this;
        }

        @NonNull
        public Builder setLoadingView(@Nullable final View loadingView) {
            mLoadingView = loadingView;

            return this;
        }

        @NonNull
        public Builder setNoContentView(@Nullable final View contentView) {
            mNoContentView = contentView;

            return this;
        }

        @Nullable
        private View mContentView;

        @Nullable
        private View mErrorView;

        @Nullable
        private View mLoadingView;

        @Nullable
        private View mNoContentView;
    }
}