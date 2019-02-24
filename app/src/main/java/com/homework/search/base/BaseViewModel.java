package com.homework.search.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import com.homework.search.utils.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

   private final SchedulerProvider schedulerProvider;

   private final ObservableBoolean isLoading = new ObservableBoolean(false);

   private CompositeDisposable compositeDisposable;

   public BaseViewModel(SchedulerProvider schedulerProvider) {
      this.schedulerProvider = schedulerProvider;
      this.compositeDisposable = new CompositeDisposable();
   }

   protected SchedulerProvider getSchedulerProvider() {
      return schedulerProvider;
   }

   public CompositeDisposable getCompositeDisposable() {
      return compositeDisposable;
   }

   public ObservableBoolean getIsLoading() {
      return isLoading;
   }

   public void setIsLoading(boolean loading) {
      isLoading.set(loading);
   }

   @Override
   protected void onCleared() {
      compositeDisposable.clear();
      super.onCleared();
   }

}
