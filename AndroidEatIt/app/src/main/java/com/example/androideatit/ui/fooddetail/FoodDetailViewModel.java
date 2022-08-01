package com.example.androideatit.ui.fooddetail;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androideatit.Common.Common;
import com.example.androideatit.Model.FoodModel;

import java.util.List;

public class FoodDetailViewModel extends ViewModel {


    private MutableLiveData<FoodModel> mutableLiveDataFood;


    public FoodDetailViewModel() {

    }

    public MutableLiveData<FoodModel> getMutableLiveDataFood() {
        if (mutableLiveDataFood == null)
            mutableLiveDataFood = new MutableLiveData<>();
        mutableLiveDataFood.setValue(Common.selectedFood);
        return mutableLiveDataFood;
    }

}