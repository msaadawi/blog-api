package msaadawi.blogApi.domain.comment.web.payload.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import msaadawi.blogApi.domain.comment.web.payload.ResponseCommentDto;
import msaadawi.blogApi.domain.post.web.payload.ResponsePostDto;
import msaadawi.blogApi.domain.user.web.payload.ResponseUserDto;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DefaultResponseCommentDto implements ResponseCommentDto {

    private Long id;

    private String content;

    private Date createdAt;

    private Date lastUpdatedAt;

    private ResponsePostDto post;

    private ResponseUserDto owner;

    @JsonIgnore
    @Override
    public String getPayloadName() {
        return "Comment Payload";
    }
}
