package com.jjh.druid.task;

import com.alibaba.fastjson.JSON;
import com.jjh.druid.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class DataTask {

    @Autowired
    SessionFactory sessionFactory;

    Logger logger = LoggerFactory.getLogger(DataTask.class);

    @Transactional
//    @Scheduled(cron = "* 2 * * * *")
    @Scheduled(fixedRate = 5000)
    public void writeData() {
        Session session = sessionFactory.getCurrentSession();

        String json = "{name:'jjh "+new Date().getTime()+"'}";

        logger.info("[TASK] [DATA]"+json);

        User user = JSON.parseObject(json, User.class);
        session.save(user);
    }

}
