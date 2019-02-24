package com.homework.search.data;

import com.homework.search.data.pojo.SearchResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApiService {
   @GET("image")
   Observable<SearchResponse> getSearchDataImage(@Query("query") String query,@Query("page") int page,@Query("sort") String sort,@Query("size") int size);
}
