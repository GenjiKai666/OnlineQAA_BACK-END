package cn.edu.usst.onlineqaa.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RegisterDTO {
    String username;
    String student_id;
    String password;
}
