package org.csu.mywork1.controller;

import org.csu.mywork1.domain.Account;
import org.csu.mywork1.domain.Expert;
import org.csu.mywork1.service.AccountService;
import org.csu.mywork1.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/account/")
@SessionAttributes({"account","myList","authenticated"})
@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ManagerService managerService;


    @GetMapping("enter")//进入登录界面
    public String enter(){
        return "account/signon";
    }

    @PostMapping("signon")//登录
    public String signon(String username, String password, Model model, HttpSession session) {// 验证码所用的：String vrifyCode,
        String wrongMsg = "用户名或密码错误，请重新输入";
        Account account = accountService.getAccount(username, password);
        //下面这个是验证码所用到的
//        String text = (String) session.getAttribute("v");
//        if (text==null || !text.equalsIgnoreCase(vrifyCode)) {//equalsIgnoreCase意思是不考虑大小写
//            model.addAttribute("msg", "验证码输入错误!");
//
//            return "account/signon";
//        }
//        else
        if (account == null) {
            model.addAttribute("wrongMsg", wrongMsg);
            return "account/signon";
        }else {
            account.setPassword(null);
//            List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            boolean authenticated = true;
            model.addAttribute("account", account);
//            model.addAttribute("myList", myList);
            model.addAttribute("authenticated", authenticated);
            //下面这里要进行判断分类，如果为经理进入经理manager界面，如果是工作人员则进入personnel界面
            if (account.getUsername().charAt(0)=='m'){//进入manager的界面时，要直接显示专家列表
                List<Expert> expertList = managerService.getExpertList();
                model.addAttribute("expertList",expertList);
                model.addAttribute("account",account);
                return "common/mFrame";
            }
            else
                model.addAttribute("account",account);
                return "common/pFrame";
        }
    }

    @GetMapping("newAccountForm")//在登录界面点击注册时
    public String newAccountForm(){
        return "account/newAccount";
    }
    @PostMapping("newAccount")//在注册界面点击注册时
    public String newAccount(Account account,String username,String password,String repeatPassword,String role, Model model){
        String usernameMsg = "用户名已存在";
//        String passwordMsg = "密码不能为空";
        String repeatPwdMsg = "两次密码输入不一致";
        Account account1 = accountService.getAccount(username);//尝试从数据库获取这个用户
        if (account1 != null){//已经存在这个用户名
            model.addAttribute("usernameMsg",usernameMsg);//进行提示
        }else {//用户名不存在，可以注册
            if (!password.equals(repeatPassword)){//两次输入密码不一致
                model.addAttribute("repeatPwdMsg",repeatPwdMsg);
            }else {
                if (role.charAt(0)=='m'){//即选择注册为经理manager
                    username = 'm'+username;
                }else {//即选择注册为工作人员personnel
                    username = 'p'+username;
                }
                account.setUsername(username);
                accountService.newAccount(account);
                String successNewMsg = "注册成功,您的账号为："+username;
                model.addAttribute("successNewMsg",successNewMsg);
            }
        }
        return "account/newAccount";
    }
    @GetMapping("viewMyInf")
    public String viewMyInf(){
        return "account/myInf";
    }
    @PostMapping(value = "updateMyPicture")
    public String updateMyPicture(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request){
        if (file.isEmpty()){
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();//文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//后缀名
        String filePath = "D://IDE//mywork1//src//main//resources//static//images//";//上传后的路径
        fileName = UUID.randomUUID() + suffixName;//新文件名
        File dest =new File(filePath + fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        }catch (IOException e){
            e.printStackTrace();
        }
        String filename = "../images/" + fileName;
        model.addAttribute("filename",filename);
        accountService.updateMyInfPicture(filename);
        return "account/myInf";
    }

}
