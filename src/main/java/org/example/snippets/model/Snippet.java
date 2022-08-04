package org.example.snippets.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="SNIPPET_DATA")
@Getter
@Setter
public class Snippet {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String body;
    private String tag;

    @Override
    public String toString() {
        return "Snippet{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
