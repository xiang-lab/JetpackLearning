package com.xiang.booksactivity;

import android.widget.ListView;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Main entry point for accessing tasks data.
 */
public interface BooksDataSource {

    interface LoadBooksCallback {
        void onBooksLoaded(List<Book> books);
        void onDataNotAvailable();
    }

    interface GetBookCallback {
        void onBookLoaded(Book book);
        void onDataNotAvailable();
    }

    void getBooks(@NonNull LoadBooksCallback callback);

    void getBook(@NonNull String bookId, @NonNull GetBookCallback callback);

    void savaBook(@NonNull Book book);

    void completeBook(@NonNull Book book);

    void completeBook(@NonNull String bookId);

    void activateBook(@NonNull Book book);

    void activateBook(@NonNull String bookId);

    void clearCompletedBooks();

    void refreshBooks();

    void deleteAllBooks();

    void deleteBook(@NonNull String bookId);
}











