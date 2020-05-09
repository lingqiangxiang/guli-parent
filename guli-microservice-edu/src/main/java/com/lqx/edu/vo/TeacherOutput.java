package com.lqx.edu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Bruce
 * @description:
 * @date: Created in 2020-04-26 20:31
 * @modified by:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherOutput implements Serializable {

    private static final long serialVersionUID = 1L;

    private String stname;

    private Integer aalevel;
}
