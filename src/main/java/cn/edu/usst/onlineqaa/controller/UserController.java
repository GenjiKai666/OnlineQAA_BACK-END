package cn.edu.usst.onlineqaa.controller;

import cn.edu.usst.onlineqaa.bean.dto.LoginDTO;
import cn.edu.usst.onlineqaa.bean.dto.RegisterDTO;
import cn.edu.usst.onlineqaa.bean.vo.UserInfoVO;
import cn.edu.usst.onlineqaa.service.UserService;
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
    @RequestMapping(value = "/user/studentregister",method = RequestMethod.POST)
    public int studentRegister(@RequestBody RegisterDTO registerDTO){
        return userService.studentRegister(registerDTO);
    }
    @RequestMapping(value = "/user/studentlogin",method = RequestMethod.POST)
    public int studentLogin(@RequestBody LoginDTO loginDTO, HttpSession session){
        Integer flag = userService.studentLogin(loginDTO);
        if(flag != 0){
            session.setAttribute("userinfo",new UserInfoVO(flag,0));
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
            session.setAttribute("userinfo",new UserInfoVO(flag,1));
            return 1;
        }
        else{
            return 0;
        }
    }
    @RequestMapping(value = "/user/getuserinfo",method = RequestMethod.GET)
    public UserInfoVO getUserInfo(HttpSession session){
        return (UserInfoVO) session.getAttribute("userinfo");
    }
}