package tqs.lab5;

import java.util.Date;

public class Book {
    private String title;
    private String author;
    private String ISBN;
    private Date date;
    private String publisher;

    public Book(String title, String author, String ISBN, Date date, String publisher) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.date = date;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", ISBN=" + ISBN + ", date=" + date + ", publisher="
                + publisher + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (ISBN == null) {
            if (other.ISBN != null)
                return false;
        } else if (!ISBN.equals(other.ISBN))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (publisher == null) {
            if (other.publisher != null)
                return false;
        } else if (!publisher.equals(other.publisher))
            return false;
        return true;
    }
}

