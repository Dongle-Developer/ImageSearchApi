package com.homework.search.ui.fragment.search;

import android.support.v7.widget.StaggeredGridLayoutManager;
import com.homework.search.ui.fragment.search.adapter.ThumbnailViewHolderFactory;
import com.homework.search.ui.fragment.search.adapter.ThumbnailViewListAdapter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.PublishSubject;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

@Module
public class SearchFragmentModule {

   @Provides
   ThumbnailViewHolderFactory provideThumbnailViewHolderFactory() {
      return new ThumbnailViewHolderFactory();
   }

   @Provides
   ThumbnailViewListAdapter provideThumbnailViewListAdapter(
       ThumbnailViewHolderFactory thumbnailViewHolderFactory) {
      return new ThumbnailViewListAdapter(thumbnailViewHolderFactory);
   }

   @Provides
   StaggeredGridLayoutManager provideStaggeredGridLayoutManager() {
      return new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
   }

   @Provides
   Scheduler provideSchedulerAndroidThread(){
      return AndroidSchedulers.mainThread();
   }

}
