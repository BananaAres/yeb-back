package com.sundaohan.server.service.impl;

import com.sundaohan.server.pojo.Oplog;
import com.sundaohan.server.mapper.OplogMapper;
import com.sundaohan.server.service.IOplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
