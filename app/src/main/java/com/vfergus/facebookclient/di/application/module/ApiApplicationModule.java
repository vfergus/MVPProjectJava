package com.vfergus.facebookclient.di.application.module;

import android.support.annotation.NonNull;

import lombok.val;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vfergus.facebookclient.api.FacebookClientApi;
import com.vfergus.facebookclient.api.FacebookClientConstants;
import com.vfergus.facebookclient.di.application.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
    public final FacebookClientApi providePetSquareService(
        @NonNull final Retrofit retrofit) {

        return retrofit.create(FacebookClientApi.class);
    }

    @Provides
    @ApplicationScope
    @NonNull
    public final Retrofit provideRetrofit(@NonNull final Gson gson) {
        val retrofitBuilder = new Retrofit.Builder();
        val httpClientBuild = new OkHttpClient.Builder();
        if (mEnableLogging) {
            httpClientBuild.addInterceptor(new HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY));
        }
        retrofitBuilder.client(httpClientBuild.build());
        return retrofitBuilder
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(FacebookClientConstants.BASE_URL)
            .build();
    }

    private final boolean mEnableLogging;
}
