package com.codeprep;

public class IteratorDemo {

    public static void main(String[] args) {
        BookCollection bookCollection = new BookCollection();
        bookCollection.addBook(new Book("Clean Code", "Robert C. Martin"));
        bookCollection.addBook(new Book("The Pragmatic Programmer", "Andrew Hunt"));
        bookCollection.addBook(new Book("Design Patterns", "Gang of Four"));

        Iterator<Book> iterator = bookCollection.createIterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(book);
        }
    }
}
