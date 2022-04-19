package com.example.restblog.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {

    private Long id;
    private User author;
    private String title;
    private String content;

}
