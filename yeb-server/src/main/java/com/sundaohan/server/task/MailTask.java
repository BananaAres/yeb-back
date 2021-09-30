package com.sundaohan.server.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sundaohan.server.pojo.Employee;
import com.sundaohan.server.pojo.MailConstants;
import com.sundaohan.server.pojo.MailLog;
import com.sundaohan.server.service.IEmployeeService;
import com.sundaohan.server.service.IMailLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.task
 * @Title MailTask
 * @Description 邮件发送定时任务
 * @Date 2021/8/4 下午3:53
 */
@Component
public class MailTask {

    @Autowired
    private IMailLogService mailLogService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * @Title mailTask
     * @Description 邮件发送定时任务，十秒执行一次
     * @Author sundaohan
     * @Params []
     * @return void
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void mailTask(){
       List<MailLog> list = mailLogService.list(new QueryWrapper<MailLog>().eq("status",0).lt("tryTime", LocalDateTime.now()));
       //如果重试次数超过三次,更新状态为投递失败，不再重试
       list.forEach(mailLog -> {
           if(mailLog.getCount() >= 3){
               mailLogService.update(new UpdateWrapper<MailLog>().
                       set("status",2).
                       eq("msgId",mailLog.getMsgId()));
           }

           mailLogService.update(new UpdateWrapper<MailLog>().
                   set("count",mailLog.getCount()+1).
                   set("updateTime",LocalDateTime.now()).
                   set("tryTime",LocalDateTime.now().
                           plusMinutes(MailConstants.MSG_TIMEOUT)).eq("msgId",mailLog.getMsgId()));
           Employee emp = employeeService.getEmployee(mailLog.getEid()).get(0);
           rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                   MailConstants.MAIL_ROUTING_KEY_NAME,
                   emp,
                   new CorrelationData(mailLog.getMsgId()));
       });
    }

}
