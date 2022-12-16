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
@TableName("COURSE")
public class CoursePO {
    @TableId(type = IdType.AUTO)
    Integer id;
    @TableField
    String time;
    @TableField
    String description;
    @TableField
    String rate;
}
