package com.xiang.booksactivity;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * A creator is used to inject the product ID into the ViewModel
 * <p>
 * This creator is to showcase how to inject dependencies into ViewModels. It's not
 * actually necessary in this case, as the product ID can be passed in a public method.
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final BooksRepository mBooksRepository;

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(
                            Injection.provideTasksRepository(application.getApplicationContext()));
                }
            }
        }
        return INSTANCE;
    }

    public BooksRepository getBooksRepository() {
        return mBooksRepository;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(BooksRepository repository) {
        mBooksRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StatisticsViewModel.class)) {

        } else if (modelClass.isAssignableFrom(TaskDetailViewModel.class)) {

        } else if (modelClass.isAssignableFrom(AddEditTaskViewModel.class)) {

        } else if (modelClass.isAssignableFrom(BooksViewModel.class)) {
            // oninspection unchecked
            return (T) new BooksViewModel(mBooksRepository); // 传入repository仓库的属性
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}











