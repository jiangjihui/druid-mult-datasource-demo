package com.jjh.druid.services;

import com.jjh.druid.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    SessionFactory sessionFactory;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public void save(User user){
        Session session = sessionFactory.getCurrentSession();
        if (!session.isOpen()) {
            System.out.println("session is open");
            return;
        }
        session.save(user);
    }

}
