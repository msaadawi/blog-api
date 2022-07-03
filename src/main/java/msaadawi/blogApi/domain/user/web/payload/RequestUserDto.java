package msaadawi.blogApi.domain.user.web.payload;

public interface RequestUserDto extends UserDto {

    boolean containsId();

    boolean containsUsername();

    boolean containsEmail();

    boolean containsPhone();

    boolean containsFirstName();

    boolean containsLastName();

    boolean containsBirthDate();

    boolean containsProfession();

    boolean containsCurrentLocation();
}
