package com.codeprep;

import java.util.ArrayList;
import java.util.List;

public class BookCollection {

    private final List<Book> books;

    public BookCollection() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public  Iterator<Book> createIterator() {
        return new BookIterator(books);
    }

    private static class BookIterator implements Iterator<Book> {

        private final List<Book> books;
        private int position;

        public BookIterator(List<Book> books) {
            this.books = books;
        }

        @Override
        public boolean hasNext() {
            return position < books.size();
        }

        @Override
        public Book next() {
            return books.get(position++);
        }
    }
}
