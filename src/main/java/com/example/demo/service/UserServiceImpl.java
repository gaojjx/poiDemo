package com.example.demo.service;

import com.example.demo.entity.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        User user;
        for (int i = 0; i < 10; i ++) {
            user = new User();
            user.setId(i);
            user.setAge(new Random().nextInt(10) + 1);
            user.setName("user" + i);
            System.out.println(user);
            list.add(user);
        }
        return list;
    }

    @Override
    public void exportExcel(HttpServletResponse response, List<User> userList, String fileName) {
        try {
            OutputStream outputStream = response.getOutputStream();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("sheet1");
            HSSFRow title = sheet.createRow(0);
            HSSFCell titleCell = title.createCell(0);
            titleCell.setCellValue("ID");
            titleCell = title.createCell(1);
            titleCell.setCellValue("年龄");
            titleCell = title.createCell(2);
            titleCell.setCellValue("名字");
            if (userList != null && userList.size() > 0) {
                HSSFRow row;
                HSSFCell cell;
                int i = 1;
                for (User user : userList) {
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(user.getId());
                    cell = row.createCell(1);
                    cell.setCellValue(user.getAge());
                    cell = row.createCell(2);
                    cell.setCellValue(user.getName());
                    i++;
                }
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
