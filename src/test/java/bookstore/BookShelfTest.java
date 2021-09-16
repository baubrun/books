package bookstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("BookShelf")
public class BookShelfTest {

    private BookShelfTest(TestInfo testInfo){
        System.out.println(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("should be empty when no book is added")
    public void shelfIsEmptyWhenNoBookAdded() throws Exception{
        BookShelf shelf = new BookShelf();
        List<String> books = shelf.books();
        assertTrue(
                books.isEmpty(),
                () -> "bookstore.BookShelf is empty.");
    }
}
