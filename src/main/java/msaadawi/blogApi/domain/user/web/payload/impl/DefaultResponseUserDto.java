package msaadawi.blogApi.domain.user.web.payload.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import msaadawi.blogApi.domain.user.web.payload.ResponseUserDto;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DefaultResponseUserDto implements ResponseUserDto {

    private Long id;

    private String username;

    private String email;

    private String phone;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String profession;

    private String currentLocation;

    @JsonIgnore
    @Override
    public String getPayloadName() {
        return "User Payload";
    }
}
