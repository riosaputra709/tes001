package com.example.androideatit.callback;

import com.example.androideatit.Model.AppBestDealModel;

import java.util.List;

public interface IAppBestDealCallbackListener {
    void onBestDealLoadSuccess(List<AppBestDealModel> bestDealModels);
    void onBestDealLoadFailed(String message);
}
