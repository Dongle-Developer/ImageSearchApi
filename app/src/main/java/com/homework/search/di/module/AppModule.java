package com.homework.search.di.module;

import android.app.Application;
import android.content.Context;
import com.homework.search.utils.RxSchedulerProvider;
import com.homework.search.utils.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerProvider provideRxSchedulerProvider() { return new RxSchedulerProvider();
    }

}
