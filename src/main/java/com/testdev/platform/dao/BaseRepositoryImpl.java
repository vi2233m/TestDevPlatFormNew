package com.testdev.platform.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.*;

@Repository
public class BaseRepositoryImpl <T, ID extends Serializable>  implements BaseRepository<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public List<T> findByMoreFiled(String tablename,LinkedHashMap<String,Object> map) {
        String sql="from "+tablename+" u WHERE ";
        Set<String> set=null;
        set=map.keySet();
        List<String> list=new ArrayList<>(set);
        List<Object> filedlist=new ArrayList<>();
        for (String filed:list){
            sql+="u."+filed+"=? and ";
            filedlist.add(filed);
        }
        sql=sql.substring(0,sql.length()-4);
        System.out.println(sql+"--------sql语句-------------");
        Query query=entityManager.createQuery(sql);
        for (int i=0;i<filedlist.size();i++){
            query.setParameter(i+1,map.get(filedlist.get(i)));
        }
        List<T> listRe= query.getResultList();
        entityManager.close();
        return listRe;
    }
    @Transactional
    @Override
    public List<T> findByMoreFiledpages(String tablename,LinkedHashMap<String,Object> map,int start,int pageNumber) {
        String sql="from "+tablename+" u WHERE ";
        Set<String> set=null;
        set=map.keySet();
        List<String> list=new ArrayList<>(set);
        List<Object> filedlist=new ArrayList<>();
        for (String filed:list){
            sql+="u."+filed+"=? and ";
            filedlist.add(filed);
        }
        sql=sql.substring(0,sql.length()-4);
        System.out.println(sql+"--------sql语句-------------");
        Query query=entityManager.createQuery(sql);
        for (int i=0;i<filedlist.size();i++){
            query.setParameter(i+1,map.get(filedlist.get(i)));
        }
        query.setFirstResult((start-1)*pageNumber);
        query.setMaxResults(pageNumber);
        List<T> listRe= query.getResultList();
        entityManager.close();
        return listRe;
    }
}

