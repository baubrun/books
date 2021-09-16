package bookstore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookShelf {

    private final List<String> books = new ArrayList<>();

    public List<String> books(){
        return books;
    }

    public void add(String... newBook){
        Arrays.stream(newBook).forEach(books::add);
    }
}
