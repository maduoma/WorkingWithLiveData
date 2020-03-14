package com.dodemy.android.workingwithlivedata.ui;


import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dodemy.android.workingwithlivedata.R;
import com.dodemy.android.workingwithlivedata.model.Blog;
import com.dodemy.android.workingwithlivedata.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeRefresh;
    private MainViewModel mainViewModel;

    BlogAdapter mBlogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializationViews();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class); //Use
        //mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);//Deprecated
        getPopularBlog();
        // lambda expression
        swipeRefresh.setOnRefreshListener(() -> {
            getPopularBlog();
        });
    }

    private void initializationViews() {
        swipeRefresh = findViewById(R.id.swiperefresh);
        mRecyclerView = findViewById(R.id.blogRecyclerView);
    }

    public void getPopularBlog() {
        swipeRefresh.setRefreshing(true);
        mainViewModel.getAllBlog().observe(this, blogList -> {
            swipeRefresh.setRefreshing(false);
            prepareRecyclerView(blogList);
        });
        /**
         * Replace this statement with lambda expression
         * For using set you have to set following lines in app/build.gradle
             // add below line
             compileOptions {
             sourceCompatibility JavaVersion.VERSION_1_8
             targetCompatibility JavaVersion.VERSION_1_8
             }
             // reduce line of code
             mainViewModel.getAllBlog().observe(this, blogList -> prepareRecyclerView(blogList));

         */

    }

    private void prepareRecyclerView(List<Blog> blogList) {

        mBlogAdapter = new BlogAdapter(blogList);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mBlogAdapter);
        mBlogAdapter.notifyDataSetChanged();

    }

}
