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
        //int isbn = book.getIsbn();
        /*if(bookDAO.getBookByIsbn(isbn) != null){
            return null;
    }*/
        return accountDAO.openAccount(account);
        
    
    
}  

}
