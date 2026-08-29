package in.thirutech.institute.service;

import in.thirutech.institute.dto.request.LoginRequest;
import in.thirutech.institute.dto.request.UserRequest;
import in.thirutech.institute.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    LoginResponse register(UserRequest request);
}
