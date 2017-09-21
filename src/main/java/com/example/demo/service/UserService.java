package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public interface UserService {
    List<User> getUsers();

    void exportExcel(HttpServletResponse response, List<User> userList, String fileName);
}
