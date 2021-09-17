package bookstore;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.time.LocalDate;
import java.time.Month;
import java.lang.reflect.Parameter;

class BooksParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext,
            final ExtensionContext extensionContext
    )
            throws ParameterResolutionException {

        Parameter parameter = parameterContext.getParameter();
            return (
                Objects.equals(parameter.getParameterizedType().getTypeName(),
                        "java.util.Map<java.lang.String, bookstore.Book>"));
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext,
            final ExtensionContext extensionContext
    ) throws ParameterResolutionException {
        Map<String, Book> books = new HashMap<>();
        books.put("book1",
                new Book(
                        "Zizi Dane",
                        "Marie-Eve Bergeron",
                        LocalDate.of(2020, Month.APRIL, 15)));
        books.put("book2",
                new Book(
                        "Big Bang",
                        "Patrick Cote",
                        LocalDate.of(2020, Month.JANUARY, 5)));
        books.put("book3",
                new Book(
                        "Amour",
                        "Voltaire",
                        LocalDate.of(2021, Month.MARCH, 9)));
        books.put("book4",
                new Book(
                        "ABC",
                        "D.L",
                        LocalDate.of(2021, Month.DECEMBER, 12)));
        books.put("book5",
                new Book(
                        "Finance",
                        "J.P.B",
                        LocalDate.of(2019, Month.FEBRUARY, 25)));
        return books;

    }

}
