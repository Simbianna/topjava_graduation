package ru.javawebinar.topjava.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.service.UserService;

@Controller
public class UserRestController {
    protected final Logger log = LoggerFactory.getLogger(UserRestController.class);

    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }
}
