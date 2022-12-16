package cn.edu.usst.onlineqaa.controller;

import cn.edu.usst.onlineqaa.bean.vo.CourseVO;
import cn.edu.usst.onlineqaa.bean.vo.UserInfoVO;
import cn.edu.usst.onlineqaa.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CourseController {
    @Autowired
    CourseService courseService;
    @RequestMapping(value = "/course/getcourses",method = RequestMethod.GET)
    public List<CourseVO> getCourses(String keyWord){
        return courseService.getCourses(keyWord);
    }
    @RequestMapping(value = "/course/select",method = RequestMethod.POST)
    public String selectCourse(@RequestBody List<Integer> courses, HttpSession session){
        UserInfoVO userinfo = (UserInfoVO) session.getAttribute("userinfo");
        if(userinfo.getCourseSelected() == 1){
            return "无法重复选课";
        }
        else{
            courseService.selectCourse(courses, userinfo.getId());
            session.setAttribute("userinfo",new UserInfoVO(userinfo.getId(), 0,1));
            return "选课成功";
        }
    }
    @RequestMapping(value = "/course/getselectedcourses",method = RequestMethod.GET)
    public List<Integer> getSelectedCourses(HttpSession session){
        UserInfoVO userinfo = (UserInfoVO)session.getAttribute("userinfo");
        return courseService.getSelectedCourses(userinfo.getId());
    }
}
