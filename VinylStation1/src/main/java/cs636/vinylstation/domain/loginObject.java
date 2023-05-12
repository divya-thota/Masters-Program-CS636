

package cs636.vinylstation.domain;

public class loginObject{
    private String USERNAME;
    private String PASS;
    
    public loginObject(final String username, final String pass) {
        this.USERNAME = username;
        this.PASS = pass;
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