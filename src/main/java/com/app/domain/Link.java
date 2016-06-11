package com.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user1 on 10/06/2016.
 */

@Entity
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private int linkId;

    @Column(name = "url",nullable = false)
    private String url;

    @Column(name = "category",nullable = false)
    private String category;

    @Embedded
    private LinkDetails linkDetails = new LinkDetails();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Link() {
    }

    public Link(String url, String category, LinkDetails linkDetails, Account account) {
        this.url = url;
        this.category = category;
        this.linkDetails = linkDetails;
        this.account = account;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LinkDetails getLinkDetails() {
        return linkDetails;
    }

    public void setLinkDetails(LinkDetails linkDetails) {
        this.linkDetails = linkDetails;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
