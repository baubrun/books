package bookstore;

import java.time.LocalDate;

public class Book {
    private final String title;
    private final String author;
    private final LocalDate publishedOn;
    private LocalDate startedReadingOn;
    private LocalDate finishedReadingOn;

    public Book(String title, String author, LocalDate publishedOn) {
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public LocalDate getStartedReadingOn() {
        return startedReadingOn;
    }

    public void setStartedReadingOn(LocalDate startedReadingOn) {
        this.startedReadingOn = startedReadingOn;
    }

    public LocalDate getFinishedReadingOn() {
        return finishedReadingOn;
    }

    public void setFinishedReadingOn(LocalDate finishedReadingOn) {
        this.finishedReadingOn = finishedReadingOn;
    }
}