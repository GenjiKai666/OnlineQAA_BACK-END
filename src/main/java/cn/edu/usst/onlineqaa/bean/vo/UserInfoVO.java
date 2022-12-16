package cn.edu.usst.onlineqaa.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserInfoVO {
    Integer id;
    String username;
    String studentId;   //  为老师时为空
    Integer isTeacher;
    Integer courseSelected; //仅服务于判定学生是否选课
}
