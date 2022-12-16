package cn.edu.usst.onlineqaa.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CourseVO {
    Integer id;
    String name;
    String time;
    String description;
    String teacherName;
    Integer rate;
}
