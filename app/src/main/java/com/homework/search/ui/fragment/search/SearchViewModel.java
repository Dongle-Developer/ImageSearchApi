package com.homework.search.ui.fragment.search;

import static com.homework.search.ui.fragment.search.SearchViewModelEvent.END_DATA;
import static com.homework.search.ui.fragment.search.SearchViewModelEvent.END_SEARCH_DATA;
import static com.homework.search.ui.fragment.search.SearchViewModelEvent.ERROR_API;
import static com.homework.search.ui.fragment.search.SearchViewModelEvent.START_SEARCH_DATA;
import static com.homework.search.utils.DateUtils.StringToDate;
import static io.reactivex.subjects.PublishSubject.create;

import android.databinding.ObservableArrayList;
import com.homework.search.base.BaseViewModel;
import com.homework.search.data.SearchApiService;
import com.homework.search.data.ThumbnailItem;
import com.homework.search.data.pojo.ThumbnailResponse;
import com.homework.search.utils.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

public class SearchViewModel extends BaseViewModel {

	private final String QUERY_SORT = "recency";
	private final int QUERY_SIZE = 30;

	private ObservableArrayList<ThumbnailItem> thumbnailItems = new ObservableArrayList<>();

	private PublishSubject<SearchViewModelEvent> searchViewModelEventPublishSubject = create();

	private SearchApiService searchApiService;

	private int imagePageCount = 1;

	private String oldQuery;

	@Inject
	public SearchViewModel(SchedulerProvider androidSchedulers, SearchApiService searchApiService) {
		super(androidSchedulers);
		this.searchApiService = searchApiService;
	}

	public void NewSearchDate(String queryStr) {
		//SameQuery Check
		if(isSameQuery(queryStr)) return;

		//Reset Value
		setIsLoading(true);
		thumbnailItems.clear();
		oldQuery = queryStr;
		imagePageCount = 1;

		searchData(getImageResponse());
	}

	private boolean isSameQuery(String query) {
		return (oldQuery != null && !oldQuery.isEmpty() && oldQuery.equals(query));
	}

	public void loadNextData() {
		setIsLoading(true);
		if (imagePageCount >= 1) {
			searchData(getImageResponse());
		} else  {
			setIsLoading(false);
			searchViewModelEventPublishSubject.onNext(END_DATA);
		}
	}

	private void searchData(Observable<List<ThumbnailResponse>> observable) {
		observable
						.map(thumbnailResponses -> thumbnailResponses)
						.flatMapIterable(thumbnailResponse -> thumbnailResponse)
						.subscribeOn(getSchedulerProvider().io())
						.observeOn(getSchedulerProvider().ui())
						.subscribe(responseObserver());
	}

	public Observer<ThumbnailResponse> responseObserver() {
		return new Observer<ThumbnailResponse>() {
			@Override
			public void onSubscribe(Disposable d) {
				getCompositeDisposable().add(d);
				searchViewModelEventPublishSubject.onNext(START_SEARCH_DATA);
			}

			@Override
			public void onNext(ThumbnailResponse thumbnailResponse) {
				try {
					addThumbnailData(thumbnailResponse);
				} catch (ParseException e) {
					onError(e.fillInStackTrace());
				}
			}

			@Override
			public void onError(Throwable throwable) {
				searchViewModelEventPublishSubject.onNext(ERROR_API);
				throwable.printStackTrace();
				setIsLoading(false);
			}

			@Override
			public void onComplete() {
				if (thumbnailItems.size() == 0) {
					searchViewModelEventPublishSubject.onNext(END_DATA);
				} else {
					++imagePageCount;
					searchViewModelEventPublishSubject.onNext(END_SEARCH_DATA);
				}
				setIsLoading(false);
			}
		};
	}

	private Observable<List<ThumbnailResponse>> getImageResponse() {
		return searchApiService
						.getSearchDataImage(oldQuery, imagePageCount, QUERY_SORT, QUERY_SIZE)
						.map(searchResponse -> {
							if (searchResponse.getMeta().isIsEnd()) {
								imagePageCount = -1;
							}
							return searchResponse.getDocuments();
						})
						.subscribeOn(getSchedulerProvider().io());
	}

	private void addThumbnailData(ThumbnailResponse thumbnailResponse) throws ParseException {
		thumbnailItems.add(new ThumbnailItem(StringToDate(thumbnailResponse.getDatetime()),
						thumbnailResponse.getThumbnailUrl()));
	}

	public PublishSubject<SearchViewModelEvent> getSearchViewModelEventPublishSubject() {
		return searchViewModelEventPublishSubject;
	}

	public ObservableArrayList<ThumbnailItem> getThumbnailItems() {
		return thumbnailItems;
	}

}
