package msaadawi.blogApi.domain.post.web.payload.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import msaadawi.blogApi.common.exception.NoSuchPropertyException;
import msaadawi.blogApi.common.validation.Validatable;
import msaadawi.blogApi.common.validation.group.OnBulkUpdate;
import msaadawi.blogApi.common.validation.group.OnSingleInsert;
import msaadawi.blogApi.common.validation.group.OnSingleUpdate;
import msaadawi.blogApi.domain.comment.validation.group.OnReferencedByComment;
import msaadawi.blogApi.domain.post.web.payload.RequestPostDto;
import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.Optional;

@SuppressWarnings({"OptionalAssignedToNull", "OptionalUsedAsFieldOrParameterType"})

@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        creatorVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultRequestPostDto implements RequestPostDto, Validatable {

    @Null(message = "{validation.post.id.on-single-insert-or-update.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class})
    @NotNull(message = "{validation.post.id.on-bulk-update.if-absent}",
            groups = OnBulkUpdate.class)
    @NotNull(message = "{validation.comment.post.id.on-referenced.if-absent}",
            groups = OnReferencedByComment.class)
    private Optional<@NotNull(message = "{validation.post.id.on-bulk-update.if-present}",
            groups = OnBulkUpdate.class)
    @NotNull(message = "{validation.comment.post.id.on-referenced.if-present}",
            groups = OnReferencedByComment.class) Long> id;

    @NotNull(message = "{validation.post.title.on-single-insert.if-absent}",
            groups = OnSingleInsert.class)
    @Null(message = "{validation.comment.post.title.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<@NotBlank(message = "{validation.post.title.on-all.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class, OnBulkUpdate.class}) String> title;

    @NotNull(message = "{validation.post.content.on-single-insert.if-absent}",
            groups = OnSingleInsert.class)
    @Null(message = "{validation.comment.post.content.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<@NotBlank(message = "{validation.post.content.on-all.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class, OnBulkUpdate.class}) String> content;

    @Null(message = "{validation.post.created-at.on-single-insert.if-present}",
            groups = OnSingleInsert.class)
    @Null(message = "{validation.post.created-at.on-single-or-bulk-update.if-present}",
            groups = {OnSingleUpdate.class, OnBulkUpdate.class})
    @Null(message = "{validation.comment.post.created-at.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<Date> createdAt;

    @Null(message = "{validation.post.last-updated-at.on-single-insert.if-present}",
            groups = OnSingleInsert.class)
    @Null(message = "{validation.post.last-updated-at.on-single-or-bulk-update.if-present}",
            groups = {OnSingleUpdate.class, OnBulkUpdate.class})
    @Null(message = "{validation.comment.post.last-updated-at.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<Date> lastUpdatedAt;

    @NotNull(message = "{validation.post.owner.on-single-insert.if-absent}",
            groups = {OnSingleInsert.class})
    @Null(message = "{validation.post.owner.on-single-or-bulk-update.if-present}",
            groups = {OnSingleUpdate.class, OnBulkUpdate.class})
    @Null(message = "{validation.comment.post.owner.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<@NotNull(message = "{validation.post.owner.on-single-insert.if-present}",
            groups = {OnSingleInsert.class}) RequestUserDto> owner;

    @Override
    public String getPayloadName() {
        return "Post Payload";
    }

    @Override
    public Long getId() throws NoSuchPropertyException {
        if (id == null) return 0L;
        return id.orElse(0L);
    }

    @Override
    public void setId(Long id) throws NoSuchPropertyException {
        this.id = Optional.ofNullable(id);
    }

    @Override
    public boolean containsId() throws NoSuchPropertyException {
        return id != null;
    }

    @Override
    public String getTitle() throws NoSuchPropertyException {
        if (title == null) return null;
        return title.orElse(null);
    }

    @Override
    public void setTitle(String title) throws NoSuchPropertyException {
        this.title = Optional.ofNullable(title);
    }

    @Override
    public boolean containsTitle() throws NoSuchPropertyException {
        return title != null;
    }

    @Override
    public String getContent() throws NoSuchPropertyException {
        if (content == null) return null;
        return content.orElse(null);
    }

    @Override
    public void setContent(String content) throws NoSuchPropertyException {
        this.content = Optional.ofNullable(content);
    }

    @Override
    public boolean containsContent() throws NoSuchPropertyException {
        return content != null;
    }

    @Override
    public Date getCreatedAt() throws NoSuchPropertyException {
        if (createdAt == null) return null;
        return createdAt.orElse(null);
    }

    @Override
    public void setCreatedAt(Date createdAt) throws NoSuchPropertyException {
        this.createdAt = Optional.ofNullable(createdAt);
    }

    @Override
    public boolean containsCreatedAt() throws NoSuchPropertyException {
        return createdAt != null;
    }

    @Override
    public Date getLastUpdatedAt() throws NoSuchPropertyException {
        if (lastUpdatedAt == null) return null;
        return lastUpdatedAt.orElse(null);
    }

    @Override
    public void setLastUpdatedAt(Date lastUpdatedAt) throws NoSuchPropertyException {
        this.lastUpdatedAt = Optional.ofNullable(lastUpdatedAt);
    }

    @Override
    public boolean containsLastUpdatedAt() throws NoSuchPropertyException {
        return lastUpdatedAt != null;
    }

    @Override
    public RequestUserDto getOwner() throws NoSuchPropertyException {
        if (owner == null) return null;
        return owner.orElse(null);
    }

    @Override
    public void setOwner(RequestUserDto owner) throws NoSuchPropertyException {
        this.owner = Optional.ofNullable(owner);
    }

    @Override
    public boolean containsOwner() throws NoSuchPropertyException {
        return owner != null;
    }

    @Override
    public String getResourceName() {
        return "Post Resource";
    }

    @Override
    public Object getFieldValueByName(String fieldName) throws NoSuchPropertyException {
        if (fieldName == null) throw new IllegalArgumentException("fieldName is null");
        if (fieldName.equals("id")) return id;
        if (fieldName.equals("title")) return title;
        if (fieldName.equals("content")) return content;
        if (fieldName.equals("createdAt")) return createdAt;
        if (fieldName.equals("lastUpdatedAt")) return lastUpdatedAt;
        if (fieldName.equals("owner")) return owner;
        throw new NoSuchPropertyException("there is no field with name "
                + fieldName + " in " + getClass().getName());
    }
}
