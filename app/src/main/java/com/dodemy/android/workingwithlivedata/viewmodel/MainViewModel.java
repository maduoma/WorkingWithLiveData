package com.dodemy.android.workingwithlivedata.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dodemy.android.workingwithlivedata.model.Blog;
import com.dodemy.android.workingwithlivedata.model.BlogRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private BlogRepository movieRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new BlogRepository(application);
    }

    public LiveData<List<Blog>> getAllBlog() {
        return movieRepository.getMutableLiveData();
    }


}
