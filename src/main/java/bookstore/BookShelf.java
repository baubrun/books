package bookstore;

import java.util.*;
import java.util.stream.Collectors;

public class BookShelf {

    private final List<Book> books = new ArrayList<>();

    public List<Book> books(){
        return Collections.unmodifiableList(books);
    }

    public void add(Book... newBook){
        Arrays.stream(newBook).forEach(books::add);
    }

    public List<Book> arrange(){
       return arrange(Comparator.naturalOrder());
    }

    public List<Book> arrange(Comparator<Book> attr){
       return books.stream().sorted(attr).collect(Collectors.toList());
    }
}
