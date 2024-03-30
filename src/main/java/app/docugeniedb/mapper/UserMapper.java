package app.docugeniedb.mapper;

import app.docugeniedb.dto.UserDTO;
import app.docugeniedb.entity.User;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUserName(),
                user.getPassword()
        );
    }

    public static User mapToUser(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getPassword()
        );
    }
}
