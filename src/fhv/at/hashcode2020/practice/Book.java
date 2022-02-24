package fhv.at.hashcode2020.practice;

import java.util.Objects;

public class Book {
    private int bookId;
    private int score;

    public Book(int bookId, int score) {
        this.bookId = bookId;
        this.score = score;
    }

    public int getBookId() {
        return bookId;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "id: " + bookId + ", score: " + score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && score == book.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, score);
    }
}
