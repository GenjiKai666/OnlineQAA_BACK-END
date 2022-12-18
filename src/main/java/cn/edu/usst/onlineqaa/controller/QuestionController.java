package cn.edu.usst.onlineqaa.controller;

import cn.edu.usst.onlineqaa.bean.po.StudentCoursePO;
import cn.edu.usst.onlineqaa.bean.po.TeacherCoursePO;
import cn.edu.usst.onlineqaa.bean.vo.QuestionVO;
import cn.edu.usst.onlineqaa.bean.vo.UserInfoVO;
import cn.edu.usst.onlineqaa.mapper.StudentCourseMapper;
import cn.edu.usst.onlineqaa.mapper.TeacherCourseMapper;
import cn.edu.usst.onlineqaa.service.QuestionService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    StudentCourseMapper studentCourseMapper;
    @Autowired
    TeacherCourseMapper teacherCourseMapper;
    @RequestMapping(value = "/question/createquestion",method = RequestMethod.GET)
    public int createQuestion(Integer courseId,String info, HttpSession session){
        UserInfoVO userinfo = (UserInfoVO) session.getAttribute("userinfo");
        if(studentCourseMapper.exists(Wrappers.lambdaQuery(StudentCoursePO.class)
                .eq(StudentCoursePO::getCourseId,courseId)
                .eq(StudentCoursePO::getStudentId,userinfo.getId()))){
            questionService.createQuestion(courseId, userinfo.getId(), info);
            return 1;
        }
        else{
            return 0;
        }
    }
    @RequestMapping(value = "/question/getquestionsbycourseid",method = RequestMethod.GET)
    public List<QuestionVO> getQuestionsByCourseId(Integer courseId){
        return questionService.getQuestionsByCourseId(courseId);
    }
    @RequestMapping(value = "/question/answer",method = RequestMethod.GET)
    public int answer(Integer courseId,Integer questionId,String answer,HttpSession session){
        UserInfoVO userInfo = (UserInfoVO) session.getAttribute("userinfo");
        if(teacherCourseMapper.exists(Wrappers.lambdaQuery(TeacherCoursePO.class)
                .eq(TeacherCoursePO::getCourseId,courseId)
                .eq(TeacherCoursePO::getTeacherId,userInfo.getId()))){
            questionService.answerQuestion(questionId,answer);
            return 1;
        }
        else{
            return 0;
        }
    }
}
