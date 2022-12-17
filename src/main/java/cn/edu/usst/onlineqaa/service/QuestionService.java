package cn.edu.usst.onlineqaa.service;

import cn.edu.usst.onlineqaa.bean.po.QuestionPO;
import cn.edu.usst.onlineqaa.bean.po.StudentCoursePO;
import cn.edu.usst.onlineqaa.bean.po.StudentCourseQuestionPO;
import cn.edu.usst.onlineqaa.mapper.QuestionMapper;
import cn.edu.usst.onlineqaa.mapper.StudentCourseMapper;
import cn.edu.usst.onlineqaa.mapper.StudentCourseQuestionMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    StudentCourseMapper studentCourseMapper;
    @Autowired
    StudentCourseQuestionMapper studentCourseQuestionMapper;
    public int createQuestion(Integer courseId,Integer studentId,String info){
        Integer questionId = questionMapper.insert(new QuestionPO(null,
                new Date(),
                null,
                info,
                null));
        StudentCoursePO studentCoursePO = studentCourseMapper.selectOne(Wrappers
                .lambdaQuery(StudentCoursePO.class)
                .eq(StudentCoursePO::getCourseId,courseId)
                .eq(StudentCoursePO::getStudentId,studentId));
        studentCourseQuestionMapper.insert(new StudentCourseQuestionPO(null,studentCoursePO.getId(),questionId));
        return 1;
    }
}
