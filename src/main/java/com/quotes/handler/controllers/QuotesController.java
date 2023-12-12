package com.quotes.handler.controllers;

import com.quotes.handler.dto.QuoteDTO;
import com.quotes.handler.services.QuoteService;
import com.quotes.handler.utils.InvalidQuoteException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuotesController {
    private final QuoteService quoteService;

    public QuotesController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<String> add(@RequestBody @Valid QuoteDTO quote,
                                  BindingResult bindingResult) {
        checkBindingResultOnErrors(bindingResult);
        quoteService.add(quote);
        return new HttpEntity<>("Quote was added");
    }
    @GetMapping("/get")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpEntity<QuoteDTO> get(@RequestParam @Min(1) int id){
        return new HttpEntity<>(quoteService.get(id));
    }
    @GetMapping("/getRandom")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpEntity<QuoteDTO> get() {
        return new HttpEntity<>(quoteService.getRandom());
    }
    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpEntity<String> update(@RequestBody @Valid QuoteDTO quote,
                                     BindingResult bindingResult) {
        checkBindingResultOnErrors(bindingResult);
        quoteService.update(quote);
        return new HttpEntity<>("Quote was updated");
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<String> delete(@RequestParam @Min(1) int id) {
        quoteService.delete(id);
        return new HttpEntity<>("Quote was deleted");
    }
    @GetMapping("/getTop")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpEntity<List<QuoteDTO>> getTop() {
        return new HttpEntity<>(quoteService.getTopList());
    }
    @GetMapping("/getWorse")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpEntity<List<QuoteDTO>> getWorse() {
        return new HttpEntity<>(quoteService.getWorseList());
    }
    @GetMapping("/getVotes")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpEntity<Integer> getVotes(@RequestParam @Min(1) int id) {
        return new HttpEntity<>(quoteService.getVotes(id));
    }
    @GetMapping("/getEvolutionOfVotes")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpEntity<List<Integer>> getEvolutionOfVotes(@RequestParam @Min(1) int id) {
        return new HttpEntity<>(quoteService.getEvolutionOfVotes(id));
    }

    @PostMapping("/upvote")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpEntity<String> upvote(@RequestParam @Min(1) int id) {
        this.quoteService.upvote(id);
        return new HttpEntity<>("You voted");
    }
    @PostMapping("/downVote")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpEntity<String> downVote(@RequestParam @Min(1) int id) {
        this.quoteService.downVote(id);
        return new HttpEntity<>("You down voted");
    }

    public void checkBindingResultOnErrors(BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            StringBuilder message = new StringBuilder();
            message.append("message: ");
            for (FieldError error: bindingResult.getFieldErrors()) {
                message.append(error.getField());
                message.append(" - ");
                message.append(error.getDefaultMessage());
                message.append("; ");
            }
            throw new InvalidQuoteException(message.toString());
        }
    }
}
