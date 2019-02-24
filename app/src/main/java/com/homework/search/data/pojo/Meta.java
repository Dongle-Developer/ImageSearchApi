package com.homework.search.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("is_end")
    @Expose
    private boolean isEnd;
    @SerializedName("pageable_count")
    @Expose
    private int pageableCount;
    @SerializedName("total_count")
    @Expose
    private int totalCount;

    public boolean isIsEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public int getPageableCount() {
        return pageableCount;
    }

    public void setPageableCount(int pageableCount) {
        this.pageableCount = pageableCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "isEnd=" + isEnd +
                ", pageableCount=" + pageableCount +
                ", totalCount=" + totalCount +
                '}';
    }
}
