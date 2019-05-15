package com.xiang.booksactivity;

import android.view.View;

import java.util.List;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class BooksViewModel extends ViewModel {

    private final MutableLiveData<List<Book>> mItems = new MutableLiveData<>();

    private final MutableLiveData<Boolean> mDataLoading = new MutableLiveData<>();

    private final MutableLiveData<Boolean> mBooksAddViewVisable = new MutableLiveData<>();

    private final MutableLiveData<Event<Integer>> mSnackbarText = new MutableLiveData<>();

    private final BooksRepository mBooksRepository;

    // This LiveData depends on another so we can use a transformation
    public final LiveData<Boolean> empty = Transformations.map(mItems,
            new Function<List<Book>, Boolean>() {
                @Override
                public Boolean apply(List<Book> input) {
                    return input.isEmpty();
                }
            });

    public BooksViewModel(BooksRepository repository) {
        mBooksRepository = repository;
    }

    public void start() {
        loadBooks(false);
    }

    public void loadBooks(boolean forceUpdate) {
        loadBooks(forceUpdate, true);
    }

    // LiveData getters
    // 未完待续







    /**
     * @param forceUpdate   Pass in true to refresh the data in the {@link BooksDataSource}
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    public void loadBooks(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mDataLoading.setValue(true);
        }
        if (forceUpdate) {
            mBooksRepository.refreshBooks();
        }

        mBooksRepository.getBooks(new BooksDataSource.LoadBooksCallback() {
            @Override
            public void onBooksLoaded(List<Book> books) {

            }

            @Override
            public void onDataNotAvailable() {

            }

        });
    }



}
