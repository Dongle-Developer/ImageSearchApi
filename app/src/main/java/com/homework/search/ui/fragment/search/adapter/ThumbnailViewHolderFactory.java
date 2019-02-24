package com.homework.search.ui.fragment.search.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.homework.search.R;

public class ThumbnailViewHolderFactory {

   ThumbnailViewHolder ThumbnailViewHolderFactory(ViewGroup parent) {
      return new ThumbnailViewHolder(parent, LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_thumbnail_layout, parent, false));
   }

}
