package msaadawi.blogApi.domain.user.web.payload.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import msaadawi.blogApi.domain.comment.validation.group.OnReferencedByComment;
import msaadawi.blogApi.common.validation.Validatable;
import msaadawi.blogApi.common.exception.NoSuchPropertyException;
import msaadawi.blogApi.common.validation.constraint.NullOrNotBlank;
import msaadawi.blogApi.common.validation.group.OnBulkUpdate;
import msaadawi.blogApi.common.validation.group.OnSingleInsert;
import msaadawi.blogApi.common.validation.group.OnSingleUpdate;
import msaadawi.blogApi.domain.post.validation.group.OnReferencedByPost;
import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Optional;

@SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalAssignedToNull"})

@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        creatorVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultRequestUserDto implements RequestUserDto, Validatable {

    @Null(message = "{validation.user.id.on-single-insert-or-update.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class})
    @NotNull(message = "{validation.user.id.on-bulk-update.if-absent}",
            groups = OnBulkUpdate.class)
    @NotNull(message = "{validation.post.owner.id.on-referenced.if-absent}",
            groups = OnReferencedByPost.class)
    @NotNull(message = "{validation.comment.owner.id.on-referenced.if-absent}",
            groups = OnReferencedByComment.class)
    private Optional<@NotNull(message = "{validation.user.id.on-bulk-update.if-present}",
            groups = OnBulkUpdate.class)
    @NotNull(message = "{validation.post.owner.id.on-referenced.if-present}",
            groups = OnReferencedByPost.class)
    @NotNull(message = "{validation.comment.owner.id.on-referenced.if-present}",
            groups = OnReferencedByComment.class) Long> id;

    @NotNull(message = "{validation.user.username.on-single-insert.if-absent}",
            groups = OnSingleInsert.class)
    @Null(message = "{validation.user.username.on-single-or-bulk-update.if-present}",
            groups = {OnSingleUpdate.class, OnBulkUpdate.class})
    @Null(message = "{validation.post.owner.username.on-referenced.if-present}",
            groups = OnReferencedByPost.class)
    @Null(message = "{validation.comment.owner.username.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<@NotBlank(message = "{validation.user.username.on-single-insert.if-present}",
            groups = OnSingleInsert.class) String> username;

    @NotNull(message = "{validation.user.email.on-single-insert.if-absent}",
            groups = OnSingleInsert.class)
    @Null(message = "{validation.user.email.on-single-or-bulk-update.if-present}",
            groups = {OnSingleUpdate.class, OnBulkUpdate.class})
    @Null(message = "{validation.post.owner.email.on-referenced.if-present}",
            groups = OnReferencedByPost.class)
    @Null(message = "{validation.comment.owner.email.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<@NotEmpty(message = "{validation.user.email.on-single-insert.if-present}",
            groups = {OnSingleInsert.class})
    @Email(message = "{validation.user.email.on-single-insert.if-present}",
            groups = {OnSingleInsert.class}) String> email;

    @Null(message = "{validation.post.owner.phone.on-referenced.if-present}",
            groups = OnReferencedByPost.class)
    @Null(message = "{validation.comment.owner.phone.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<@Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "{validation.user.phone.on-all.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class, OnBulkUpdate.class}) String> phone;

    @NotNull(message = "{validation.user.first-name.on-single-insert.if-absent}",
            groups = OnSingleInsert.class)
    @Null(message = "{validation.post.owner.first-name.on-referenced.if-present}",
            groups = OnReferencedByPost.class)
    @Null(message = "{validation.comment.owner.first-name.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<@NotBlank(message = "{validation.user.first-name.on-all.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class, OnBulkUpdate.class}) String> firstName;

    @NotNull(message = "{validation.user.last-name.on-single-insert.if-absent}",
            groups = OnSingleInsert.class)
    @Null(message = "{validation.post.owner.last-name.on-referenced.if-present}",
            groups = OnReferencedByPost.class)
    @Null(message = "{validation.comment.owner.last-name.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<@NotBlank(message = "{validation.user.last-name.on-all.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class, OnBulkUpdate.class}) String> lastName;

    @Null(message = "{validation.post.owner.birthdate.on-referenced.if-present}",
            groups = OnReferencedByPost.class)
    @Null(message = "{validation.comment.owner.birthdate.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<@Past(message = "{validation.user.birthdate.on-all.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class, OnBulkUpdate.class}) Date> birthDate;

    @Null(message = "{validation.post.owner.profession.on-referenced.if-present}",
            groups = OnReferencedByPost.class)
    @Null(message = "{validation.comment.owner.profession.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<@NullOrNotBlank(message = "{validation.user.profession.on-all.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class, OnBulkUpdate.class}) String> profession;

    @Null(message = "{validation.post.owner.current-location.on-referenced.if-present}",
            groups = OnReferencedByPost.class)
    @Null(message = "{validation.comment.owner.current-location.on-referenced.if-present}",
            groups = OnReferencedByComment.class)
    private Optional<@NullOrNotBlank(message = "{validation.user.current-location.on-all.if-present}",
            groups = {OnSingleInsert.class, OnSingleUpdate.class, OnBulkUpdate.class}) String> currentLocation;

    @Override
    public String getPayloadName() {
        return "User Payload";
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
    public String getUsername() throws NoSuchPropertyException {
        if (username == null) return null;
        return username.orElse(null);
    }

    @Override
    public void setUsername(String username) throws NoSuchPropertyException {
        this.username = Optional.ofNullable(username);
    }

    @Override
    public boolean containsUsername() throws NoSuchPropertyException {
        return username != null;
    }

    @Override
    public String getEmail() throws NoSuchPropertyException {
        if (email == null) return null;
        return email.orElse(null);
    }

    @Override
    public void setEmail(String email) throws NoSuchPropertyException {
        this.email = Optional.ofNullable(email);
    }

    @Override
    public boolean containsEmail() throws NoSuchPropertyException {
        return email != null;
    }

    @Override
    public String getPhone() throws NoSuchPropertyException {
        if (phone == null) return null;
        return phone.orElse(null);
    }

    @Override
    public void setPhone(String phone) throws NoSuchPropertyException {
        this.phone = Optional.ofNullable(phone);
    }

    @Override
    public boolean containsPhone() throws NoSuchPropertyException {
        return phone != null;
    }

    @Override
    public String getFirstName() throws NoSuchPropertyException {
        if (firstName == null) return null;
        return firstName.orElse(null);
    }

    @Override
    public void setFirstName(String firstName) throws NoSuchPropertyException {
        this.firstName = Optional.ofNullable(firstName);
    }

    @Override
    public boolean containsFirstName() throws NoSuchPropertyException {
        return firstName != null;
    }

    @Override
    public String getLastName() throws NoSuchPropertyException {
        if (lastName == null) return null;
        return lastName.orElse(null);
    }

    @Override
    public void setLastName(String lastName) throws NoSuchPropertyException {
        this.lastName = Optional.ofNullable(lastName);
    }

    @Override
    public boolean containsLastName() throws NoSuchPropertyException {
        return lastName != null;
    }

    @Override
    public Date getBirthDate() throws NoSuchPropertyException {
        if (birthDate == null) return null;
        return birthDate.orElse(null);
    }

    @Override
    public void setBirthDate(Date birthDate) throws NoSuchPropertyException {
        this.birthDate = Optional.ofNullable(birthDate);
    }

    @Override
    public boolean containsBirthDate() throws NoSuchPropertyException {
        return birthDate != null;
    }

    @Override
    public String getProfession() throws NoSuchPropertyException {
        if (profession == null) return null;
        return profession.orElse(null);
    }

    @Override
    public void setProfession(String profession) throws NoSuchPropertyException {
        this.profession = Optional.ofNullable(profession);
    }

    @Override
    public boolean containsProfession() throws NoSuchPropertyException {
        return profession != null;
    }

    @Override
    public String getCurrentLocation() throws NoSuchPropertyException {
        if (currentLocation == null) return null;
        return currentLocation.orElse(null);
    }

    @Override
    public void setCurrentLocation(String currentLocation) throws NoSuchPropertyException {
        this.currentLocation = Optional.ofNullable(currentLocation);
    }

    @Override
    public boolean containsCurrentLocation() throws NoSuchPropertyException {
        return currentLocation != null;
    }

    @Override
    public String getResourceName() {
        return "User Resource";
    }

    @Override
    public Object getFieldValueByName(String fieldName) throws NoSuchPropertyException {
        if (fieldName == null) throw new IllegalArgumentException("fieldName is null.");
        if (fieldName.equals("id")) return id;
        if (fieldName.equals("username")) return username;
        if (fieldName.equals("email")) return email;
        if (fieldName.equals("phone")) return phone;
        if (fieldName.equals("firstName")) return firstName;
        if (fieldName.equals("lastName")) return lastName;
        if (fieldName.equals("birthdate")) return birthDate;
        if (fieldName.equals("profession")) return profession;
        if (fieldName.equals("currentLocation")) return currentLocation;
        throw new NoSuchPropertyException("there is no field with name "
                + fieldName + " in " + getClass().getName());
    }
}
