package com.example.reddit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Data
public class Comment extends Auditable{

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String body;
    @NonNull
    @ManyToOne
    private Link link;

    public Comment(String thisIsAComment, Link link) {
        super();
    }
}
