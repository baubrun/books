package bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("BookFilter")
@ExtendWith(BooksParameterResolver.class)
public class BookFilterTest {

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
    @DisplayName("searching books")
    class BookSearch {

        @Test
        @DisplayName("should find by text in title")
        public void search_by_title_text() {
            shelf.add(book1, book2, book3, book4, book5);
            List<Book> books = shelf.findByTitle("bang",
                    x -> x.getPublishedOn().isBefore(LocalDate.of(2021, 12, 31))
            );
            assertThat(books.size()).isEqualTo(2);
        }

        @Test
        @DisplayName("should find by published date")
        public void search_by_published_date() {
            shelf.add(book1, book2, book3, book4, book5);
            BookFilter filter = BookPublishedYearFilter.After(2020);
            assertTrue(filter.apply(book4));
            assertFalse(filter.apply(book1));
        }

    }
}
