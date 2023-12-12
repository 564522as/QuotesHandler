package com.quotes.handler.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "content")
    @NotBlank(message = "Quote must have a content")
    private String content;
    @Column(name = "date_of_creation")
    private Date dateOfCreation;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User author;
    @OneToMany(mappedBy = "quote")
    private List<Votes> evolutionOfVotes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Votes> getEvolutionOfVotes() {
        return evolutionOfVotes;
    }

    public void setEvolutionOfVotes(List<Votes> evolutionOfVotes) {
        this.evolutionOfVotes = evolutionOfVotes;
    }
}
