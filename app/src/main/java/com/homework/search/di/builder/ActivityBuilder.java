package com.homework.search.di.builder;

import com.homework.search.ui.MainActivity;
import com.homework.search.ui.fragment.FragmentProvider;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

   @ContributesAndroidInjector(modules = {FragmentProvider.class})
   abstract MainActivity bindMainActivity();
}
