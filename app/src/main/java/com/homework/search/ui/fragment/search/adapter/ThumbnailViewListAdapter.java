package com.homework.search.ui.fragment.search.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import com.homework.search.base.BaseRecyclerViewAdapter;
import com.homework.search.data.ThumbnailItem;

public class ThumbnailViewListAdapter extends
    BaseRecyclerViewAdapter<ThumbnailItem, ThumbnailViewHolder> {

   private ThumbnailViewHolderFactory thumbnailViewHolderFactory;

   public ThumbnailViewListAdapter(
       ThumbnailViewHolderFactory thumbnailViewHolderFactory) {
      this.thumbnailViewHolderFactory = thumbnailViewHolderFactory;
   }

   @NonNull
   @Override
   public ThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return thumbnailViewHolderFactory.ThumbnailViewHolderFactory(parent);
   }

   @Override
   public void onBindViewHolder(@NonNull ThumbnailViewHolder holder, int position) {
      holder.onBindViewHolder(getItem(position), position);
   }
}
