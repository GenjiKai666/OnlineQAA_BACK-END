package cn.edu.usst.onlineqaa.controller;

import cn.edu.usst.onlineqaa.bean.vo.UserInfoVO;
import cn.edu.usst.onlineqaa.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @RequestMapping(value = "/question/createquestion",method = RequestMethod.POST)
    public int createQuestion(Integer courseId,String info, HttpSession session){
        UserInfoVO userinfo = (UserInfoVO) session.getAttribute("userinfo");
        questionService.createQuestion(courseId, userinfo.getId(), info);
        return 1;
    }
}
