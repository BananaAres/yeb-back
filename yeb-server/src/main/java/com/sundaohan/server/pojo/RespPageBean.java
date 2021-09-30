package com.sundaohan.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.pojo
 * @Title RespPageBean
 * @Description TODO
 * @Date 2021/8/2 下午5:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据list
     */
    private List<?> data;
}
