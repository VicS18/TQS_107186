package tqs.lab7.lab7_3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class Lab73ApplicationTests {

  	@Container
  	public static PostgreSQLContainer container = new PostgreSQLContainer()
    	.withUsername("duke")
    	.withPassword("password")
    	.withDatabaseName("test");

	@Autowired
	private BookRepository bookRepository;

	@Test
	void contextLoads() {
		Book book = new Book("Test book", "The Author");

		bookRepository.save(book);
	}

}
