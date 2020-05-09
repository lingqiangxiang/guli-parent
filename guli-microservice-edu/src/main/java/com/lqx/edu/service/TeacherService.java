package com.lqx.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lqx.edu.dto.TeacherQuery;
import com.lqx.edu.entity.Teacher;
import com.lqx.edu.vo.TeacherOutput;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Bruce
 * @since 2020-04-23
 */
public interface TeacherService extends IService<Teacher> {
//    分页条件查询
    void pageQuery(Page<Teacher> page, TeacherQuery teacherQuery);

    Integer countLevel(Integer level);

    List<TeacherOutput> selectTeacherOutput();
}
