package com.homework.search.channelsearchapp.util;

import com.google.gson.Gson;
import com.homework.search.data.SearchApiService;
import com.homework.search.data.pojo.SearchResponse;
import io.reactivex.Observable;
import retrofit2.mock.BehaviorDelegate;

public class MockSearchServiceApi implements SearchApiService {

   private final BehaviorDelegate<SearchApiService> delegate;

   public MockSearchServiceApi(BehaviorDelegate<SearchApiService> delegate) {
      this.delegate = delegate;
   }

   @Override
   public Observable<SearchResponse> getSearchDataImage(String query, int page, String sort,
       int size) {
      String testJson = null;
      try {
         testJson = JsonUtil.getStringFromFile("test_json.json");
      } catch (Exception e) {
         e.printStackTrace();
      }
      Gson gson = new Gson();
      return delegate.returningResponse(gson.fromJson(testJson, SearchResponse.class))
          .getSearchDataImage("1", 1, "", 1);
   }
}
