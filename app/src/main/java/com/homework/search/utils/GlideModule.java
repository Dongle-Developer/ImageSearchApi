package com.homework.search.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

@com.bumptech.glide.annotation.GlideModule
public class GlideModule extends AppGlideModule {

   private final int MEMORY_CACHE_SIZE = (int) ((Runtime.getRuntime().maxMemory() / 1024) / 8);
   private final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB

   @Override
   public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
      super.applyOptions(context, builder);
      builder
          .setDiskCache(
              new ExternalPreferredCacheDiskCacheFactory(context, "cache", DISK_CACHE_SIZE))
          .setMemoryCache(new LruResourceCache(MEMORY_CACHE_SIZE));
   }
}
