package org.csu.mywork1.service;

import org.csu.mywork1.domain.Account;
import org.csu.mywork1.persistence.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    public Account getAccount(String username){
        return accountMapper.getAccountByUsername(username);
    }
    public Account getAccount(String username,String password){
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountMapper.getAccountByUsernameAndPassword(account);
    }
    public void newAccount(Account account){
        accountMapper.newAccount(account);
    }

    public void updateMyInfPicture(String filename) {
        accountMapper.updateMyInfPicture(filename);
    }
//    public List<Account> getPersonnelList(){
//        return accountMapper.getPersonnelList();
//    }
}
