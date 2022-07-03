package msaadawi.blogApi.domain.user.model.impl;

import lombok.*;
import msaadawi.blogApi.domain.user.model.UserModel;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DefaultUserModel implements UserModel {

    private Long id;

    private String username;

    private String email;

    private String phone;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String profession;

    private String currentLocation;
}
