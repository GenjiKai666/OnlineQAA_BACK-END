package cn.edu.usst.onlineqaa.service;

import cn.edu.usst.onlineqaa.bean.po.QuestionPO;
import cn.edu.usst.onlineqaa.bean.po.StudentCoursePO;
import cn.edu.usst.onlineqaa.bean.po.StudentCourseQuestionPO;
import cn.edu.usst.onlineqaa.bean.vo.QuestionVO;
import cn.edu.usst.onlineqaa.mapper.QuestionMapper;
import cn.edu.usst.onlineqaa.mapper.StudentCourseMapper;
import cn.edu.usst.onlineqaa.mapper.StudentCourseQuestionMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    StudentCourseMapper studentCourseMapper;
    @Autowired
    StudentCourseQuestionMapper studentCourseQuestionMapper;
    public int createQuestion(Integer courseId,Integer studentId,String info){
        QuestionPO questionPO = new QuestionPO(null,
                new Date(),
                null,
                info,
                null);
        questionMapper.insert(questionPO);
        Integer questionId = questionPO.getId();
        StudentCoursePO studentCoursePO = studentCourseMapper.selectOne(Wrappers
                .lambdaQuery(StudentCoursePO.class)
                .eq(StudentCoursePO::getCourseId,courseId)
                .eq(StudentCoursePO::getStudentId,studentId));
        studentCourseQuestionMapper.insert(new StudentCourseQuestionPO(null,studentCoursePO.getId(),questionId));
        return 1;
    }
    public List<QuestionVO> getQuestionsByCourseId(Integer courseId){
        List<QuestionVO> questionVOS = new ArrayList<>();
        List<StudentCoursePO> studentCoursePOS = studentCourseMapper.selectList(Wrappers
                .lambdaQuery(StudentCoursePO.class)
                .eq(StudentCoursePO::getCourseId,courseId));
        for(StudentCoursePO studentCoursePO: studentCoursePOS){
            List<StudentCourseQuestionPO> studentCourseQuestionPOS = studentCourseQuestionMapper.selectList(Wrappers
                    .lambdaQuery(StudentCourseQuestionPO.class)
                    .eq(StudentCourseQuestionPO::getStudentCourseId,studentCoursePO.getId()));
            for(StudentCourseQuestionPO studentCourseQuestionPO:studentCourseQuestionPOS){
                QuestionPO questionPO = questionMapper.selectById(studentCourseQuestionPO.getQuestionId());
                if(questionPO.getAnswerTime() == null){
                    questionVOS.add(new QuestionVO(questionPO.getId(),
                            questionPO.getCreateTime().toString(),
                            null,
                            questionPO.getInfo(), questionPO.getAnswer()));
                }
                else{
                    questionVOS.add(new QuestionVO(questionPO.getId(),
                            questionPO.getCreateTime().toString(),
                            questionPO.getAnswerTime().toString(),
                            questionPO.getInfo(), questionPO.getAnswer()));
                }
            }
        }
        return questionVOS;
    }
    public int answerQuestion(Integer questionId,String answer){
        QuestionPO questionPO = questionMapper.selectById(questionId);
        if(questionPO == null){
            return 0;
        }
        questionPO.setAnswer(answer);
        questionPO.setAnswerTime(new Date());
        questionMapper.updateById(questionPO);
        return 1;
    }
}
