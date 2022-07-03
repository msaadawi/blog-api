package msaadawi.blogApi.domain.comment.web.payload.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import msaadawi.blogApi.domain.comment.web.payload.RequestCommentDto;
import msaadawi.blogApi.commons.error.ValidatedBean;
import msaadawi.blogApi.commons.exception.NoSuchPropertyException;
import msaadawi.blogApi.commons.validation.group.OnBulkUpdate;
import msaadawi.blogApi.commons.validation.group.OnSingleInsert;
import msaadawi.blogApi.commons.validation.group.OnSingleUpdate;
import msaadawi.blogApi.domain.post.web.payload.RequestPostDto;
import msaadawi.blogApi.domain.post.web.payload.impl.DefaultRequestPostDto;
import msaadawi.blogApi.domain.user.web.payload.impl.DefaultRequestUserDto;
import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.Optional;

@SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalAssignedToNull"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultRequestCommentDto implements RequestCommentDto, ValidatedBean {

    @Null(message = "{validation.comment.id.on-single-insert-or-update.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class})
    @NotNull(message = "{validation.comment.id.on-bulk-update.if-absent}",
            groups = OnBulkUpdate.class)
    private Optional<@NotNull(message = "{validation.comment.id.on-bulk-update.if-present}",
            groups = OnBulkUpdate.class) Long> id;

    @NotNull(message = "{validation.comment.content.on-single-insert.if-absent}",
            groups = OnSingleInsert.class)
    private Optional<@NotBlank(message = "{validation.comment.content.on-all.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class, OnBulkUpdate.class}) String> content;

    @Null(message = "{validation.comment.created-at.on-single-insert.if-present}",
            groups = OnSingleInsert.class)
    @Null(message = "{validation.comment.created-at.on-single-or-bulk-update.if-present}",
            groups = {OnSingleUpdate.class, OnBulkUpdate.class})
    private Optional<Date> createdAt;

    @Null(message = "{validation.comment.last-updated-at.on-single-insert.if-present}",
            groups = OnSingleInsert.class)
    @Null(message = "{validation.comment.last-updated-at.on-single-or-bulk-update.if-present}",
            groups = {OnSingleUpdate.class, OnBulkUpdate.class})
    private Optional<Date> lastUpdatedAt;

    @NotNull(message = "{validation.comment.post.on-single-insert.if-absent}",
            groups = {OnSingleInsert.class})
    @Null(message = "{validation.comment.post.on-single-or-bulk-update.if-present}",
            groups = {OnSingleUpdate.class, OnBulkUpdate.class})
    private Optional<@NotNull(message = "{validation.comment.post.on-single-insert.if-present}",
            groups = {OnSingleInsert.class}) DefaultRequestPostDto> postAddedTo;

    @NotNull(message = "{validation.comment.owner.on-single-insert.if-absent}",
            groups = {OnSingleInsert.class})
    @Null(message = "{validation.comment.owner.on-single-or-bulk-update.if-present}",
            groups = {OnSingleUpdate.class, OnBulkUpdate.class})
    private Optional<@NotNull(message = "{validation.comment.owner.on-single-insert.if-present}",
            groups = {OnSingleInsert.class}) DefaultRequestUserDto> owner;

    @Override
    public String getPayloadName() {
        return "Comment Payload";
    }

    @Override
    public Long getId() throws NoSuchPropertyException {
        if (id == null) return 0L;
        return id.orElse(0L);
    }

    public void setId(Long id) throws NoSuchPropertyException {
        this.id = Optional.ofNullable(id);
    }

    @Override
    public boolean containsId() {
        return id != null;
    }

    @Override
    public String getContent() throws NoSuchPropertyException {
        if (content == null) return null;
        return content.orElse(null);
    }

    @Override
    public boolean containsContent() throws NoSuchPropertyException, UnsupportedOperationException {
        return content != null;
    }

    @Override
    public Date getCreatedAt() throws NoSuchPropertyException {
        if (createdAt == null) return null;
        return createdAt.orElse(null);
    }

    @Override
    public boolean containsCreatedAt() throws NoSuchPropertyException, UnsupportedOperationException {
        return createdAt != null;
    }

    @Override
    public Date getLastUpdatedAt() throws NoSuchPropertyException {
        if (lastUpdatedAt == null) return null;
        return lastUpdatedAt.orElse(null);
    }

    @Override
    public boolean containsLastUpdatedAt() throws NoSuchPropertyException, UnsupportedOperationException {
        return lastUpdatedAt != null;
    }

    @Override
    public RequestPostDto getPostAddedTo() throws NoSuchPropertyException {
        if (postAddedTo == null) return null;
        return postAddedTo.orElse(null);
    }

    @Override
    public boolean containsPostAddedTo() throws NoSuchPropertyException, UnsupportedOperationException {
        return postAddedTo != null;
    }

    @Override
    public RequestUserDto getOwner() throws NoSuchPropertyException {
        if (owner == null) return null;
        return owner.orElse(null);
    }

    @Override
    public boolean containsOwner() throws NoSuchPropertyException, UnsupportedOperationException {
        return owner != null;
    }

    @Override
    public String getResourceName() {
        return "Comment Resource";
    }

    @Override
    public Object getFieldValueByName(String fieldName) throws NoSuchPropertyException {
        if (fieldName == null) throw new IllegalArgumentException("fieldName is null");
        if (fieldName.equals("id")) return id;
        if (fieldName.equals("content")) return content;
        if (fieldName.equals("createdAt")) return createdAt;
        if (fieldName.equals("lastUpdatedAt")) return lastUpdatedAt;
        if (fieldName.equals("postAddedTo")) return postAddedTo;
        if (fieldName.equals("owner")) return owner;
        throw new NoSuchPropertyException("there is no field with name "
                + fieldName + " in " + getClass().getName());
    }
}
