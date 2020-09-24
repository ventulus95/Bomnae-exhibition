package com.projvent.board.web.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostsUpadteRequestDto {

    public String title;
    public String content;

    @Builder
    public PostsUpadteRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
