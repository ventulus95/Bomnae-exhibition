package com.projvent.board.web.domain.post;

import com.projvent.board.web.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long Id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Column(columnDefinition = "TEXT")
    private String filePath;

    @Builder
    public Post(String title, String content, String author, String filePath){
        this.title = title;
        this.content = content;
        this.author = author;
        this.filePath = filePath;
    }

    public void update(String title, String content, String filePath){
        this.title = title;
        this.content = content;
        this.filePath = filePath;
    }
}
