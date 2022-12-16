package cn.edu.usst.onlineqaa.controller;

import cn.edu.usst.onlineqaa.bean.dto.LoginDTO;
import cn.edu.usst.onlineqaa.bean.dto.RegisterDTO;
import cn.edu.usst.onlineqaa.bean.po.StudentCoursePO;
import cn.edu.usst.onlineqaa.bean.po.StudentPO;
import cn.edu.usst.onlineqaa.bean.po.TeacherPO;
import cn.edu.usst.onlineqaa.bean.vo.UserInfoVO;
import cn.edu.usst.onlineqaa.mapper.StudentCourseMapper;
import cn.edu.usst.onlineqaa.mapper.StudentMapper;
import cn.edu.usst.onlineqaa.mapper.TeacherMapper;
import cn.edu.usst.onlineqaa.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    StudentCourseMapper studentCourseMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @RequestMapping(value = "/user/studentregister",method = RequestMethod.POST)
    public int studentRegister(@RequestBody RegisterDTO registerDTO){
        return userService.studentRegister(registerDTO);
    }
    @RequestMapping(value = "/user/studentlogin",method = RequestMethod.POST)
    public int studentLogin(@RequestBody LoginDTO loginDTO, HttpSession session){
        Integer flag = userService.studentLogin(loginDTO);
        if(flag != 0){
            StudentPO studentPO = studentMapper.selectById(flag);
            if(studentCourseMapper.exists(Wrappers.lambdaQuery(StudentCoursePO.class).eq(StudentCoursePO::getStudentId,flag))){
                session.setAttribute("userinfo",new UserInfoVO(flag,
                        studentPO.getUsername(),
                        studentPO.getStudentId(),
                        0,
                        1));
            }
            else{
                session.setAttribute("userinfo",new UserInfoVO(flag,
                        studentPO.getUsername(),
                        studentPO.getStudentId(),
                        0,
                        0));
            }
            return 1;
        }
        else{
            return 0;
        }
    }
    @RequestMapping(value = "/user/teacherlogin",method = RequestMethod.POST)
    public int teacherLogin(@RequestBody LoginDTO loginDTO, HttpSession session){
        Integer flag = userService.teacherLogin(loginDTO);
        if(flag != 0){
            TeacherPO teacherPO = teacherMapper.selectById(flag);
            session.setAttribute("userinfo",new UserInfoVO(flag, teacherPO.getUsername(), null,1,0));
            return 1;
        }
        else{
            return 0;
        }
    }
    @RequestMapping(value = "/user/getuserinfo",method = RequestMethod.GET)
    public UserInfoVO getUserInfo(HttpSession session){
        UserInfoVO userInfoVO = (UserInfoVO) session.getAttribute("userinfo");
        return userInfoVO;
    }
}
