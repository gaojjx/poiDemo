package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class ExportExcelController {

    @Autowired
    private UserService userService;

    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = "demo";
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/binary;charset=ISO8859_1");
        response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1") + ".xls");
        List<User> userList = userService.getUsers();
        userService.exportExcel(response, userList, fileName);
    }
}
