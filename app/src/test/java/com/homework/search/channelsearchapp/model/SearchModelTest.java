package com.homework.search.channelsearchapp.model;

import static com.homework.search.channelsearchapp.util.JsonUtil.getStringFromFile;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.homework.search.channelsearchapp.util.EmptyMockSearchServiceApi;
import com.homework.search.channelsearchapp.util.MockSearchServiceApi;
import com.homework.search.channelsearchapp.util.TestSchedulerProvider;
import com.homework.search.data.SearchApiService;
import com.homework.search.ui.fragment.search.SearchViewModel;
import com.homework.search.ui.fragment.search.SearchViewModelEvent;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

@RunWith(MockitoJUnitRunner.class)
public class SearchModelTest {

   private SearchApiService searchApiService;

   private Retrofit retrofit;

   private TestScheduler testScheduler;

   private MockRetrofit mockRetrofit;

   private SearchViewModel searchViewModel;

   private TestObserver<SearchViewModelEvent> eventTestObserver;

   @Before
   public void setUp() {

      testScheduler = new TestScheduler();

      retrofit = new Retrofit.Builder().baseUrl("http://test.com")
          .client(new OkHttpClient())
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build();

      mockRetrofit = new MockRetrofit.Builder(retrofit).networkBehavior(NetworkBehavior.create())
          .build();
      set_test_json();
   }

   private void set_test_json() {
      BehaviorDelegate<SearchApiService> apiBehaviorDelegate = mockRetrofit
          .create(SearchApiService.class);
      searchApiService = new MockSearchServiceApi(apiBehaviorDelegate);
      searchViewModel = new SearchViewModel(new TestSchedulerProvider(testScheduler),
          searchApiService);
      eventTestObserver = searchViewModel.getSearchViewModelEventPublishSubject().test();
   }

   private void set_test_json_empty() {
      BehaviorDelegate<SearchApiService> apiBehaviorDelegate = mockRetrofit
          .create(SearchApiService.class);
      searchApiService = new EmptyMockSearchServiceApi(apiBehaviorDelegate);
      searchViewModel = new SearchViewModel(new TestSchedulerProvider(testScheduler),
          searchApiService);
      eventTestObserver = searchViewModel.getSearchViewModelEventPublishSubject().test();
   }

   @Test
   public void readTestJsonFile() throws Exception {
      String json = getStringFromFile("test_json.json");
      assertThat(json, notNullValue());
   }

   @Test
   public void readTestJsonEmptyFile() throws Exception {
      String json = getStringFromFile("test_json_empty.json");
      assertThat(json, notNullValue());
   }

   @Test
   public void searchApiService_Success_SearchViewModel_Event() {
      set_test_json();
      searchViewModel.NewSearchDate("Test");
      eventTestObserver.assertValueAt(0, SearchViewModelEvent.START_SEARCH_DATA);
      eventTestObserver.assertValueAt(1, SearchViewModelEvent.END_SEARCH_DATA);
   }

   @Test
   public void searchApiService_Empty_SearchViewModel_Event() {
      set_test_json_empty();
      searchViewModel.NewSearchDate("Test");
      eventTestObserver.assertValueAt(0, SearchViewModelEvent.START_SEARCH_DATA);
      eventTestObserver.assertValueAt(1, SearchViewModelEvent.END_DATA);
   }

   @Test
   public void searchApiService_Success_SearchViewModel_ThumbnailItem_Count() {
      set_test_json();
      searchViewModel.NewSearchDate("Test");
      assertTrue(searchViewModel.getThumbnailItems().size() > 0);
   }

   @Test
   public void searchApiService_Empty_SearchViewModel__ThumbnailItem_Count() {
      set_test_json_empty();
      searchViewModel.NewSearchDate("Test");
      assertTrue(searchViewModel.getThumbnailItems().size() == 0);
   }

}
