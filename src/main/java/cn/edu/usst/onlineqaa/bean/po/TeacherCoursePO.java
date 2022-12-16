package cn.edu.usst.onlineqaa.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("TEACHER_COURSE")
public class TeacherCoursePO {
    @TableId(type = IdType.AUTO)
    Integer id;
    @TableField
    Integer teacherId;
    @TableField
    Integer courseId;
}
