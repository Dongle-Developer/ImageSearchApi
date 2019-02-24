package com.homework.search.di.component;

import android.app.Application;
import com.homework.search.di.App;
import com.homework.search.di.builder.ActivityBuilder;
import com.homework.search.di.module.ApiModule;
import com.homework.search.di.module.AppModule;
import com.homework.search.di.module.ViewModelModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
    AndroidSupportInjectionModule.class,
    ApiModule.class,
    AppModule.class,
    ViewModelModule.class,
    ActivityBuilder.class
})
public interface AppComponent {

   void inject(App app);

   @Component.Builder
   interface Builder {

      @BindsInstance
      AppComponent.Builder application(Application application);

      AppComponent build();
   }

}
