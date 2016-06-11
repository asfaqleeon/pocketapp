package com.app.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by user1 on 10/06/2016.
 */

@Embeddable
public class LinkDetails {
    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    public LinkDetails() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
