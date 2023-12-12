package com.quotes.handler.services;

import com.quotes.handler.dto.QuoteDTO;
import com.quotes.handler.entities.Quote;
import com.quotes.handler.entities.Votes;
import com.quotes.handler.mappers.QuoteMapper;
import com.quotes.handler.repositories.QuoteRepository;
import com.quotes.handler.repositories.VotesRepository;
import com.quotes.handler.utils.QuoteAlreadyExistException;
import com.quotes.handler.utils.QuoteNotExistException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final QuoteMapper quoteMapper;
    private final VotesRepository votesRepository;

    public QuoteServiceImpl(QuoteRepository quoteRepository, QuoteMapper quoteMapper, VotesRepository votesRepository) {
        this.quoteRepository = quoteRepository;
        this.quoteMapper = quoteMapper;
        this.votesRepository = votesRepository;
    }

    @Override
    public void add(QuoteDTO quoteDTO) {
        if (this.quoteRepository.findByContent(quoteDTO.getContent()).isEmpty()) {
            Quote quote = quoteMapper.toEntity(quoteDTO);
            quote.setDateOfCreation(new Date());
            quoteRepository.save(quote);
            Votes votes = new Votes();
            votes.setCount(0);
            votes.setQuote(quote);
            votes.setLastUpdated(new Date());
            votesRepository.save(votes);
        } else {
            throw new QuoteAlreadyExistException("This quote already exist");
        }
    }

    @Override
    public QuoteDTO get(int id) {
        return quoteMapper.toDTO(getQuoteAndCheckOnExisting(id));
    }

    @Override
    public QuoteDTO getRandom() {
        int randomId = (int)Math.ceil(Math.random()*quoteRepository.getMaxId());
        Optional<Quote> quote = quoteRepository.findById(randomId);
        while(quote.isEmpty()) {
            quote = quoteRepository.findById(randomId);
            randomId = (int)Math.ceil(Math.random()*quoteRepository.getMaxId());
        }

        return quoteMapper.toDTO(quote.get());
    }

    @Override
    public void update(QuoteDTO quoteDTO) {
        Quote optionalQuote = getQuoteAndCheckOnExisting(quoteDTO.getId());
        Quote quote = quoteMapper.toEntity(quoteDTO);
        quote.setId(optionalQuote.getId());
        quoteRepository.save(quote);
    }

    @Override
    public void delete(int id) {
        quoteRepository.deleteById(id);
    }

    @Override
    public List<QuoteDTO> getTopList() {
        List<Quote> quotes = votesRepository.getTopQuotes();
        List<QuoteDTO> quoteDTOS = new ArrayList<>();
        for (Quote quote: quotes) {
            quoteDTOS.add(quoteMapper.toDTO(quote));
        }
        return quoteDTOS;
    }

    @Override
    public List<QuoteDTO> getWorseList() {
        List<Quote> quotes = votesRepository.getWorseQuotes();
        List<QuoteDTO> quoteDTOS = new ArrayList<>();
        for (Quote quote: quotes) {
            quoteDTOS.add(quoteMapper.toDTO(quote));
        }
        return quoteDTOS;
    }

    @Override
    public Integer getVotes(int id) {
        Quote quote = getQuoteAndCheckOnExisting(id);
        int quotesEvolutionSize = quote.getEvolutionOfVotes().size();
        return quote.getEvolutionOfVotes()
                .get(quotesEvolutionSize-1).getCount();
    }

    @Override
    public List<Integer> getEvolutionOfVotes(int id) {
        getQuoteAndCheckOnExisting(id);
        List<Integer> evolutionOFVotesList = new ArrayList<>();
        for (Votes votes : votesRepository.findByQuoteId(id)) {
            evolutionOFVotesList.add(votes.getCount());
        }
        return evolutionOFVotesList;
    }

    @Override
    public void upvote(int id) {
        Quote quote = getQuoteAndCheckOnExisting(id);
        Votes lastVotes = quote.getEvolutionOfVotes().get(0);
        boolean hasTheDayPassed = (new Date().getTime() - lastVotes.getLastUpdated().getTime()) > 86400000L;
        if (hasTheDayPassed) {
            Votes currentVotes = new Votes();
            currentVotes.setCount(lastVotes.getCount()+1);
            currentVotes.setLastUpdated(new Date());
            currentVotes.setQuote(quote);
            votesRepository.save(currentVotes);
        } else {
            lastVotes.setCount(lastVotes.getCount()+1);
            votesRepository.save(lastVotes);
        }
    }

    @Override
    public void downVote(int id) {
        Quote quote = getQuoteAndCheckOnExisting(id);
        Votes lastVotes = quote.getEvolutionOfVotes().get(0);
        boolean hasTheDayPassed = (new Date().getTime() - lastVotes.getLastUpdated().getTime()) > 86400000L;
        if (hasTheDayPassed) {
            Votes currentVotes = new Votes();
            currentVotes.setCount(lastVotes.getCount()-1);
            currentVotes.setLastUpdated(new Date());
            currentVotes.setQuote(quote);
            votesRepository.save(currentVotes);
        } else {
            lastVotes.setCount(lastVotes.getCount()-1);
            votesRepository.save(lastVotes);
        }
    }

    public Quote getQuoteAndCheckOnExisting(int id) {
        Optional<Quote> quote = quoteRepository.findById(id);
        if (quote.isPresent()) {
            return quote.get();
        } else {
            throw new QuoteNotExistException("Quote not exist");
        }
    }
}
