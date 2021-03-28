package org.csu.mywork1.service;

import org.csu.mywork1.domain.Expert;
import org.csu.mywork1.persistence.ExpertMapper;
import org.csu.mywork1.persistence.LectureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonnelService {
    @Autowired
    private ExpertMapper expertMapper;
    @Autowired
    private LectureMapper lectureMapper;

    public void addExpert(Expert expert){
        expertMapper.addExpert(expert);
    }
    @Transactional
    public void updateExpert(Expert expert){
        expertMapper.updateExpert(expert);
    }
    @Transactional
    public void deleteExpert(Expert expert){
        expertMapper.deleteExpert(expert);
    }
}
