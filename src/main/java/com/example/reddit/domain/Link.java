package com.example.reddit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Link {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String url;

}
