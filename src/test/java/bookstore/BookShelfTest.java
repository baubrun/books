package bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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
                () -> "BookShelf is empty.");
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

    @Test
    @DisplayName("should be empty when called without books")
    public void book_shelf_empty_when_called_without_books() {
        shelf.add();
        List<String> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf is empty.");
    }

    @Test
    @DisplayName("should return immutable books")
    public void book_shelf_immutable_books() {
        shelf.add("book1title", "book2title");
        List<String> books = shelf.books();
        try {
            books.add("book3title");
            fail(() -> "Should not add books.");
        } catch (Exception e) {
            assertTrue(
                    e instanceof UnsupportedOperationException,
                    () -> "Should throw UnsupportedOperationException"
            );
        }
    }


    @Test
    @DisplayName("should short books by title.")
    public void book_shelf_arrange_by_title(){
        shelf.add("Big Bang", "Zizi Dane", "Amour de Loin");
        List<String> books = shelf.arrange();
        assertEquals(
                List.of("Amour de Loin", "Big Bang", "Zizi Dane"),
                books,
                () -> "Books should be sorted ASC by title."
        );

    }

}
