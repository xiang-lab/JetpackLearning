package com.xiang.booksactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

public class BooksActivity extends AppCompatActivity {

    private BooksViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_act);

        mViewModel = obtainViewModel(this); // 创建常用套路

    }

    public static BooksViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        // ViewModelProviders.of(<Your UI controller>).get(<Your ViewModel>.class)
        BooksViewModel viewModel =
                ViewModelProviders.of(activity, factory).get(BooksViewModel.class);

        return viewModel;
    }
}
