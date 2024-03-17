package tqs.lab5;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static tqs.lab5.DateUtils.parseYearMonthDay;

public class LibraryTest {

    static final Logger log = getLogger(lookup().lookupClass());

    private Library library;

    private List<Book> tempSearchResult = new ArrayList<Book>();

    @BeforeEach
    public void resetResults(){
        tempSearchResult = new ArrayList<Book>();
    }

    @ParameterType("\\d{4}\\-\\d{2}\\-\\d{2}")
    public Date date(String date) throws ParseException{
        return parseYearMonthDay(date);
    }

    @Given("the following books exist:")
    public void setup(DataTable table) throws ParseException {
        library = new Library();

        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        
        for (Map<String, String> columns : rows) {
            library.addBook(
                new Book(
                    columns.get("title"), 
                    columns.get("author"), 
                    columns.get("ISBN"),
                    parseYearMonthDay(columns.get("date")),
                    columns.get("publisher")
                    )
            );
        }
    }

    @When("I search for a book by title {string}")
    public void searchByTitle(String title) {
        log.debug("Searching by title with {}", title);
        tempSearchResult = library.findByTitle(title);
    }

    @When("I search for a book by author {string}")
    public void searchByAuthor(String author) {
        log.debug("Searching by author with {}", author);
        tempSearchResult = library.findByAuthor(author);
    }

    @When("I search for a book by ISBN {string}")
    public void searchByISBN(String ISBN) {
        log.debug("Searching by ISBN with {}", ISBN);
        tempSearchResult = library.findByISBN(ISBN);
    }

    @When("I search for a book published between {date} and {date}")
    public void searchByDate(Date d1, Date d2) {
        log.debug("Searching by date with {} and {}", d1, d2);
        tempSearchResult = library.findBetweenDates(d1, d2);
    }

    @Then("then I should get:")
    public void searchResult(DataTable expected) throws ParseException{
        List<Book> books = toBookList(expected);

        log.debug("Expected: {}", books);
        log.debug("Result: {}", tempSearchResult);

        assertIterableEquals(tempSearchResult, books);
    }

    private static List<Book> toBookList(DataTable table) throws ParseException{
        List<Book> bookList = new ArrayList<Book>();
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        
        for (Map<String, String> columns : rows) {
            bookList.add(
                new Book(
                    columns.get("title"), 
                    columns.get("author"), 
                    columns.get("ISBN"),
                    parseYearMonthDay(columns.get("date")),
                    columns.get("publisher")
                    )
            );
        }
        
        return bookList;
    }
}
