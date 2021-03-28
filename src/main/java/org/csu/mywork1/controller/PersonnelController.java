package org.csu.mywork1.controller;

//import com.sun.org.apache.xpath.internal.operations.Mod;
import org.csu.mywork1.domain.Account;
import org.csu.mywork1.domain.Expert;
import org.csu.mywork1.domain.Lecture;
import org.csu.mywork1.service.AccountService;
import org.csu.mywork1.service.ManagerService;
import org.csu.mywork1.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.Authenticator;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;

@RequestMapping("/personnel/")
@Controller
public class PersonnelController {
    @Autowired
    private PersonnelService personnelService;
    @Autowired
    private ManagerService managerService;

    @GetMapping("viewLecturePlan")
    public String viewLecturePlan(Model model){
        List<Lecture> lectureList = managerService.getLectureList();
        int yesCount = managerService.yesCount();
        int noCount = managerService.noCount();
        model.addAttribute("lectureList",lectureList);
        model.addAttribute("yesCount",yesCount);
        model.addAttribute("noCount",noCount);
        return "personnel/lectureList";
    }
    @GetMapping("viewExpertList")//在personnelLeft点击专家资料时的操作
    public String viewExpertList(Model model){
        List<Expert> expertList = managerService.getExpertList();
        model.addAttribute("expertList",expertList);
        return "personnel/expertList";
    }
    @GetMapping("viewExpert")
    public String viewExpert(String name,Model model){
        Expert expert = managerService.getExpertByName(name);
        model.addAttribute("expert",expert);
        return "personnel/viewExpert";
    }
    @GetMapping("addExpertForm")//这个是在专家资料页面点击新增时的操作，即返回增加专家信息的界面
    public String addExpertForm(Model model){
        return "personnel/addExpert";
    }
    @PostMapping("addExpert")//这个是在新增专家信息界面点击保存时的操作
    public String addExpert(Expert expert,Model model){
        String msg = "添加成功";
        personnelService.addExpert(expert);
        expert = managerService.getExpertByName(expert.getName());
        model.addAttribute("expert",expert);
        model.addAttribute("msg",msg);
        return "personnel/viewExpert";//这个地方还可以改进，返回一个弹窗页面
    }
    @GetMapping("updateExpertForm")//这个是在专家资料界面点击更新时的操作
    public String updateExpertForm(String name,Model model){
        Expert expert = managerService.getExpertByName(name);
        model.addAttribute("expert",expert);
        return "personnel/updateExpert";
    }
    @PostMapping("updateExpert")//这个是在修改专家资料界面点击保存时的操作
    public String updateExpert(Expert expert,Model model){
        personnelService.updateExpert(expert);
        expert = managerService.getExpertByName(expert.getName());
        model.addAttribute("expert",expert);
        return "personnel/viewExpert";
    }
    @GetMapping("deleteExpert")//这个是在专家资料点击某个专家后面的删除时的操作
    public String deleteExpert(String name,Model model){
        Expert expert = managerService.getExpertByName(name);
        personnelService.deleteExpert(expert);
        List<Expert> expertList = managerService.getExpertList();
        model.addAttribute("expertList",expertList);
        return "personnel/expertList";
    }
    @GetMapping("executeForm")
    public String executeForm(String theme,Model model){
        Lecture lecture = managerService.getLectureByTheme(theme);
        model.addAttribute("lecture",lecture);
        return "personnel/execute";
    }
    @PostMapping("execute")
    public String execute(Lecture lecture,Model model) throws GeneralSecurityException {
        //发送邮件功能!!!!!!暂时没有完成
//        Properties properties = new Properties();
//        properties.setProperty("mail.host","smtp.qq.com");//设置QQ浏览器
//        properties.setProperty("mail.transport.protocol","smtp");//邮件发送协议
//        properties.setProperty("mail.smtp.auth","true");//需要验证用户名密码
//        //关于QQ邮箱，还要设置SSL加密，加上以下代码即可
//        MailSSLSocketFactory sf = new MailSSLSocketFactory();
//        sf.setTrustAllHosts(true);
//        properties.put("mail.smtp.ssl.enable","true");
//        properties.put("mail.smtp.ssl.socketFactory",sf);
//        //1、创建一个session会话对象
//        Session session = Session.getDefaultInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("1484501121@qq.com", "urhjhaphvjbjiafe");
//
//            }
//        });
//        session.setDebug(true);
//        //2.获取连接对象，通过session对象获得transport，需要铺货异常
//        Transport tp = session.getTransport();
//        //3.使用邮箱的用户名和授权码连上邮件服务器
//        tp.connect("smtp.qq.com","1484501121@qq.com","urhjhaphvjbjiafe");
//        //4.创建邮件
//        //创建邮件对象
//        MimeMessage message = new MimeMessage(session);
//        //指明邮件发送人
//        message.setFrom(new InternetAddress("1484501121@qq.com"));
//        //指明邮件的收件人
//        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("1484501121@qq.com")});
//        //邮件的标题
//        message.setSubject("只包含文本的简单邮件");
//        //邮件的文本内容
//        message.setContent("你好啊","text/html;charset=utf-8");
//        //发送邮件
//        tp.sendMessage(message,message.getAllRecipients());
//
//        tp.close();
        

        return "personnel/execute";
    }

}
