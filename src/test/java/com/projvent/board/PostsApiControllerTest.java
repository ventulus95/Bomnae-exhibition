package com.projvent.board;

import com.projvent.board.web.domain.post.Post;
import com.projvent.board.web.domain.post.PostRepository;
import com.projvent.board.web.dto.PostsSaveRequestDto;
import com.projvent.board.web.dto.PostsUpadteRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    String title = "title";
    String content = "content";

    @LocalServerPort
    private int port;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TestRestTemplate testTemplate;

    @After
    public void tearDown() throws Exception{
        postRepository.deleteAll();
    }

    @Test
    public void posts_등록테스트() throws Exception{
        //given

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        String url ="http://localhost:"+port+"/api/v1/posts";

        //when
        ResponseEntity<Long> entity = testTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isGreaterThan(0L);
        List<Post> list = postRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(title);
        assertThat(list.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void posts_수정테스트() throws Exception{
        Post save = postRepository.save(Post.builder()
                .title(title)
                .author("author")
                .content(content)
                .build()
        );
        Long updateId = save.getId();
        String uptitle = "변경변경";
        String upcontent = "변경된 게시물";

        PostsUpadteRequestDto dto  = PostsUpadteRequestDto.builder()
                .title(uptitle)
                .content(upcontent)
                .build();

        String url ="http://localhost:"+port+"/api/v1/posts/"+updateId;
        HttpEntity<PostsUpadteRequestDto> request = new HttpEntity<>(dto);

        //when
        ResponseEntity<Long> response = testTemplate.exchange(url, HttpMethod.PUT, request, Long.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isGreaterThan(0L);
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(uptitle);
        assertThat(all.get(0).getContent()).isEqualTo(upcontent);
    }
}
