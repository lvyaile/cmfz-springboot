package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.China;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by 白鹿 on 2018/8/6.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Map queryAll(int page, int rows) {
        int start = (page - 1) * rows;
        int end = rows;
        System.out.println(start);
        List<User> list = userDao.queryAll(start,end);
        int total = userDao.getCount();
        Map map = new HashMap();
        map.put("rows", list);
        map.put("total", total);
        return map;
    }

    @Override
    public void update(User user) {
        userDao.update(user);

    }
    @Override
    public void export(HttpServletResponse response) {
//      excel文件对象
        Workbook workbook = new HSSFWorkbook();
//      日期调整
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);
//      字体的调整
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(Font.COLOR_RED);
        font.setFontName("楷体");
        font.setBold(true);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
//      工作表
        Sheet sheet = workbook.createSheet("用户信息");
        sheet.setColumnWidth(7, 40 * 256);
        sheet.setColumnWidth(8, 25 * 256);
        sheet.setColumnWidth(11, 25 * 256);

        String[] titles = {"编号", "姓名", "法名", "图片","密码","盐值","性别","所在地","签名","状态","电话","创建时间"};
        //   创建行     行下标
        Row row = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title);
        }
//        相应的数据   数据库   list<user>
        List<User> users = userDao.queryAllUser();
        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(users.get(i).getId());
            row1.createCell(1).setCellValue(users.get(i).getName());
            row1.createCell(2).setCellValue(users.get(i).getDharma());
            row1.createCell(3).setCellValue(users.get(i).getPhoto());
            row1.createCell(4).setCellValue(users.get(i).getPassword());
            row1.createCell(5).setCellValue(users.get(i).getSalt());
            row1.createCell(6).setCellValue(users.get(i).getSex());
            row1.createCell(7).setCellValue(users.get(i).getLocation());
            row1.createCell(8).setCellValue(users.get(i).getSign());
            row1.createCell(9).setCellValue(users.get(i).getStatus());
            row1.createCell(10).setCellValue(users.get(i).getPhoneNum());
            row1.createCell(11).setCellValue(users.get(i).getRegistDate());
            Cell cell1 = row1.createCell(11);
            cell1.setCellStyle(cellStyle1);
            cell1.setCellValue(users.get(i).getRegistDate());
        }

        String newName = new Date().getTime() + "";
        newName = newName + "用户数据" + ".xls";
        String s = null;

        try {
            s = new String(newName.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-Disposition", "attachment;fileName=" + s);

        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //导入用户表
    @Override
    public void userImport(MultipartFile file) {
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            cell.setCellType(CellType.STRING);
            String s = row.getCell(0).getStringCellValue();
            int id=Integer.parseInt(s);
            String name = row.getCell(1).getStringCellValue();
            String dharma = row.getCell(2).getStringCellValue();
            String photo = row.getCell(3).getStringCellValue();
            String password = row.getCell(4).getStringCellValue();
            String salt = row.getCell(5).getStringCellValue();
            int sex = row.getCell(6).getRowIndex();
            String location = row.getCell(7).getStringCellValue();
            String sign = row.getCell(8).getStringCellValue();
            String status = row.getCell(9).getStringCellValue();
            int phoneNum = row.getCell(10).getRowIndex();
            Date registDate = row.getCell(11).getDateCellValue();
            User user = new User(0, name, dharma, photo,password,salt,sex,location,sign,status,phoneNum,registDate);
            System.out.println(user);
            userDao.add(user);
        }

    }
    public void customerExport(String title, String fileds, HttpServletResponse response) {
        Workbook workbook = new HSSFWorkbook();

        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("YYYY年MM月dd日");
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);

        Sheet sheet = workbook.createSheet("用户信息");
        Row row = sheet.createRow(0);
        String[] split = title.split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            row.createCell(i).setCellValue(s);
        }
        String[] filed = fileds.split(",");
        List<User> users = userDao.queryAllUser();
        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            Class<? extends User> aClass = users.get(i).getClass();
            for (int j = 0; j < filed.length; j++) {
                String methodName = "get" + filed[j].substring(0, 1).toUpperCase() + filed[j].substring(1);
                Object o = null;
                try {
                    o = aClass.getDeclaredMethod(methodName, null).invoke(users.get(i), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (o instanceof Date) {
                    sheet.setColumnWidth(j, 20 * 256);
                    Cell cell = row1.createCell(j);
                    cell.setCellStyle(cellStyle1);
                    cell.setCellValue((Date) o);
                } else {
                    row1.createCell(j).setCellValue(String.valueOf(o));
                }
            }
        }
        String newName = new Date().getTime() + "";
        newName = newName + "用户数据" + ".xls";
        String s = null;
        try {
            s = new String(newName.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-Disposition", "attachment;fileName=" + s);
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int userNum() {
        int num=userDao.userNum();
        return num;
    }

    @Override
    public int userNum2() {
        int num2=userDao.userNum2();
        return num2;
    }

    @Override
    public int userNum3() {
       int num3=userDao.userNum3();
        return num3;
    }

    @Override
    public List<China> chinaMale() {
        List<China> male=userDao.chinaMale();
        System.out.println(male);
        return male;
    }

    @Override
    public List<China> chinaFemale() {
        List<China> female=userDao.chinaFemale();
        System.out.println(female);
        return female;
    }

}
