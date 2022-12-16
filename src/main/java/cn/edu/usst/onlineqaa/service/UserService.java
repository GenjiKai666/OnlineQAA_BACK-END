package cn.edu.usst.onlineqaa.service;

import cn.edu.usst.onlineqaa.bean.dto.LoginDTO;
import cn.edu.usst.onlineqaa.bean.dto.RegisterDTO;
import cn.edu.usst.onlineqaa.bean.po.StudentPO;
import cn.edu.usst.onlineqaa.bean.po.TeacherPO;
import cn.edu.usst.onlineqaa.mapper.StudentMapper;
import cn.edu.usst.onlineqaa.mapper.TeacherMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    public int studentRegister(RegisterDTO registerDTO){
        if(studentMapper.exists(Wrappers.lambdaQuery(StudentPO.class)
                .eq(StudentPO::getUsername,registerDTO.getUsername()))){
            return 0;
        }
        else{
            studentMapper.insert(new StudentPO(null,
                    registerDTO.getStudent_id(),
                    registerDTO.getUsername(),
                    registerDTO.getPassword()));
            return 1;
        }
    }
    public int studentLogin(LoginDTO loginDTO){
        StudentPO studentPO = studentMapper.selectOne(Wrappers
                .lambdaQuery(StudentPO.class)
                .eq(StudentPO::getUsername,loginDTO.getUsername()));
        if(studentPO != null && studentPO.getPassword().equals(loginDTO.getPassword())){
            return studentPO.getId();
        }
        return 0;
    }
    public int teacherLogin(LoginDTO loginDTO){
        TeacherPO teacherPO = teacherMapper.selectOne(Wrappers
                .lambdaQuery(TeacherPO.class)
                .eq(TeacherPO::getUsername,loginDTO.getUsername()));
        if(teacherPO != null && teacherPO.getPassword().equals(loginDTO.getPassword())){
            return teacherPO.getId();
        }
        return 0;
    }
}
