package com.homework.search.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseDataBindingViewHolder<BINDING extends ViewDataBinding, ITEM> extends RecyclerView.ViewHolder {

    protected Context context;
    protected BINDING binding;

    public BaseDataBindingViewHolder(ViewGroup parent, View itemView) {
        super(itemView);
        context = parent.getContext();
        binding = DataBindingUtil.bind(itemView);
    }

    public abstract void onBindViewHolder(ITEM item, int position);
}