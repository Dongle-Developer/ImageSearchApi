package com.homework.search.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseDataBindingFragment<BINDING extends ViewDataBinding, VM extends BaseViewModel> extends
    Fragment {

   protected BINDING binding;
   protected VM viewModel;

   private BaseDataBindingActivity activity;
   protected View rootView;

   private CompositeDisposable compositeDisposable = new CompositeDisposable();

   public abstract @LayoutRes
   int getLayoutId();

   public abstract int getBindingVar();

   public abstract VM getViewModel();

   @Override
   public void onAttach(Context context) {
      super.onAttach(context);
      activity = (BaseDataBindingActivity) context;
      activity.onAttachFragment(this);
   }

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      AndroidSupportInjection.inject(this);
      super.onCreate(savedInstanceState);
   }

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
       Bundle savedInstanceState) {
      binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
      rootView = binding.getRoot();
      return rootView;
   }

   @Override
   public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      binding.executePendingBindings();
      binding.setLifecycleOwner(this);
   }

   @Override
   public void onDetach() {
      activity = null;
      compositeDisposable.clear();
      super.onDetach();
   }

   public BaseDataBindingActivity getParentActivity() {
      return activity;
   }

   public CompositeDisposable getCompositeDisposable() {
      return compositeDisposable;
   }

}
