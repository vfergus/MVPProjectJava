package com.vfergus.facebookclient.presenter.userPosts;

import android.support.annotation.NonNull;
import android.util.Log;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import com.vfergus.facebookclient.R;
import com.vfergus.facebookclient.api.model.Post;
import com.vfergus.facebookclient.api.model.entity.DataResponseEntity;
import com.vfergus.facebookclient.core.manager.account.AccountHelper;
import com.vfergus.facebookclient.core.manager.api.ApiManager;
import com.vfergus.facebookclient.core.manager.message.MessageManager;
import com.vfergus.facebookclient.core.manager.navigation.NavigationManager;
import com.vfergus.facebookclient.core.manager.rx.RxManager;
import com.vfergus.facebookclient.di.qualifier.ScopeNames;
import com.vfergus.facebookclient.presenter.base.BasePresenter;
import com.vfergus.facebookclient.view.PostsScreen;

import java.util.List;

import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

@Accessors(prefix = "m")
public class PostsPresenterImpl extends BasePresenter<PostsScreen> implements PostsPresenter {

    private static final String LOG_TAG = PostsPresenterImpl.class.getSimpleName();

    public PostsPresenterImpl(
        @NonNull final AccountHelper accountHelper,
        @NonNull final NavigationManager navigationManager,
        @NonNull final ApiManager apiManager,
        @NonNull final MessageManager messageManager,
        @NonNull @Named(ScopeNames.ACTIVITY) final RxManager rxManager) {

        mAccountHelper = accountHelper;
        mNavigationManager = navigationManager;
        mApiManager = apiManager;
        mMessageManager = messageManager;
        mRxManager = rxManager;
    }

    @Override
    public void loadPosts() {
        displayLoading();

        val api = mApiManager.getApi();
        mDisposables.add(mAccountHelper
                             .withToken(new Function<String, Observable<DataResponseEntity>>() {
                                 @Override
                                 public Observable<DataResponseEntity> apply(final String token)
                                     throws Exception {
                                     return mRxManager.autoManage(api.getUserPosts(token));
                                 }
                             })
                             .subscribeOn(mRxManager.getIOScheduler())
                             .observeOn(mRxManager.getUIScheduler())
                             .subscribeWith(new DisposableObserver<DataResponseEntity>() {
                                 @Override
                                 public void onNext(final DataResponseEntity response) {
                                     val data =
                                         (List<Post>) (List<? extends Post>) response.getData();
                                     if (data != null && !data.isEmpty()) {
                                         displayPosts(data);
                                     } else {
                                         displayEmptyResult();
                                     }
                                 }

                                 @Override
                                 public void onError(final Throwable e) {
                                     Log.w(LOG_TAG, "call:  = " + e);
                                     mMessageManager.showErrorMessage(R.string
                                                                          .message_error_unknown);

                                     displayEmptyResult();
                                 }

                                 @Override
                                 public void onComplete() {

                                 }
                             }));
    }

    @Override
    protected void onScreenDetach() {
        super.onScreenDetach();
        if (!mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final AccountHelper mAccountHelper;

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final ApiManager mApiManager;

    @NonNull
    private final CompositeDisposable mDisposables = new CompositeDisposable();

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final MessageManager mMessageManager;

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final NavigationManager mNavigationManager;

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final RxManager mRxManager;

    private void displayEmptyResult() {
        val screen = getScreen();
        if (screen != null) {
            screen.displayEmptyResult();
        }
    }

    private void displayLoading() {
        val screen = getScreen();
        if (screen != null) {
            screen.displayLoading();
        }
    }

    private void displayPosts(@NonNull final List<Post> posts) {
        val screen = getScreen();
        if (screen != null) {
            screen.displayPosts(posts);
        }
    }
}
