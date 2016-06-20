package com.app.repository;

import com.app.domain.Account;
import com.app.domain.Link;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by user1 on 10/06/2016.
 */
public interface LinkRepository extends PagingAndSortingRepository<Link,Integer> {
    List<Link> findAll();
    void delete(Integer linkId);
    List<Link> findAllByAccount(Account account, Sort sort);
    List<Link> findAllByAccount(Account account, Pageable pageable);
}
