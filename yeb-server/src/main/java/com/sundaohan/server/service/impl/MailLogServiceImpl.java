package com.sundaohan.server.service.impl;

import com.sundaohan.server.pojo.MailLog;
import com.sundaohan.server.mapper.MailLogMapper;
import com.sundaohan.server.service.IMailLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
