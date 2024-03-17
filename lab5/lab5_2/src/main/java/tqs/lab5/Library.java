package tqs.lab5;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private ArrayList<Book> bookCatalog = new ArrayList<Book>();

    public List<Book> findByTitle(String title){
        return bookCatalog.stream()
                          .filter((b) -> b.getTitle().equals(title))
                          .collect(Collectors.toList());
    }

    public List<Book> findByAuthor(String author){
        return bookCatalog.stream()
                          .filter((b) -> b.getAuthor().equals(author))
                          .collect(Collectors.toList());
    }

    public List<Book> findByISBN(String ISBN){
        return bookCatalog.stream()
                          .filter((b) -> b.getISBN().equals(ISBN))
                          .collect(Collectors.toList());
    }

    public List<Book> findBetweenDates(Date d1, Date d2){
        if(d1.compareTo(d2) >= 0)
            throw new IllegalArgumentException("First date argument must be before second date argument");

        return bookCatalog.stream()
                          .filter((b) -> d1.compareTo(b.getDate()) <= 0 && d2.compareTo(b.getDate()) >= 0)
                          .collect(Collectors.toList());
    }

    public void addBook(Book book){
        bookCatalog.add(book);
    }

    public void removeBook(Book book){
        bookCatalog.remove(book);
    }

    public List<Book> getAllBooks(){
        return new ArrayList<Book>(bookCatalog);
    }

    public void deleteAllBooks(){
        bookCatalog = new ArrayList<Book>();
    }
}
