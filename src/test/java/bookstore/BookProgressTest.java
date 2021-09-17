package bookstore;

import Progress.Progress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;


@DisplayName("BookProgress")
@ExtendWith(BooksParameterResolver.class)
public class BookProgressTest {
    private BookShelf shelf;
    private Book book1;
    private Book book2;
    private Book book3;


    @BeforeEach
    void init(Map<String, Book> bks) {
        shelf = new BookShelf();
        this.book1 = bks.get("book1");
        this.book2 = bks.get("book2");
        this.book3 = bks.get("book3");
    }
    @Test
    @DisplayName("progress is 0% completed and 100% remaining when book unread")
    void progress_when_book_unread(){
        shelf.add(book1, book2, book3);
        Progress progress = shelf.progress();
        assertThat(progress.completed()).isEqualTo(0);
        assertThat(progress.remaining()).isEqualTo(100);
    }

    @Test
    @DisplayName("progress is 60% completed and 40% remaining when 3 book are read")
    void progress_when_book_read(){
        shelf.add(book1, book2, book3);
        book1.setStartedReadingOn(LocalDate.of(2021, Month.MARCH, 1));
        book1.setFinishedReadingOn(LocalDate.of(2021, Month.MARCH, 15));
        Progress progress = shelf.progress();
        assertThat(progress.completed()).isEqualTo(33);
        assertThat(progress.remaining()).isEqualTo(66);
    }

}
