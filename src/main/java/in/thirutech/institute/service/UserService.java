package in.thirutech.institute.service;

import in.thirutech.institute.dto.request.UserRequest;
import in.thirutech.institute.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);

    UserResponse updateUser(Integer id, UserRequest request);

    UserResponse getUserById(Integer id);

    List<UserResponse> getAllUsers();

    List<UserResponse> getActiveUsers();

    void deleteUser(Integer id);
}
