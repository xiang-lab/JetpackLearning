package com.xiang.booksactivity;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 * <p>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 *
 * //TODO: Implement this class using LiveData.
 */
public class BooksRepository implements BooksDataSource {

    private volatile static BooksRepository INSTANCE = null;

    private final BooksDataSource mBooksLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Book> mCachedBooks;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    private boolean mCacheIsDirty = false;

    // Prevent direct instantiation
    private BooksRepository(@NonNull BooksDataSource booksLocalDataSource) {
        mBooksLocalDataSource = checkNotNull(booksLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param booksLocalDataSource  the device storage data source
     * @return the {@link BooksRepository} instance
     */
    public static BooksRepository getInstance(BooksDataSource booksLocalDataSource) {
        if (INSTANCE == null) {
            synchronized (BooksRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BooksRepository(booksLocalDataSource);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link # getInstance(BooksDataSource, BooksDataSource)} to create a new instance
     * next time it's called.
     */
    public static void  destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Gets books from cache, local data source (SQLite) , whichever is
     * available first.
     * <p>
     * Note: {@link LoadBooksCallback #onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @Override
    public void getBooks( @NonNull final LoadBooksCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCachedBooks != null && !mCacheIsDirty) {
            callback.onBooksLoaded(new ArrayList<>(mCachedBooks.values()));
            return;
        }

        EspressoIdlingResource.increment(); // App is busy until further notice

        if (!mCacheIsDirty) {
            // Query the local storage if available. If not, query the network.
            mBooksLocalDataSource.getBooks(new LoadBooksCallback() {
                @Override
                public void onBooksLoaded(List<Book> books) {
                    refreshCache(books);

                    EspressoIdlingResource.decrement(); // Set app as idle.
                    callback.onBooksLoaded(new ArrayList<>(mCachedBooks.values()));
                }

                @Override
                public void onDataNotAvailable() {
                    EspressoIdlingResource.decrement(); // Set app as idle.
                    callback.onDataNotAvailable();
                }
            });
        }

    }

    @Override
    public void getBook(@NonNull String bookId, @NonNull GetBookCallback callback) {

    }

    @Override
    public void savaBook(@NonNull Book book) {

    }

    @Override
    public void completeBook(@NonNull Book book) {

    }

    @Override
    public void completeBook(@NonNull String bookId) {

    }

    @Override
    public void activateBook(@NonNull Book book) {

    }

    @Override
    public void activateBook(@NonNull String bookId) {

    }

    @Override
    public void clearCompletedBooks() {

    }

    @Override
    public void refreshBooks() {

    }

    @Override
    public void deleteAllBooks() {

    }

    @Override
    public void deleteBook(@NonNull String bookId) {
        mBooksLocalDataSource.deleteBook(checkNotNull((bookId)));
        mCachedBooks.remove(bookId);
    }

    private void refreshCache(List<Book> books) {
        if (mCachedBooks == null) {
            mCachedBooks = new LinkedHashMap<>();
        }
        mCachedBooks.clear();
        for (Book book : books) {
            mCachedBooks.put(book.getId(), book);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<Book> books) {
        mBooksLocalDataSource.deleteAllBooks();
        for (Book book : books) {
            mBooksLocalDataSource.savaBook(book);
        }
    }

    @NonNull
    private Book getBookWithId(@NonNull String id) {
        checkNotNull(id);
        if (mCachedBooks == null || mCachedBooks.isEmpty()) {
            return  null;
        } else {
            return mCachedBooks.get(id);
        }
    }

}






