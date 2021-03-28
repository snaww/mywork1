package org.csu.mywork1.persistence;

import org.csu.mywork1.domain.Expert;
import org.csu.mywork1.domain.Lecture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureMapper {

    Lecture getLectureByTheme(String theme);//这个用于获取某个讲座的详细信息
    List<Lecture> getLectureList();//这个用于获取所有的讲座信息
    int YesCount();//这个用于统计所有讲座中已经讲过的场数
    int NoCount();//这个用于统计所有讲座中待讲的场数
    List<Lecture> searchLectureList(String keyword);

    void addLecture(Lecture lecture);//这个用于增加一个新的讲座计划
    void updateLecture(Lecture lecture);//这个用于修改讲座的信息
    void deleteLecture(Lecture lecture);//这个用于删除一个讲座
}
