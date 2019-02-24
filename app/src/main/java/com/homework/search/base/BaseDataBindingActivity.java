package com.homework.search.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseDataBindingActivity<BINDING extends ViewDataBinding, VM extends BaseViewModel> extends
    AppCompatActivity {

   protected BINDING binding;
   protected VM viewModel;

   private CompositeDisposable compositeDisposable = new CompositeDisposable();

   public abstract @LayoutRes
   int getLayoutId();

   public abstract int getBindingVar();

   public abstract VM getViewModel();

   public BINDING getBinding() {
      return binding;
   }

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      AndroidInjection.inject(this);
      super.onCreate(savedInstanceState);
      performDataBinding();
   }

   private void performDataBinding() {
      binding = DataBindingUtil.setContentView(this, getLayoutId());
      this.viewModel = viewModel == null ? getViewModel() : viewModel;
      binding.setVariable(getBindingVar(), viewModel);
      binding.executePendingBindings();
      binding.setLifecycleOwner(this);
   }

   @Override
   protected void onDestroy() {
      compositeDisposable.clear();
      super.onDestroy();
   }

   public CompositeDisposable getCompositeDisposable() {
      return compositeDisposable;
   }

}
