package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;

public class UserDao {

	 public User findByLoginInfo(String login_id,String password) {
		 Connection conn = null;
		 try {
			 conn =DBManager.getConnection();

			 String sql ="SELECT * FROM user WHERE login_id =? and password=?";

			 PreparedStatement pStmt= conn.prepareStatement(sql);
			 pStmt.setString(1,login_id);
			 pStmt.setString(2, MD5(password));

			 ResultSet rs=pStmt.executeQuery();
			 if(!rs.next()) {
				 return null;
			 }
			 String login_idDate= rs.getString("login_id");
			 String nameDate=rs.getString("name");
			 return new User(login_idDate,nameDate);
		 }catch(SQLException e){
			 e.printStackTrace();
			 return null;
		 }finally {
			 if(conn!=null) {
				 try {
					 conn.close();
				 }catch(SQLException e){
					 e.printStackTrace();
					 return null;
				 }
			 }
		 }
	 }

	 public User findByLogin_idInfo(String login_id) {
		 Connection conn = null;
		 try {
			 conn =DBManager.getConnection();

			 String sql ="SELECT * FROM user WHERE login_id =?";

			 PreparedStatement pStmt= conn.prepareStatement(sql);
			 pStmt.setString(1,login_id);

			 ResultSet rs=pStmt.executeQuery();
			 if(!rs.next()) {
				 return null;
			 }
			 String login_idDate= rs.getString("login_id");

			 return new User(login_id);
		 }catch(SQLException e){
			 e.printStackTrace();
			 return null;
		 }finally {
			 if(conn!=null) {
				 try {
					 conn.close();
				 }catch(SQLException e){
					 e.printStackTrace();
					 return null;
				 }
			 }
		 }
	 }
	 public List<User>findAll(){
		 Connection conn = null;
		 List<User> userList =new ArrayList<User>();

		 try {
			 conn = DBManager.getConnection();
			 String sql = "SELECT * FROM user where login_id not in ('admin')";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

			 while(rs.next()) {
				 int id= rs.getInt("id");
				 String login_id= rs.getString("login_id");
				 String name = rs.getString("name");
				 Date birth_date = rs.getDate("birth_date");
				 String password = rs.getString("password");
				 String create_date = rs.getString("create_date");
				 String update_date = rs.getString("update_date");

				 User user=new User(id, login_id, name, birth_date, password, create_date, update_date);

				 userList.add(user);
			 }
		 }catch(SQLException e) {
			 e.printStackTrace();
			 return null;
		 }finally {
			 if(conn !=null) {
				 try {
					 conn.close();
				 }catch(SQLException e) {
					 e.printStackTrace();
					 return null;
				 }
			 }
		 }
		 return userList;
	 }


	 public List<User>findSearch(String loginId, String userName, String birthDate1, String birthDate2){
		 Connection conn = null;
		 List<User> userList =new ArrayList<User>();

		 try {
			 conn = DBManager.getConnection();
			 String sql = "SELECT * FROM user where login_id not in ('admin')";

			 if(!loginId.equals("")) {
				 sql += "and login_id = '" + loginId + "'";
			 }

			 if(!userName.equals("")) {
				 sql += "and name LIKE '%" + userName + "%'";
			 }

			 if(!birthDate1.equals("")) {
				 sql += "and birth_date >= '" + birthDate1 + "'";
			 }

			 if(!birthDate2.equals("")) {
				 sql += "and birth_date <= '" + birthDate2 + "'";
			 }


            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

			 while(rs.next()) {
				 int id= rs.getInt("id");
				 String login_id= rs.getString("login_id");
				 String name = rs.getString("name");
				 Date birth_date = rs.getDate("birth_date");
				 String password = rs.getString("password");
				 String create_date = rs.getString("create_date");
				 String update_date = rs.getString("update_date");

				 User user=new User(id, login_id, name, birth_date, password, create_date, update_date);

				 userList.add(user);
			 }
		 }catch(SQLException e) {
			 e.printStackTrace();
			 return null;
		 }finally {
			 if(conn !=null) {
				 try {
					 conn.close();
				 }catch(SQLException e) {
					 e.printStackTrace();
					 return null;
				 }
			 }
		 }
		 return userList;
	 }

