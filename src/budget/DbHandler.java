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
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class DbHandler{

    public int addUser(int uid, String uname, String pw){

	try{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

		String sql = "Insert into UserDetails values(?,?,?)";

		PreparedStatement pst = con.prepareStatement(sql);

		pst.setInt(1,uid);
		pst.setString(2, uname);
                pst.setString(3, pw);

		int r = pst.executeUpdate();

            con.close();
            
             return r;

	}catch(SQLException e){

            JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
            return 0;

	}
       
    }
    
    public int updateUser(int uid, String name, String pw){

                int r = 0;
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

			String sql = "update UserDetails set password = ? where id = ?";

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(2,uid);
			pst.setString(1, pw);

			r = pst.executeUpdate();

			con.close();

		}catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
		}
                return r;

	}
    
    	public int deleteUser(int uid){
            
                int r=0; 
                
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

			String sql = "delete from UserDetails where id = ?";

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1, uid);

			r = pst.executeUpdate();

			con.close();

		}catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
		}
                
                return r;

	}
        
        public int deleteTransaction(int uid){
            
                int r=0; 
                
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

			String sql = "delete from transaction where id = ?";

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1, uid);

			r = pst.executeUpdate();

			con.close();

		}catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
		}
                
                return r;

	}
        
	public boolean getPasswordDetails(int id, String password){
                
                boolean flag = false;
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

			String sql = "select * from UserDetails where id = ? and password = ?";

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1,id);
                        pst.setString(2,password);
                       
			ResultSet rs = pst.executeQuery();
                        
                        if(rs.next()){
                            flag = true;                               
			}
			rs.close();
                        
                        con.close();
                        
		}catch(SQLException e){

			JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
		}
                return flag;

	}
        
       public String getUsername(int id){
                
                ResultSet rs;
                String username = "";
                

		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

			String sql = "select username from UserDetails where id = ?";

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1,id);
                       
			rs = pst.executeQuery();
                        
                        while(rs.next()){
                            username = rs.getString(1);                                
			}
			rs.close();
                        
                        con.close();
                        
		}catch(SQLException e){

			JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
		}
                return username;

	}
       
        public long checkBalance(int id){
                
                ResultSet rs;
                long bal = 0;
                

		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

			String sql = "select balance from transaction where id = ?";
                        

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1,id);
                       
			rs = pst.executeQuery();
                        
                        while(rs.next()){
                            bal = rs.getLong(1);                                
			}
			rs.close();
                        
                        con.close();
                        
		}catch(SQLException e){

			JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
		}
                return bal;

	}

    public int Deposit(int uid, String date, String mode, long amt, long bal){

	try{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

		String sql = "Insert into transaction values(?,?,?,?,?)";

		PreparedStatement pst = con.prepareStatement(sql);

		pst.setInt(1,uid);
		pst.setString(2, date);
                pst.setString(3, mode);
                pst.setLong(4, amt);
                pst.setLong(5, bal);

		int r = pst.executeUpdate();

            con.close();
            
             return r;

	}catch(SQLException e){

            JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
            return 0;

	}
       
    }
      
        
        
        public ArrayList<Transaction> trasactionList(int id){
            ArrayList<Transaction> transactionList = new ArrayList<>();
            
            try{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

		String sql = "select * from transaction where id = ?";
                        

		PreparedStatement pst = con.prepareStatement(sql);

		pst.setInt(1,id);
                       
		ResultSet rs = pst.executeQuery();
                
                Transaction transaction;

		while(rs.next()){
                    
                    transaction = new Transaction(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getLong(5));
                    
                    transactionList.add(transaction);

                    
		}
		rs.close();
		con.close();

            }catch(SQLException e){

		JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
            }
            
            
            return transactionList;
        }
        
        public ArrayList<Users> userList(){
            ArrayList<Users> userList = new ArrayList<>();
            Connection con;
            ResultSet rs;
            
            try{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

		String sql = "select id,username from UserDetails";
                        

		Statement stmt = con.createStatement();
                       
		rs = stmt.executeQuery(sql);
                
                Users user;

		while(rs.next()){
                    
                    user = new Users(rs.getInt(1),rs.getString(2));
                    
                    userList.add(user);
                    
		}
		rs.close();
		con.close();

            }catch(SQLException e){

		JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
            }
            
            
            return userList;
        }

    int withdraw(int id, String date, String mode, long amt, long bal) {
     
        	try{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

		String sql = "Insert into transaction values(?,?,?,?,?)";

		PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1,id);
            pst.setString(2, date);
            pst.setString(3, mode);
            pst.setLong(4, amt);
            pst.setLong(5, bal);

            int r = pst.executeUpdate();

            con.close();
            
             return r;

	}catch(SQLException e){

            JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
            return 0;

	}
        
        
    }
        



}