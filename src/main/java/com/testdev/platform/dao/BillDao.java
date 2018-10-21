package com.testdev.platform.dao;

import com.testdev.platform.domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import org.springframework.util.StringUtils;

@Repository
public class BillDao {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

//    @Transactional(readOnly = true)

    public Bill searchBillByEm(String name, String type, String url, String header, int pageNo, int pageSize){
        StringBuffer jpql = new StringBuffer("from Bill w where 1=1 ");
        Map<String, Object> paramMap = new HashMap<>();
        if ( !StringUtils.isEmpty(name)){
            jpql.append(" and w.name = :name");
            paramMap.put("name", name);
        }
        if ( !StringUtils.isEmpty(type)){
            jpql.append(" and w.type = :type");
            paramMap.put("type", type);
        }
        if ( !StringUtils.isEmpty(url)){
            jpql.append(" and w.url = :url");
            paramMap.put("url", url);
        }
        if ( !StringUtils.isEmpty(header)){
            jpql.append(" and w.body like :header");
            paramMap.put("header", header);
        }

        Query query = entityManager.createQuery(jpql.toString());
        Set<String> keys = paramMap.keySet();
        for (String keyItem : keys) {
            query.setParameter(keyItem,paramMap.get(keyItem));
        }

        return (Bill)query.setFirstResult(pageNo * pageSize).setMaxResults(pageSize).getResultList();
    }
}
