package com.homework.search.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.homework.search.di.ViewModelFactory;
import com.homework.search.di.ViewModelKey;
import com.homework.search.ui.fragment.search.SearchViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import javax.inject.Singleton;

@Module
public abstract class ViewModelModule {

   @Binds
   @IntoMap
   @Singleton
   @ViewModelKey(SearchViewModel.class)
   abstract ViewModel bindProductListViewModel(SearchViewModel searchViewModel);

   @Binds
   abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
