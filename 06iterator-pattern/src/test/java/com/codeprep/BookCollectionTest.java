package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BookCollection Iterator Tests")
class BookCollectionTest {

    private BookCollection bookCollection;
    private Book cleanCode;
    private Book pragmaticProgrammer;
    private Book designPatterns;

    @BeforeEach
    void setUp() {
        bookCollection = new BookCollection();
        cleanCode = new Book("Clean Code", "Robert C. Martin");
        pragmaticProgrammer = new Book("The Pragmatic Programmer", "Andrew Hunt");
        designPatterns = new Book("Design Patterns", "Gang of Four");

        bookCollection.addBook(cleanCode);
        bookCollection.addBook(pragmaticProgrammer);
        bookCollection.addBook(designPatterns);
    }

    @Test
    @DisplayName("hasNext() returns true when books are available")
    void hasNext_returnsTrueWhenBooksAvailable() {
        Iterator<Book> iterator = bookCollection.createIterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("hasNext() returns false on an empty collection")
    void hasNext_returnsFalseOnEmptyCollection() {
        BookCollection empty = new BookCollection();
        Iterator<Book> iterator = empty.createIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("next() returns books in insertion order")
    void next_returnsBooksInInsertionOrder() {
        Iterator<Book> iterator = bookCollection.createIterator();

        assertEquals(cleanCode, iterator.next());
        assertEquals(pragmaticProgrammer, iterator.next());
        assertEquals(designPatterns, iterator.next());
    }

    @Test
    @DisplayName("Iterator traverses all books exactly once")
    void iterator_traversesAllBooksExactlyOnce() {
        Iterator<Book> iterator = bookCollection.createIterator();
        List<Book> result = new ArrayList<>();

        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertEquals(3, result.size());
        assertTrue(result.contains(cleanCode));
        assertTrue(result.contains(pragmaticProgrammer));
        assertTrue(result.contains(designPatterns));
    }

    @Test
    @DisplayName("hasNext() returns false after all books are consumed")
    void hasNext_returnsFalseAfterAllBooksConsumed() {
        Iterator<Book> iterator = bookCollection.createIterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Book toString contains title and author")
    void book_toStringContainsTitleAndAuthor() {
        String result = cleanCode.toString();
        assertTrue(result.contains("Clean Code"));
        assertTrue(result.contains("Robert C. Martin"));
    }

    @Test
    @DisplayName("Book getters return correct values")
    void book_gettersReturnCorrectValues() {
        assertEquals("Clean Code", cleanCode.getTitle());
        assertEquals("Robert C. Martin", cleanCode.getAuthor());
    }

    @Test
    @DisplayName("createIterator() returns a fresh iterator each time")
    void createIterator_returnsFreshIteratorEachTime() {
        Iterator<Book> first = bookCollection.createIterator();
        first.next(); // advance first iterator

        Iterator<Book> second = bookCollection.createIterator();

        // second iterator should start from the beginning
        assertEquals(cleanCode, second.next());
    }
}

