package com.quotes.handler.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class QuoteDTO {
    private Integer id;
    @NotBlank(message = "Quote must have a content")
    private String content;
    private Integer votes;
    @NotBlank(message = "Need to point author's email")
    private String authorEmail;
    private List<Integer> evolutionOfVotes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public List<Integer> getEvolutionOfVotes() {
        return evolutionOfVotes;
    }

    public void addToEvolutionOfVotes(Integer votes) {
        this.evolutionOfVotes.add(votes);
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }
}
