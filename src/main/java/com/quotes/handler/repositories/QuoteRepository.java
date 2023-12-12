package com.quotes.handler.repositories;

import com.quotes.handler.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    Optional<Quote> findByContent(String content);
    @Query("SELECT MAX (id) FROM Quote ")
    Integer getMaxId();
}
