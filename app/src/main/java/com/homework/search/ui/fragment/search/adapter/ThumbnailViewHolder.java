package com.homework.search.ui.fragment.search.adapter;

import android.view.View;
import android.view.ViewGroup;
import com.homework.search.base.BaseDataBindingViewHolder;
import com.homework.search.data.ThumbnailItem;
import com.homework.search.databinding.ItemThumbnailLayoutBinding;

public class ThumbnailViewHolder extends
	BaseDataBindingViewHolder<ItemThumbnailLayoutBinding, ThumbnailItem> {

	ThumbnailViewHolder(ViewGroup parent, View itemView) {
		super(parent, itemView);
	}

	@Override
	public void onBindViewHolder(ThumbnailItem thumbnailItem, int position) {
		binding.setThumbnailItem(thumbnailItem);
	}

}
