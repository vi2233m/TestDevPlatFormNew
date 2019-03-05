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
    public boolean save(T entity){
        boolean flag=false;
        try {
            entityManager.persist(entity);
            flag=true;
        }catch (Exception e){
            System.out.println("---------------保存出错---------------");
            throw e;
        }
        return flag;
    }

    @Transactional
    @Override
    public List<T> findByMoreFiled(String tablename,LinkedHashMap<String,Object> map) {
        String sql="from "+tablename+" u WHERE ";
        Set<String> set=null;
        set=map.keySet();
        List<String> list=new ArrayList<>(set);
        List<Object> filedlist=new ArrayList<>();
        for (int i=0; i<list.size(); i++ ){
            String filed = list.get(i);
            sql+="u."+filed+" like ?"+(i+1)+" and ";
            filedlist.add(filed);
        }
        sql=sql.substring(0,sql.length()-4);
        System.out.println("filelist is :" +filedlist);
        System.out.println(sql+"--------sql语句-------------");
        Query query=entityManager.createQuery(sql);
        for (int i=0;i<filedlist.size();i++){
//            String value1 = filedlist.get(i).toString();
//            String value2 = map.get(filedlist.get(i)).toString();
//            System.out.println("value: " + map.get(filedlist.get(i)));
            query.setParameter(i+1,"%"+map.get(filedlist.get(i)).toString()+"%");
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
        for (int i=0; i<list.size(); i++ ){
            String filed = list.get(i);
            sql+="u."+filed+" like ?"+(i+1)+" and ";
            filedlist.add(filed);
        }
        sql=sql.substring(0,sql.length()-4);
        System.out.println(sql+"--------sql语句-------------");
        Query query=entityManager.createQuery(sql);
        for (int i=0;i<filedlist.size();i++){
            query.setParameter(i+1,"%"+map.get(filedlist.get(i))+"%");
        }
        query.setFirstResult((start-1)*pageNumber);
        query.setMaxResults(pageNumber);
        List<T> listRe= query.getResultList();
        entityManager.close();
        return listRe;
    }

    @Transactional
    @Override
    public Object findCount(String tablename, LinkedHashMap<String, Object> map) {
        String sql="select count(u) from "+tablename+" u WHERE ";
        Set<String> set=null;
        set=map.keySet();
        List<String> list=new ArrayList<>(set);
        List<Object> filedlist=new ArrayList<>();
        for (int i=0; i<list.size(); i++ ){
            String filed = list.get(i);
            sql+="u."+filed+" like ?"+(i+1)+" and ";
            filedlist.add(filed);
        }
        sql=sql.substring(0,sql.length()-4);
        System.out.println(sql+"--------sql语句-------------");
        Query query=entityManager.createQuery(sql);
        for (int i=0;i<filedlist.size();i++){
            query.setParameter(i+1,"%"+map.get(filedlist.get(i))+"%");
        }
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public Object findByid(Object o,int id) {
        return entityManager.find(o.getClass(),id);
    }

    @Transactional
    @Override
    public boolean delete(T entity) {
        boolean flag=false;
        try {
            entityManager.remove(entityManager.merge(entity));
            flag=true;
        }catch (Exception e){
            System.out.println("---------------删除出错---------------");
        }
        return flag;
    }

    @Transactional
    @Override
    public boolean update(T entity) {
        boolean flag = false;
        try {
            entityManager.merge(entity);
            flag = true;
        } catch (Exception e) {
            System.out.println("---------------更新出错---------------");
        }
        return flag;
    }
}

