package cn.edu.usst.onlineqaa.mapper;

import cn.edu.usst.onlineqaa.bean.po.StudentPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<StudentPO> {
}
