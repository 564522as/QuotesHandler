package com.quotes.handler.services;

import com.quotes.handler.dto.QuoteDTO;
import com.quotes.handler.entities.Quote;

import java.util.List;

public interface QuoteService {
    void add(QuoteDTO quote);
    QuoteDTO get(int id);
    QuoteDTO getRandom();
    void update(QuoteDTO quote);
    void delete(int id);
    List<QuoteDTO> getTopList();
    List<QuoteDTO> getWorseList();
    Integer getVotes(int id);
    List<Integer> getEvolutionOfVotes(int id);
    void upvote(int id);
    void downVote(int id);
}
