package com.lqx.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lqx.common.exception.GuliException;
import com.lqx.common.util.ResponseResault;
import com.lqx.common.util.ResultCodeEnum;
import com.lqx.edu.dto.TeacherQuery;
import com.lqx.edu.entity.Teacher;
import com.lqx.edu.service.TeacherService;
import com.lqx.edu.vo.TeacherOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Bruce
 * @since 2020-04-23
 */
@RestController
@RequestMapping("/edu/teacher")
//@Api(value = "老师controller", tags = {"老师信息操作接口"})
@Api(description = "讲师管理")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //{"code":20000,"data":{"token":"admin"}}
    //模拟登陆
    @PostMapping("login")
    public ResponseResault login() {
        return ResponseResault.ok().data("token","admin");
    }

    //{"code":20000,"data":{"roles":["admin"],"name":"admin","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
    @GetMapping("info")
    public ResponseResault info() {
        return ResponseResault.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @ResponseBody
    @GetMapping("/allTeacher")
    //option的value的内容是这个method的描述，notes是详细描述，response是最终返回的json model。其他可以忽略
    @ApiOperation(value = "获取所有老师信息")
    public ResponseResault allTeacher() {
        List<Teacher> list = teacherService.list(null);
        return ResponseResault.ok().data("list", list);
    }

    @DeleteMapping("/deleteTeacherById/{id}")//逻辑删除
    @ApiOperation(value = "根据id删除老师")
    public ResponseResault deleteTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        return teacherService.removeById(id) == true ? ResponseResault.ok() : ResponseResault.error();
    }

    @GetMapping("/pageList/{page}/{limit}")
    @ApiOperation(value = "分页查询老师")
    public ResponseResault pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit
    ) {
        Page<Teacher> teacherPage = new Page<>(page, limit);
        teacherService.page(teacherPage, null);
        List<Teacher> teachers = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        return ResponseResault.ok().data("total", total).data("data", teachers);
    }

    /**
     * @RequestBody:必须使用 PostMapping, 使用GetMapping获取不到json数据
     */
    @PostMapping("/pageQueryTeacher/{page}/{limit}")
    @ApiOperation(value = "分页条件查询老师")
    public ResponseResault pageQueryTeacher(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "条件查询条件", required = false)
            @RequestBody TeacherQuery teacherQuery
    ) {
        if(page <= 0 || limit <= 0){
            //使用自定义异常
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }
        Page<Teacher> teacherPage = new Page<>(page, limit);
        teacherService.pageQuery(teacherPage, teacherQuery);
        List<Teacher> teachers = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        return ResponseResault.ok().data("total", total).data("data", teachers);
    }

    @PostMapping("/addTeacher")
    @ApiOperation(value = "添加老师")
    public ResponseResault addTeacher(@RequestBody Teacher teacher) {
        if (teacherService.save(teacher)) {
            return ResponseResault.ok();
        }
        return ResponseResault.error();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("/getTeacherById/{id}")
    public ResponseResault getTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if (!StringUtils.isEmpty(teacher)) {
            return ResponseResault.ok().data("data", teacher);
        }
        return ResponseResault.error();
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("/updateById/{id}")
    public ResponseResault updateById(
            @ApiParam(name = "id", value = "老师ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher
    ) {
        teacher.setId(id);
        teacherService.updateById(teacher);
        return ResponseResault.ok();
    }

    @ApiOperation(value = "查询等级的个数")
    @GetMapping("/countLevel/{level}")
    public ResponseResault countAge(
            @ApiParam(name = "level", value = "等级", required = true)
            @PathVariable Integer level
    ) {
        Integer count=teacherService.countLevel(level);
        return ResponseResault.ok().data("data",count);
    }

    @ApiOperation(value = "查询特定条件")
    @GetMapping("/selectTeacherOutput")
    public ResponseResault selectTeacherOutput() {
        List<TeacherOutput> teacherOutput = teacherService.selectTeacherOutput();
        return ResponseResault.ok().data("data",teacherOutput);
    }
}

