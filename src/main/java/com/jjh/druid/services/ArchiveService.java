package com.jjh.druid.services;

import com.jjh.druid.othermodels.Archive;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArchiveService {

    @Autowired
    @Qualifier(value = "secondarySessionFactory")       //使用第二个数据源
    private SessionFactory sessionFactory;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public void save(Archive archive){
        Session session = sessionFactory.getCurrentSession();
        //使用session
        session.save(archive);

//        String insertSql = "INSERT INTO ARCHIVE (INFO) VALUES ('"+archive.getINFO()+"')";
//        SQLQuery query = session.createSQLQuery(insertSql);
//        query.executeUpdate();
    }

    @Transactional
    public List<Archive> find(String id){
        ArrayList<Archive> archives = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        //使用SQL查找
        String selectSql = "select * from ARCHIVE where id=:id";
        SQLQuery query = session.createSQLQuery(selectSql);
        query.setParameter("id",id);
        List<Object[]> objectList = query.list();
        for (Object[] objects : objectList) {
            Archive archive = new Archive();
            archive.setID(String.valueOf(objects[0]));
            archive.setINFO(String.valueOf(objects[1]));
            archives.add(archive);
        }
        return archives;
    }
}
