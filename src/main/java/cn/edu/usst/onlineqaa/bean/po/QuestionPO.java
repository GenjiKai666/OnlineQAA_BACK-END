package cn.edu.usst.onlineqaa.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("QUESTION")
public class QuestionPO {
    @TableId(type = IdType.AUTO)
    Integer id;
    @TableField
    Date createTime;
    @TableField
    Date answerTime;
    @TableField
    String info;
    @TableField
    String answer;
}
