package cs636.vinylstation.service;

import java.sql.SQLException;
import cs636.vinylstation.domain.loginObject;
import cs636.vinylstation.dao.loginDAO;

public class loginService
{
    private loginDAO loginDAO;
    private String USERNAME;
    private String PASS;
    
    public loginService(final loginDAO a) {
        this.loginDAO = a;
    }
    
    public String checkloginCredential(final String a, final String b) {
        final loginObject u = new loginObject(a, b);
        String role = "none";
        try {
            role = this.loginDAO.loginCheck(u);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
    
    public String getUsername() {
        return this.USERNAME;
    }
    
    public String getPass() {
        return this.PASS;
    }
    
    public void setUsername(final String user) {
        this.USERNAME = user;
    }
    
    public void setPass(final String password) {
        this.PASS = password;
    }
}