package com.lqx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqx.edu.dto.TeacherQuery;
import com.lqx.edu.entity.Teacher;
import com.lqx.edu.mapper.TeacherMapper;
import com.lqx.edu.service.TeacherService;
import com.lqx.edu.vo.TeacherOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Bruce
 * @since 2020-04-23
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void pageQuery(Page<Teacher> page, TeacherQuery teacherQuery) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByAsc("sort");
        if (StringUtils.isEmpty(teacherQuery)) {
            baseMapper.selectPage(page, teacherQueryWrapper);
            return;
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
//        模糊查询
        if (!StringUtils.isEmpty(name)) {
            teacherQueryWrapper.like("name", name);
        }
//        精准查询
        if (!StringUtils.isEmpty(level)) {
            teacherQueryWrapper.eq("level", level);
        }
//        ge:大于等于 开始时间 gmt_create:数据库表字段
        if (!StringUtils.isEmpty(begin)) {
            teacherQueryWrapper.ge("gmt_create", begin);
        }
//        le:小于等于<= 结束时间
        if (!StringUtils.isEmpty(begin)) {
            teacherQueryWrapper.ge("gmt_create", end);
        }
        baseMapper.selectPage(page, teacherQueryWrapper);
    }

    @Override
    public Integer countLevel(Integer level) {
        QueryWrapper<Teacher> levelCount = new QueryWrapper<Teacher>().eq("level", level);
        Integer count = baseMapper.selectCount(levelCount);
        return count;
    }

    @Override
    public List<TeacherOutput> selectTeacherOutput() {
        List<TeacherOutput> teacherOutput = teacherMapper.selectTeacherOutput();
        return teacherOutput;
    }
}
