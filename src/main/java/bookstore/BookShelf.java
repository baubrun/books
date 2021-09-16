package bookstore;

import java.util.*;

public class BookShelf {

    private final List<String> books = new ArrayList<>();

    public List<String> books(){
        return Collections.unmodifiableList(books);
    }

    public void add(String... newBook){
        Arrays.stream(newBook).forEach(books::add);
    }

    public List<String> arrange(){
        books.sort(Comparator.naturalOrder());
        return books;
    }
}
