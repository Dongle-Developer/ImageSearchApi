package com.homework.search.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.homework.search.BR;
import com.homework.search.R;
import com.homework.search.base.BaseDataBindingActivity;
import com.homework.search.databinding.ActivityMainBinding;
import com.homework.search.ui.fragment.search.SearchViewModel;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

public class MainActivity extends
    BaseDataBindingActivity<ActivityMainBinding, SearchViewModel> implements
    HasSupportFragmentInjector {

   @Inject
   DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

   @Override
   public int getLayoutId() {
      return R.layout.activity_main;
   }

   @Override
   public int getBindingVar() {
      return BR.searchViewModel;
   }

   @Override
   public SearchViewModel getViewModel() {
      return null;
   }

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }

   @Override
   public AndroidInjector<Fragment> supportFragmentInjector() {
      return fragmentDispatchingAndroidInjector;
   }

}
