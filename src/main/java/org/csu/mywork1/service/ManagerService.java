package org.csu.mywork1.service;

import org.csu.mywork1.domain.Expert;
import org.csu.mywork1.domain.Lecture;
import org.csu.mywork1.domain.SignUp;
import org.csu.mywork1.persistence.ExpertMapper;
import org.csu.mywork1.persistence.LectureMapper;
import org.csu.mywork1.persistence.SignUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private ExpertMapper expertMapper;
    @Autowired
    private LectureMapper lectureMapper;
    @Autowired
    private SignUpMapper signUpMapper;

    public Expert getExpertByName(String name){
        return expertMapper.getExpertByName(name);
    }
    public Lecture getLectureByTheme(String theme){
        return lectureMapper.getLectureByTheme(theme);
    }
    public List<Expert> getExpertList(){
        return expertMapper.getExpertList();
    }
    //将上面的List类型改为PageInfo类型，并在函数参数中添加分页大小和当前页面参数

    public List<Lecture> getLectureList(){
        return lectureMapper.getLectureList();
    }
    public List<Expert> searchExpertList(String keyword){
        return expertMapper.searchExpertList("%"+keyword.toLowerCase()+"%");
    }
    public List<Lecture> searchLectureList(String keyword){
         return lectureMapper.searchLectureList("%"+keyword.toLowerCase()+"%");
    }
    public int yesCount(){//在所有的讲座中获取已经讲过的讲座数量
        return lectureMapper.YesCount();
    }
    public int noCount(){//在所有的讲座中获取还没有讲过的讲座数量
        return lectureMapper.NoCount();
    }

    public void addLecture(Lecture lecture){
        lectureMapper.addLecture(lecture);
    }
    public void addSignUp(SignUp signUp){
        signUpMapper.addSignUp(signUp);
    }
    @Transactional
    public void deleteLecture(Lecture lecture){
        lectureMapper.deleteLecture(lecture);
    }
    @Transactional
    public void updateLecture(Lecture lecture){
        lectureMapper.updateLecture(lecture);
    }


}
