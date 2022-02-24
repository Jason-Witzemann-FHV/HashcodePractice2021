package fhv.at.hashcode2020.practice;

import java.util.*;

public class Library {
    List<Book> availableBooks;
    int amountOfBooks;
    int signupProcess;
    int booksPerDay;

    public Library(String amountOfBooks, String signupProcess, String booksPerDay) {
        availableBooks = new ArrayList<>();
        this.amountOfBooks = Integer.parseInt(amountOfBooks);
        this.signupProcess = Integer.parseInt(signupProcess);
        this.booksPerDay = Integer.parseInt(booksPerDay);
    }

    public void addBook(Book book) {
        availableBooks.add(book);
    }

    public void addBooks(List<Book> books) {
        availableBooks = List.copyOf(books);
    }

    public List<Book> getAvailableBooks() {
        return List.copyOf(availableBooks);
    }

    public int getSignupProcess() {
        return signupProcess;
    }

    public int getBooksPerDay() {
        return booksPerDay;
    }

    public LibraryScore maxScore(int scanDays) {
        return calcScore(scanDays, availableBooks);
    }

    public LibraryScore notMaxScore(int scanDays, List<Book> alreadyScanned) {
        List<Book> remainingBooks = List.copyOf(availableBooks);
        remainingBooks.removeAll(alreadyScanned);
        return calcScore(scanDays, alreadyScanned);
    }

    public LibraryScore calcScore(int scanDays, List<Book> books) {
        books.sort(Comparator.comparing(Book::getScore).reversed());
        int score = 0;
        int pointer = 0;
        for (int i = 0; i < (scanDays - signupProcess); i++) {
            for (int j = 0; j < booksPerDay; j++){
                if (pointer < books.size()) {
                    score += books.get(pointer).getScore();
                    pointer++;
                } else {
                    return new LibraryScore(score, signupProcess, books, this);
                }
            }
        }
        return new LibraryScore(score, signupProcess, books, this);
    }
}
