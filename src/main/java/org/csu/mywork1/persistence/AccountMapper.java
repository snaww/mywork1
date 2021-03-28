package org.csu.mywork1.persistence;

import org.csu.mywork1.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    Account getAccountByUsername(String username);
    Account getAccountByUsernameAndPassword(Account account);

    void newAccount(Account account);//这是注册一个新的用户的操作

    void insertAccount(Account account);
    void insertSignon(Account account);

    void updateMyInfPicture(String filename);

//    List<Account> getPersonnelList();
}
