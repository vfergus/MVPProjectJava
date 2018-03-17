package com.vfergus.twitterclient.di.application.module;

import android.support.annotation.NonNull;

import lombok.val;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vfergus.common.utils.LocaleUtils;
import com.vfergus.twitterclient.api.TwitterApi;
import com.vfergus.twitterclient.api.TwitterConstants;
import com.vfergus.twitterclient.di.application.ApplicationScope;

import java.io.IOException;
import java.util.Locale;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@ApplicationScope
public class ApiApplicationModule {

    public ApiApplicationModule(
        final boolean enableLogging) {

        mEnableLogging = enableLogging;
    }

    @Provides
    @ApplicationScope
    @NonNull
    public final Gson provideApiParser() {
        return new GsonBuilder().create();
    }

    @Provides
    @ApplicationScope
    @NonNull
    public final TwitterApi providePetSquareService(
        @NonNull final Retrofit retrofit) {

        return retrofit.create(TwitterApi.class);
    }

    @Provides
    @ApplicationScope
    @NonNull
    public final Retrofit provideRetrofit(@NonNull final Gson gson) {
        val retrofitBuilder = new Retrofit.Builder();
        val httpClientBuild = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(final Chain chain)
                throws IOException {
                val original = chain.request();
                val languageTag = LocaleUtils.getLanguageTag(Locale.getDefault());
                val requestBuilder = original.newBuilder();
                if (languageTag != null) {
                    requestBuilder.header("Accept-Language", languageTag);
                }
                requestBuilder.method(original.method(), original.body());

                return chain.proceed(requestBuilder.build());
            }
        });
        if (mEnableLogging) {
            httpClientBuild.addInterceptor(new HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY));
        }
        retrofitBuilder.client(httpClientBuild.build());
        return retrofitBuilder
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(TwitterConstants.BASE_URL)
            .build();
    }

    private final boolean mEnableLogging;
}
