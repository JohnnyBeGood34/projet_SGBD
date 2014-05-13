/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

/**
 *
 * @author Easy
 */
public class Connection {
    
    private Connection connection=null;
    private String url;
    
    private Connection(String url){
        this.url=url;
    }
    public Connection getInstance(String url){
        
        if(this.connection==null){
            this.connection=new Connection(url);
        }
        return connection;
    }
    
}
