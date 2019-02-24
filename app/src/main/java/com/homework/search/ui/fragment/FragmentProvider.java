package com.homework.search.ui.fragment;

import com.homework.search.ui.fragment.search.SearchFragment;
import com.homework.search.ui.fragment.search.SearchFragmentModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentProvider {

    @ContributesAndroidInjector(modules = {SearchFragmentModule.class})
    abstract SearchFragment provideSearchFragment();
}
