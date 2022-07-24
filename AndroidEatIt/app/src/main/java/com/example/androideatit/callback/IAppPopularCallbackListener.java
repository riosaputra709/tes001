package com.example.androideatit.callback;

import com.example.androideatit.Model.AppPopularCategoryModel;

import java.util.List;

public interface IAppPopularCallbackListener {
    void onPopularLoadSuccess(List<AppPopularCategoryModel> popularCategoyModels);
    void onPopularLoadFailed(String message);
}
