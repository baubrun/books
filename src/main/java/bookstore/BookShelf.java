package bookstore;

import Progress.Progress;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.time.Year;

public class BookShelf {

    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void add(Book... newBook) {
        Arrays.stream(newBook).forEach(books::add);
    }

    public List<Book> arrange() {
        return arrange(Comparator.naturalOrder());
    }

    public List<Book> arrange(Comparator<Book> attr) {
        return books.stream().sorted(attr).collect(Collectors.toList());
    }

    public Map<Year, List<Book>> groupByPublicationYear() {
        return groupBy(book -> Year.of(book.getPublishedOn().getYear()));
    }

    public <T> Map<T, List<Book>> groupBy(Function<Book, T> attr) {
        return books.stream().collect(Collectors.groupingBy(attr));
    }

    public Progress progress() {
        int booksRead = Long.valueOf(
                books.stream().filter(Book::isRead).count()).intValue();
        int booksInProgress = Long.valueOf(
                books.stream().filter(Book::isProgress).count()).intValue();
        int booksRemaining = books.size() - booksRead - booksInProgress;

        int percentCompleted = booksRead * 100 / books.size();
        int percentRemaining = booksRemaining * 100 / books.size();
        int percentInProgress = booksInProgress * 100 / books.size();
        return new Progress(percentCompleted, percentRemaining, 0);
    }

    public List<Book> findByTitle(String title) {
        return findByTitle(title, x -> true);
    }

    public List<Book> findByTitle(String title, BookFilter filter) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title))
                .filter(filter::apply).collect(Collectors.toList());
    }
}
