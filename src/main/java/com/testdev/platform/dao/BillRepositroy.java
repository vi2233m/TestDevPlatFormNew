package com.testdev.platform.dao;

import com.testdev.platform.dao.BaseRepository;
import com.testdev.platform.domain.Bill;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

@Service
public interface  BillRepositroy extends JpaRepository<Bill, Integer> {

    @Override
    List<Bill> findAll(Sort sort);

    @Override
    Bill getOne(Integer integer);

    @Override
    Optional<Bill> findById(Integer integer);

    @Override
    Page<Bill> findAll(Pageable pageable);

    @Override
    long count();

}
