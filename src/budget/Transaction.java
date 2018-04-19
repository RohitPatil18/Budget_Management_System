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
class Transaction {
    private int id;
    private String date, mode;
    private long amt, bal;
    
    public Transaction(int id, String date, String mode, long amt, long bal){
        this.id = id;
        this.date = date;
        this.mode = mode;
        this.amt = amt;
        this.bal = bal;        
    }
    
    
    public int getId(){
        return id;
    }
    
    public String getDate(){
        return date;
    }
    
    public String getMode(){
        return mode;
    }
    
    public long getAmount(){
        return amt;
    }
    
    public long getBalance(){
        return bal;
    }
}
