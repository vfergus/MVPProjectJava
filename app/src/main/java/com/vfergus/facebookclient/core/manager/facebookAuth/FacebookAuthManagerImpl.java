package com.vfergus.facebookclient.core.manager.facebookAuth;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.vfergus.common.event.eventArgs.ActivityResultEventArgs;
import com.vfergus.common.event.generic.EventHandler;
import com.vfergus.common.utils.tuple.Tuple2;
import com.vfergus.common.utils.tuple.Tuples;
import com.vfergus.common.view.activity.ObservableActivity;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

@Accessors(prefix = "m")
public class FacebookAuthManagerImpl implements FacebookAuthManager {

    public FacebookAuthManagerImpl(
        @NonNull final CallbackManager callbackManager,
        @NonNull final LoginManager loginManager,
        @NonNull final ObservableActivity observableActivity) {
        mLoginManager = loginManager;
        mCallbackManager = callbackManager;
        mObservableActivity = observableActivity;
        mObservableActivity.getActivityResultEvent().addHandler(mActivityResultHandler);
        mLoginManager.registerCallback(mCallbackManager, mFacebookResult);
    }

    @NonNull
    @Override
    public Observable<Tuple2<String, String>> getAccessToken() {
        return Observable.create(new ObservableOnSubscribe<Tuple2<String, String>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Tuple2<String, String>> emitter)
                throws Exception {
                mEmitter = emitter;
                getLoginManager().logInWithReadPermissions(mObservableActivity.asActivity(),
                                                           Arrays.asList("public_profile",
                                                                         "user_posts",
                                                                         "user_photos"));
            }
        });
    }

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final CallbackManager mCallbackManager;

    @NonNull
    private final EventHandler<ActivityResultEventArgs> mActivityResultHandler =
        new EventHandler<ActivityResultEventArgs>() {
            @Override
            public void onEvent(@NonNull final ActivityResultEventArgs eventArgs) {
                val requestCode = eventArgs.getRequestCode();
                val resultCode = eventArgs.getResultCode();
                val data = eventArgs.getData();

                mCallbackManager.onActivityResult(requestCode, resultCode, data);
            }
        };

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final LoginManager mLoginManager;

    @NonNull
    private final ObservableActivity mObservableActivity;

    @Nullable
    private ObservableEmitter<Tuple2<String, String>> mEmitter;

    @NonNull
    private final FacebookCallback<LoginResult> mFacebookResult =
        new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                val token = loginResult.getAccessToken();
                val currentProfile = Profile.getCurrentProfile();
                val name = currentProfile == null ? "user" : currentProfile.getName();
                if (mEmitter != null) {
                    mEmitter.onNext(Tuples.from(name, token.getToken()));
                    mEmitter.onComplete();
                }
            }

            @Override
            public void onCancel() {
                if (mEmitter != null) {
                    mEmitter.onComplete();
                }
            }

            @Override
            public void onError(final FacebookException exception) {
                if (mEmitter != null) {
                    mEmitter.onError(exception);
                }
            }
        };
}
