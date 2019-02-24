package com.homework.search.utils;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

   Scheduler computation();

   Scheduler io();

   Scheduler ui();

   Scheduler newTScheduler();
}
