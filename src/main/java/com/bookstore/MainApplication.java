package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            bookstoreService.newAuthor();
        };
    }
}


/*
 * 
 * 
 * 
 * 
 * How To Check That Transaction Timeout And Rollback At Expiration Works As Expected

Note: Do not test transaction timeout via Thread.sleep()! This is not working! Rely on two transactions and exclusive locks or even better rely on SQL sleep functions (e.g., MySQL, SELECT SLEEP(n) seconds, PostgreSQL, SELECT PG_SLEEP(n) seconds). Most RDBMS supports a sleep function flavor.

Description: This application contains several approaches for setting a timeout period for a transaction or query. The timeout is signaled by a specific timeout exception (e.g., .QueryTimeoutException). After timeout, the transaction is rolled back. You can see this in the database (visually or query) and on log via a message of type: Initiating transaction rollback; Rolling back JPA transaction on EntityManager [SessionImpl(... <open>)].

Key points:

set global transaction timeout via spring.transaction.default-timeout in seconds (see, application.properties)
set transaction timeout at method-level or class-level via @Transactional(timeout = n) in seconds
set query timeout via JPA javax.persistence.query.timeout hint in milliseconds
set query timeout via Hibrenate org.hibernate.timeout hint in seconds
Note: If you are using TransactionTemplate then the timeout can be set via TransactionTemplate.setTimeout(n) in seconds.
 * 
 * 
 * 
 */
