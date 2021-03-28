package org.csu.mywork1.controller;

import org.csu.mywork1.domain.Expert;
import org.csu.mywork1.domain.Lecture;
import org.csu.mywork1.domain.SignUp;
import org.csu.mywork1.service.AccountService;
import org.csu.mywork1.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/manager/")
@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private AccountService accountService;

//    @RequestMapping("type")
//    public String type(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
//        req.setCharacterEncoding("utf-8");
//        rsp.setCharacterEncoding("utf-8");
//        List<Account> personnelList = accountService.getPersonnelList();
//        PrintWriter out = rsp.getWriter();
//        out.print(JSON.toJSONString);
//    }

    @GetMapping("viewExpertList")//这个是登陆成功进入manager的专家列表界面或者在其他地方进入专家列表时的操作
    public String viewExpertList(Model model){
        List<Expert> expertList = managerService.getExpertList();
        model.addAttribute("expertList",expertList);
        return "manager/expertList";
    }
    @PostMapping("searchExpert")//这个是在manager的专家列表界面点击搜索的操作
    public String searchExpert(String keyword,Model model){
        List<Expert> expertList = managerService.searchExpertList(keyword.toLowerCase());
        model.addAttribute("expertList",expertList);
        return "manager/expertList";
    }
    @PostMapping("searchLecture")//这个是搜索讲座
    public String searchLecture(String keyword,Model model){
        List<Lecture> lectureList = managerService.searchLectureList(keyword.toLowerCase());
        model.addAttribute("lectureList",lectureList);
        return "manager/lectureList";
    }
    @GetMapping("viewExpert")//这个是在专家列表中点每个专家后面的详情时的操作
    public String viewExpert(String name,Model model){
        Expert expert = managerService.getExpertByName(name);
        model.addAttribute("expert",expert);
        return "manager/viewExpert";
    }
    @GetMapping("viewLecture")
    public String viewLecture(String theme,Model model){
        Lecture lecture = managerService.getLectureByTheme(theme);
        model.addAttribute("lecture",lecture);
        return "manager/viewLecture";
    }

    @GetMapping("viewLectureList")
    public String viewLectureList(Model model){//这是在点击讲座计划时的操作
        List<Lecture> lectureList = managerService.getLectureList();
        int yesCount = managerService.yesCount();
        int noCount = managerService.noCount();
        model.addAttribute("lectureList",lectureList);
        model.addAttribute("yesCount",yesCount);
        model.addAttribute("noCount",noCount);
        return "manager/lectureList";
    }
    @GetMapping("deleteLecture")//执行删除的操作
    public String deleteLecture(String theme,Model model){
        Lecture lecture = managerService.getLectureByTheme(theme);
        managerService.deleteLecture(lecture);
        List<Lecture> lectureList = managerService.getLectureList();
        int yesCount = managerService.yesCount();
        int noCount = managerService.noCount();
        model.addAttribute("lectureList",lectureList);
        model.addAttribute("yesCount",yesCount);
        model.addAttribute("noCount",noCount);
        return "manager/lectureList";
    }
    @GetMapping("addLectureForm")//这个是点击增加讲座计划的操作，就是加载到增加讲座的页面
    public String addLectureForm(Model model){
        return "manager/addLecture";
    }
    @PostMapping("addLecture")//这个是点击增加讲座计划页面中的保存时的操作
    public String addLecture(Lecture lecture,Model model){
        String msg = "增加成功";
        managerService.addLecture(lecture);
        model.addAttribute("lecture",lecture);
        model.addAttribute("msg",msg);
        return "manager/addLecture";
    }
    @GetMapping("updateLectureForm")//这个是点击修改时的操作，就是加载到修改讲座的页面
    public String updateLectureForm(String theme,Model model){
        Lecture lecture = managerService.getLectureByTheme(theme);//总是无法获得lecture
        model.addAttribute(lecture);
        return "manager/updateLecture";
    }

    @PostMapping("/updateLecture")//这个是点击修改讲座计划界面中的保存时的操作
    public String updateLecture(Lecture lecture,Model model){
        String msg="修改成功";
//        Lecture lecture = catalogService.getLectureByTheme(oldTheme);//获取修改之前的lecture
        managerService.updateLecture(lecture);
        lecture = managerService.getLectureByTheme(lecture.getTheme());
        model.addAttribute("lecture",lecture);
        model.addAttribute("msg",msg);
        return "manager/updateLecture";
    }
    @PostMapping("signUpTheme")
    public String signUpTheme(SignUp signUp,Model model){
        String msg="报名成功";
        managerService.addSignUp(signUp);
        return "signUp";
    }
}
