package org.csu.mywork1.persistence;

import org.csu.mywork1.domain.Expert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertMapper {
    List<Expert> getExpertList();//这个用于点击专家列表时显示所有的专家

    Expert getExpertByName(String name);//这个用于点击专家详情时显示该专家的详细信息

    List<Expert> searchExpertList(String keyword);

    void addExpert(Expert expert);//用于新增一个专家的信息
    void updateExpert(Expert expert);//用于更新一个专家的信息
    void deleteExpert(Expert expert);//用于删除一个专家的信息
}
