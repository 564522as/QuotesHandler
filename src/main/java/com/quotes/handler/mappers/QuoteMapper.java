package com.quotes.handler.mappers;

import com.quotes.handler.dto.QuoteDTO;
import com.quotes.handler.entities.Quote;
import com.quotes.handler.entities.Votes;
import com.quotes.handler.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class QuoteMapper implements EntityAndDTOMapper<Quote, QuoteDTO> {
    private final UserService userService;

    public QuoteMapper(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Quote toEntity(QuoteDTO quoteDTO) {
        Quote quote = new Quote();
        quote.setContent(quoteDTO.getContent());
        quote.setAuthor(userService.getByEmail(quoteDTO.getAuthorEmail()));
        return quote;
    }

    @Override
    public QuoteDTO toDTO(Quote quote) {
        QuoteDTO quoteDTO = new QuoteDTO();
        quoteDTO.setContent(quote.getContent());
        quoteDTO.setId(quote.getId());
        quoteDTO.setAuthorEmail(quote.getAuthor().getEmail());
        for(Votes votes: quote.getEvolutionOfVotes()) {
            quoteDTO.addToEvolutionOfVotes(votes.getCount());
        }
        if (quote.getEvolutionOfVotes().size() > 0) {
            quoteDTO.setVotes(quote.getEvolutionOfVotes().get(0).getCount());
        } else {
            quoteDTO.setVotes(0);
        }
        return quoteDTO;
    }
}
