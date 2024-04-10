package tqs.lab7.lab7_3.integration;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import tqs.lab7.lab7_3.Book;
import tqs.lab7.lab7_3.BookRepository;

@Testcontainers
@SpringBootTest
public class BookRepositoryITest {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer(DockerImageName.parse("postgres"))
        .withDatabaseName("bookDB")
        .withUsername("admin")
        .withPassword("Admin123");
  
    @Autowired
    private BookRepository bookRepository;

    // Add Spring Boot environment properties from container DB
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
      registry.add("spring.datasource.url", container::getJdbcUrl);
      registry.add("spring.datasource.password", container::getPassword);
      registry.add("spring.datasource.username", container::getUsername);
    }

    @Test()
    public void simpleWriteAndRead(){
        Book book = new Book("1984", "George Orwell");
        Book saved = bookRepository.save(book);

        long savedId = saved.getId();

        assertEquals(bookRepository.findById(savedId).get(), book);
    }
}
