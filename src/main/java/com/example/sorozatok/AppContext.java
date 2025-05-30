package com.example.sorozatok;

import com.example.sorozatok.repository.UserRepository;
import com.example.sorozatok.service.IUserService;
import com.example.sorozatok.service.UserService;

public class AppContext {
    private static final UserRepository userRepository = new UserRepository();
    private static final UserService userService = new UserService(userRepository);

    public static IUserService getUserService() {
        return userService;
    }
}
