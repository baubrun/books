package bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookShelf")
@ExtendWith(BooksParameterResolver.class)
public class BookShelfTest {

    private BookShelf shelf;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;


    @BeforeEach
    void init(Map<String, Book> bks) {
        shelf = new BookShelf();
        this.book1 = bks.get("book1");
        this.book2 = bks.get("book2");
        this.book3 = bks.get("book3");
        this.book4 = bks.get("book4");
        this.book5 = bks.get("book5");
    }

    @Nested
    @DisplayName("adding books")
    class BooksAdded {

        @Test
        @DisplayName("should be empty when no book is added")
        public void shelf_is_empty_when_no_book_added() throws Exception {
            List<Book> books = shelf.books();
            assertTrue(
                    books.isEmpty(),
                    () -> "BookShelf is empty.");
        }

        @Test
        @DisplayName("should contain number books when number books added")
        public void book_shelf_contains_number_of_books_added() {
            shelf.add(book1);
            shelf.add(book2);
            List<Book> books = shelf.books();
            assertEquals(
                    2,
                    books.size(),
                    () -> "BookShelf should have 2 books."
            );
        }


        @Test
        @DisplayName("should be empty when called without books")
        public void book_shelf_empty_when_called_without_books() {
            shelf.add();
            List<Book> books = shelf.books();
            assertTrue(books.isEmpty(), () -> "BookShelf is empty.");
        }

        @Test
        @DisplayName("should return immutable books")
        public void book_shelf_immutable_books() {
            shelf.add(book1, book2);
            List<Book> books = shelf.books();
            try {
                books.add(book3);
                fail(() -> "Should not add books.");
            } catch (Exception e) {
                assertTrue(
                        e instanceof UnsupportedOperationException,
                        () -> "Should throw UnsupportedOperationException"
                );
            }
        }
    }


    @Nested
    @DisplayName("sorting books")
    class BooksSorted {

        @Test
        @DisplayName("should sort books by title ASC order")
        public void book_shelf_arrange_by_title_ASC() {
            shelf.add(book1, book3, book2);
            List<Book> books = shelf.arrange();
            assertEquals(
                    List.of(book3, book2, book1),
                    books,
                    () -> "Books should be sorted by title ASC order."
            );
        }

        @Test
        @DisplayName("should sort books by title DESC order")
        public void book_shelf_arrange_by_title_DESC() {
            shelf.add(book1, book3, book2);
            Comparator<Book> reversedOrder = Comparator.<Book>naturalOrder().reversed();
            List<Book> books = shelf.arrange(reversedOrder);
            assertThat(books).isSortedAccordingTo(reversedOrder);
        }

    }

    @Nested
    @DisplayName("grouping books")
    class BooksGrouped {

        @Test
        @DisplayName("should group books by published year")
        public void group_books_by_published_year() {
            shelf.add(book1, book2, book3, book4, book5);
            Map<Year, List<Book>> booksByPublication = shelf.groupByPublicationYear();
            assertThat(booksByPublication)
                    .containsKey(Year.of(2020))
                    .containsValues(List.of(book1, book2));
            assertThat(booksByPublication)
                    .containsKey(Year.of(2019))
                    .containsValues(singletonList(book5));
        }

        @Test
        @DisplayName("should group books by attribute")
        public void group_books_by_attribute() {
            shelf.add(book1, book2, book3, book4, book5);
            Map<String, List<Book>> booksByAuthor = shelf.groupBy(Book::getAuthor);
            assertThat(booksByAuthor)
                    .containsKey("J.P.B")
                    .containsValues(singletonList(book5));
        }
    }







}
