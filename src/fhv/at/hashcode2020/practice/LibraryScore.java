package fhv.at.hashcode2020.practice;

import java.util.ArrayList;
import java.util.List;

public final class LibraryScore {
    int score;
    int usedDays;
    List<Book> books = new ArrayList<>();
    Library library;

    public LibraryScore(int score, int usedDays, List<Book> books, Library library) {
        this.score = score;
        this.usedDays = usedDays;
        this.books = List.copyOf(books);
        this.library = library;
    }

    public List<Book> getBooks() {
        return books;
    }

    public int getScore() {
        return score;
    }

    public int getUsedDays() {
        return usedDays;
    }

    public Library getLibrary() {
        return library;
    }

    @Override
    public String toString() {
        return "LibraryScore{" +
                "score=" + score +
                ", scandays=" + usedDays +
                ", books=" + books +
                ", library=" + library +
                '}';
    }
}
