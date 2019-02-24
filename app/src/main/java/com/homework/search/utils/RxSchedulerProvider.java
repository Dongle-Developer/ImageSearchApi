package com.homework.search.utils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulerProvider implements SchedulerProvider {


   @Override
   public Scheduler computation() {
      return Schedulers.computation();
   }

   @Override
   public Scheduler io() {
      return Schedulers.io();
   }

   @Override
   public Scheduler ui() { return AndroidSchedulers.mainThread(); }

   @Override
   public Scheduler newTScheduler() { return Schedulers.newThread();}

}
