package com.testdev.platform.dao;

import com.testdev.platform.domain.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface DeleteRepositroy extends CrudRepository<Bill, Integer> {

    @Override
    void delete(Bill bill);

    @Override
    void deleteById(Integer integer);

    @Override
    void deleteAll();

    @Override
    void deleteAll(Iterable<? extends Bill> iterable);
}
