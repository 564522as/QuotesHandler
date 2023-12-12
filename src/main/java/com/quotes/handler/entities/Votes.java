package com.quotes.handler.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Votes")
public class Votes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "count")
    private int count;
    @Column(name = "last_updated")
    private Date lastUpdated;
    @ManyToOne
    @JoinColumn(name = "quote_id", referencedColumnName = "id")
    private Quote quote;
    @Column(name = "outdated")
    private boolean outdated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }
}
