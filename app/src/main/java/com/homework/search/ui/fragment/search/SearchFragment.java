package com.homework.search.ui.fragment.search;

import static android.support.design.widget.Snackbar.make;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import com.homework.search.BR;
import com.homework.search.R;
import com.homework.search.base.BaseDataBindingFragment;
import com.homework.search.databinding.FragmentSearchBinding;
import com.homework.search.di.ViewModelFactory;
import com.homework.search.ui.fragment.search.adapter.ThumbnailViewListAdapter;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Scheduler;

public class SearchFragment extends
    BaseDataBindingFragment<FragmentSearchBinding, SearchViewModel> {

   @Inject
   StaggeredGridLayoutManager staggeredGridLayoutManager;

   @Inject
   ThumbnailViewListAdapter thumbnailViewListAdapter;

   @Inject
   ViewModelFactory viewModelFactory;

   @Inject
   Scheduler schedulerAndroidThread;

   SearchView searchView;

   @Override
   public int getLayoutId() {
      return R.layout.fragment_search;
   }

   @Override
   public int getBindingVar() {
      return BR.searchViewModel;
   }

   @Override
   public SearchViewModel getViewModel() {
      return viewModel;
   }

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setHasOptionsMenu(true);
      viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);
      getCompositeDisposable().add(
          viewModel.getSearchViewModelEventPublishSubject().subscribe(this::showEventSnackBar));
   }

   @Override
   public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      binding.setSearchViewModel(viewModel);
      iniThumbnailListView();
      recyclerViewPageInit();
   }

   @Override
   public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
      inflater.inflate(R.menu.search_bar_menu, menu);
      searchView = (SearchView) menu.findItem(R.id.menu_bar_search).getActionView();
      searchViewInit();
   }

   private void iniThumbnailListView() {
      binding.rvSearchContents.setLayoutManager(staggeredGridLayoutManager);
      binding.rvSearchContents.setHasFixedSize(true);
      binding.rvSearchContents.setAdapter(thumbnailViewListAdapter);
   }

   private void searchViewInit() {
      searchView.onActionViewExpanded();
      searchView.setFocusableInTouchMode(true);
      searchView.setFocusable(true);
      searchView.clearFocus();
      RxSearchView
          .queryTextChangeEvents(searchView)
          .debounce(1000, TimeUnit.MILLISECONDS)
          .filter(searchViewQueryTextEvent ->
              !searchViewQueryTextEvent.queryText().toString().isEmpty())
          .distinctUntilChanged()
          .observeOn(schedulerAndroidThread)
          .subscribe(searchViewQueryTextEvent -> {
             viewModel.NewSearchDate(searchViewQueryTextEvent.queryText().toString());
             searchView.clearFocus();
          });
   }

   private void showEventSnackBar(SearchViewModelEvent searchViewModelEvent) {
      switch (searchViewModelEvent) {
         case ERROR_API:
            make(rootView, getString(R.string.api_error_message), Snackbar.LENGTH_LONG).show();
            binding.progress.setVisibility(View.GONE);
            break;
         case END_DATA:
            make(rootView, getString(R.string.no_more_searching_message), Snackbar.LENGTH_LONG)
                .show();
            binding.progress.setVisibility(View.GONE);
            break;
         case START_SEARCH_DATA:
            binding.progress.setVisibility(View.VISIBLE);
            break;
         case END_SEARCH_DATA:
            binding.progress.setVisibility(View.GONE);
            break;
      }
   }

   private void recyclerViewPageInit() {
      binding.rvSearchContents.addOnScrollListener(new OnScrollListener() {
         @Override
         public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
         }

         @Override
         public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) binding.rvSearchContents
                .getLayoutManager()).findLastCompletelyVisibleItemPositions(null);

            int lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);

            if (lastVisibleItemPosition >= viewModel.getThumbnailItems().size() - 1) {
               if (!viewModel.getIsLoading().get()) {
                  viewModel.loadNextData();
               }
            }
         }
      });
   }

   public int getLastVisibleItem(int[] lastVisibleItemPositions) {
      int maxSize = 0;
      for (int i = 0; i < lastVisibleItemPositions.length; i++) {
         if (i == 0) {
            maxSize = lastVisibleItemPositions[i];
         } else if (lastVisibleItemPositions[i] > maxSize) {
            maxSize = lastVisibleItemPositions[i];
         }
      }
      return maxSize;
   }

   @Override
   public void onDestroyView() {
      thumbnailViewListAdapter.onDestroyView();
      super.onDestroyView();
   }

}
