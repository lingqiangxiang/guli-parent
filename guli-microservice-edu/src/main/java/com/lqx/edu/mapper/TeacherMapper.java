package com.lqx.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqx.edu.entity.Teacher;
import com.lqx.edu.vo.TeacherOutput;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author Bruce
 * @since 2020-04-23
 */
@Mapper
@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {

    List<TeacherOutput> selectTeacherOutput();

}
