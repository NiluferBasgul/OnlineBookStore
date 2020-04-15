package com.example.EmT.repository;

import com.example.EmT.model.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Profile("!in-memory")
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAll();
    List<Author> findAllByName(String name);
    Optional<Author> findById(Long id);
    Author save(Author author);
    void deleteById(Long id);

}
