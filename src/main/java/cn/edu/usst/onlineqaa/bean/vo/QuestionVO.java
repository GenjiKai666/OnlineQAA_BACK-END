package cn.edu.usst.onlineqaa.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class QuestionVO {
    Integer id;
    String createTime;
    String answerTime;
    String info;
    String answer;
}
