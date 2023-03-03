package com.application.bookstore.repo;

import com.application.bookstore.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class BookRepositoryTest {

    @Mock
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    private final String titleBook = "The Hexagonal Design Pattern";
    private final String authorBook = "Author";
    private final Double priceBook = 1000000.0;
    private final String isbnBook = "ISBN-2023-01";

    @Test
    public void shouldFindBookEntityById() {
        bookRepository.save(mockInitBookEntity());
        BookEntity foundEntity = bookRepository.findById(mockInitBookEntity().getId()).orElse(null);
        assertNotNull(foundEntity);
        assertEquals(mockInitBookEntity(), foundEntity);
    }

    @Test
    public void saveBookEntity() {
        BookEntity foundEntity = bookRepository.save(mockInitBookEntity());
        assertThat(foundEntity).hasFieldOrPropertyWithValue("title",titleBook);
        assertThat(foundEntity).hasFieldOrPropertyWithValue("author",authorBook);
        assertThat(foundEntity).hasFieldOrPropertyWithValue("price",priceBook);
        assertThat(foundEntity).hasFieldOrPropertyWithValue("isbn",isbnBook);
    }

    @Test
    public void shouldFindBookEntityAll() {
        bookRepository.save(mockInitBookEntity());
        bookRepository.save(mockInitBookEntity());
        bookRepository.save(mockInitBookEntity());
        List<BookEntity> foundEntity = bookRepository.findAll();
        assertNotNull(foundEntity);
        assertEquals(3, foundEntity.size());
    }

    @Test
    public void findBookEntityIfRepositoryIsEmpty() {
        Iterable<BookEntity> bookEntities = bookRepository.findAll();
        assertThat(bookEntities).isEmpty();
    }

    @Test
    public void deleteAllBookEntity() {
        entityManager.persist(mockInitBookEntity());
        entityManager.persist(mockInitBookEntity());
        bookRepository.deleteAll();
        assertThat(bookRepository.findAll()).isEmpty();
    }

    private BookEntity mockInitBookEntity() {
        return BookEntity.builder().id(1)
                .title(titleBook).author(authorBook)
                .price(priceBook).isbn(isbnBook).build();
    }
}