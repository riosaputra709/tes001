package com.example.androideatit.ui.comments;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androideatit.Model.CommentModel;

import java.util.List;

public class CommentViewModel extends ViewModel {
    private MutableLiveData<List<CommentModel>> mutableLiveDataMateriList;

    public CommentViewModel() {
        mutableLiveDataMateriList = new MutableLiveData<>();
    }

    public MutableLiveData<List<CommentModel>> getMutableLiveDataMateriList() {
        return mutableLiveDataMateriList;
    }

    public void setCommentList(List<CommentModel> commentList)
    {
        mutableLiveDataMateriList.setValue(commentList);
    }

}