	 public void findByRegistrationInfo(String login_id,String password,String name,Date birth_date,
			 String create_date,String update_date) {
		 Connection conn = null;

		 try {
			 conn = DBManager.getConnection();
			 String sql ="INSERT INTO user(login_id,password,name,birth_date,create_date,update_date) VALUES(?,?,?,?,?,?)";


			 PreparedStatement pStmt= conn.prepareStatement(sql);
			 pStmt.setString(1,login_id);
			 pStmt.setString(2,MD5(password));
			 pStmt.setString(3,name);
			 pStmt.setDate(4,birth_date);
			 pStmt.setString(5,create_date);
			 pStmt.setString(6,update_date);

			 int rs=pStmt.executeUpdate();


		 } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // データベース切断
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	 }

	 public User findByDetail(int id) {
		 Connection conn =null;
		 try {
			 conn = DBManager.getConnection();
			 String sql = "SELECT login_id,name,birth_date,create_date,update_date FROM user WHERE id=?";
			 PreparedStatement pStmt= conn.prepareStatement(sql);

	           pStmt.setString(1, Integer.toString(id));
	           ResultSet rs = pStmt.executeQuery();
	            User user = null;
	            if(rs.next()) {
					 String login_id= rs.getString("login_id");
					 String name = rs.getString("name");
					 Date birth_date = rs.getDate("birth_date");
					 String create_date = rs.getString("create_date");
					 String update_date = rs.getString("update_date");


					 user = new User(id, login_id, name, birth_date, null, create_date, update_date);
	            }
	            return user;
		 }catch(SQLException e) {
			 e.printStackTrace();
	            return null;
		 } finally {
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            }
		 }
	 }

	 public void findByDelete(int id) {
		 Connection conn =null;
		 try {
			 conn = DBManager.getConnection();
			 String sql = "DELETE FROM user WHERE id=?";
			 PreparedStatement pStmt= conn.prepareStatement(sql);

	           pStmt.setString(1, Integer.toString(id));
	           int rs = pStmt.executeUpdate();
	           System.out.println(rs);
		 }catch(SQLException e) {
			 e.printStackTrace();
		 } finally {
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
		 }
	 }
	 public void findByUpdate(int id,String login_id,String password,String name,Date birth_date,
			 String update_date) {
		 Connection conn = null;

		 try {
			 conn = DBManager.getConnection();
			 String sql ="UPDATE user SET login_id=?,password=?,name=?,birth_date=?,update_date=? WHERE id=?";

					 PreparedStatement pStmt= conn.prepareStatement(sql);

			 pStmt.setString(1,login_id);
			 pStmt.setString(2,MD5(password));
			 pStmt.setString(3,name);
			 pStmt.setDate(4,birth_date);
			 pStmt.setString(5,update_date);
			 pStmt.setString(6,Integer.toString(id));

			 int rs=pStmt.executeUpdate();


		 } catch (SQLException e) {
	            e.printStackTrace();
		 } finally {
	            // データベース切断
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
		 }
	 }

	 private String MD5(String password) {
		//ハッシュを生成したい元の文字列
		 //ハッシュ生成前にバイト配列に置き換える際のCharset
		 Charset charset = StandardCharsets.UTF_8;
		 //ハッシュアルゴリズム
		 String algorithm = "MD5";

		 //ハッシュ生成処理
		 byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		 String result = DatatypeConverter.printHexBinary(bytes);
		 System.out.println(result);
		 //標準出力
		return result;

	 }

	 public void findByUpdate2(int id,String login_id,String name,Date birth_date,
			 String update_date) {
		 Connection conn = null;

		 try {
			 conn = DBManager.getConnection();
			 String sql ="UPDATE user SET login_id=?,name=?,birth_date=?,update_date=? WHERE id=?";

					 PreparedStatement pStmt= conn.prepareStatement(sql);

			 pStmt.setString(1,login_id);
			 pStmt.setString(2,name);
			 pStmt.setDate(3,birth_date);
			 pStmt.setString(4,update_date);
			 pStmt.setString(5,Integer.toString(id));

			 int rs=pStmt.executeUpdate();


		 } catch (SQLException e) {
	            e.printStackTrace();
		 } finally {
	            // データベース切断
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
		 }
	 }
}

