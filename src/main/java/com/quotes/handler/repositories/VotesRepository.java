package com.quotes.handler.repositories;

import com.quotes.handler.entities.Quote;
import com.quotes.handler.entities.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Integer> {
    @Query("SELECT v.quote FROM Votes v WHERE v.outdated = false ORDER BY v.count desc LIMIT 10")
    List<Quote> getTopQuotes();

    @Query("SELECT v.quote FROM Votes v WHERE v.outdated = false ORDER BY v.count LIMIT 10")
    List<Quote> getWorseQuotes();
    @Query("SELECT v FROM Votes v WHERE v.quote.id = :id ORDER BY v.lastUpdated")
    List<Votes> findByQuoteId(int id);
}
