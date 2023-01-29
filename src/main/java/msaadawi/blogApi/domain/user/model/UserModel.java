package msaadawi.blogApi.domain.user.model;

import java.util.Date;

public interface UserModel {

    Long getId();

    void setId(Long id);

    String getUsername();

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);

    String getPhone();

    void setPhone(String phone);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    Date getBirthDate();

    void setBirthDate(Date birthDate);

    String getProfession();

    void setProfession(String profession);

    String getCurrentLocation();

    void setCurrentLocation(String currentLocation);
}
