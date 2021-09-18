package bookstore;

import java.time.LocalDate;

public interface BookFilter {
    boolean apply(Book b);
}

class BookPublishedYearFilter implements BookFilter {
    private LocalDate startDate;

    static BookPublishedYearFilter After(Integer year) {
        BookPublishedYearFilter filter = new BookPublishedYearFilter();
        filter.startDate = LocalDate.of(year, 12, 31);
        return filter;
    }

    @Override
    public boolean apply(final Book b) {
        return b.getPublishedOn().isAfter(startDate);
    }

}