package org.example.snippets.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAG_DATA")
public class Tag {
    @Id
    @GeneratedValue
    // Create many to 1 relationship
    // (many tags to one snippet)
    // put annotation here
    private int id;

    private String tag;
}
