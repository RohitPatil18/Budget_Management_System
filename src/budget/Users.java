/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

/**
 *
 * @author ROHIT
 */
class Users {
    
    private int id;
    private String username;
    
    public Users(int id, String username){
        this.id = id;
        this.username = username;     
    }
    
    
    public int getId(){
        return id;
    }
    
    public String getUsername(){
        return username;
    }
    
}
