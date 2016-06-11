package com.app.repository;

import com.app.domain.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by user1 on 10/06/2016.
 */
public interface AccountRepository extends CrudRepository<Account,Integer> {
    Account save(Account account);
    Account findOneByEmail(String email);
    Account findOneByEmailAndPassword(String email,String password);
}
