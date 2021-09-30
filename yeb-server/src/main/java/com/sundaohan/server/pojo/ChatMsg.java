package com.sundaohan.server.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.pojo
 * @Title ChatMsg
 * @Description TODO
 * @Date 2021/8/5 下午1:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {

    private String from;
    private String to;
    private String content;
    private LocalDateTime date;
    private String formNickName;
}
