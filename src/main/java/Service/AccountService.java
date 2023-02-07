package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    public AccountDAO accountDAO;

    
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    
    
   
    public Account addAccount(Account account) {

        if(account.username == null || account.password.length() < 4 || account.username.isEmpty()) return null;
        //if(message_text == null) return null; 

        return accountDAO.openAccount(account);
        
    
    
}  

}
