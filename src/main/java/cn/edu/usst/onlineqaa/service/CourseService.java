package cn.edu.usst.onlineqaa.service;

import cn.edu.usst.onlineqaa.bean.po.CoursePO;
import cn.edu.usst.onlineqaa.bean.po.StudentCoursePO;
import cn.edu.usst.onlineqaa.bean.po.TeacherCoursePO;
import cn.edu.usst.onlineqaa.bean.vo.CourseVO;
import cn.edu.usst.onlineqaa.mapper.CourseMapper;
import cn.edu.usst.onlineqaa.mapper.StudentCourseMapper;
import cn.edu.usst.onlineqaa.mapper.TeacherCourseMapper;
import cn.edu.usst.onlineqaa.mapper.TeacherMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    TeacherCourseMapper teacherCourseMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentCourseMapper studentCourseMapper;
    public List<CourseVO> getCourses(){
        List<CourseVO> courseVOS = new ArrayList<>();
        List<TeacherCoursePO> teacherCoursePOS = teacherCourseMapper.selectList(null);
        for(TeacherCoursePO teacherCoursePO:teacherCoursePOS){
            CoursePO coursePO = courseMapper.selectById(teacherCoursePO.getCourseId());
            String teacherName = teacherMapper.selectById(teacherCoursePO.getTeacherId()).getUsername();
            courseVOS.add(new CourseVO(coursePO.getId(),
                    coursePO.getName(),
                    coursePO.getTime(),
                    coursePO.getDescription(),
                    teacherName,
                    coursePO.getRate()));
        }
        return courseVOS;
    }
    public void selectCourse(List<Integer> course,Integer studentId){
        for(Integer i:course){
            studentCourseMapper.insert(new StudentCoursePO(null,studentId,i));
        }
    }
}
