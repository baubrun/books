package bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("BookShelf")
public class BookShelfTest {

    private BookShelf shelf;
    private Book book1;
    private Book book2;

    private BookShelfTest(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
    }


    @BeforeEach
    void init() {
        shelf = new BookShelf();
        book1 = new Book(
                "book1Title",
                "Marie-Eve Bergeron",
                LocalDate.of(2021, Month.APRIL, 15));

        book2 = new Book(
                "book1Title",
                "Patrick Cote",
                LocalDate.of(2021, Month.JANUARY, 5));
    }

    @Test
    @DisplayName("should be empty when no book is added")
    public void shelf_is_empty_when_no_book_added() throws Exception {
        List<String> books = shelf.books();
        assertTrue(
                books.isEmpty(),
                () -> "bookstore.BookShelf is empty.");
    }

    @Test
    @DisplayName("should contain n books when n books added")
    public void book_shelf_contains_number_of_books_added() {
        shelf.add("book1Title");
        shelf.add("book2Title");
        List<String> books = shelf.books();
        assertEquals(
                2,
                books.size(),
                () -> "BookShelf should have 2 books."
        );
    }
}
