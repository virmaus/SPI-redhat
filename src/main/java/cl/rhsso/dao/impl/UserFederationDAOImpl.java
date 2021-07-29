package cl.rhsso.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cl.rhsso.dao.UserFederationDAO;
import cl.rhsso.utils.ConnectionFactory;
import cl.rhsso.vo.UserInfoVO;

public class UserFederationDAOImpl implements UserFederationDAO {

	ConnectionFactory dataConnection;
	public UserFederationDAOImpl(Properties properties) {
		dataConnection = ConnectionFactory.getInstance(properties);
	}
	
	@Override
	public UserInfoVO getUserByUsername(String username) {
		UserInfoVO userinfo = new UserInfoVO();
		
		Context initContext;
		Connection conn = null;
		ResultSet rs = null;
		try {
			//initContext = new InitialContext();
			 //Context envContext = (Context) initContext.lookup();
			 	System.out.println("en el try catch de getbyusername DAOImpl");
		        //DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MSSQLDS");
		        conn = dataConnection.getConnectionSoftland();
		        System.out.println("DAO Impl --connection username: "+ conn);
		        Statement statement = conn.createStatement();
		        String sql = "select id, first_name, last_name, username, email, atributo from [database].dbo.usuario where username = '"+username +"'";
		        rs = statement.executeQuery(sql);
		        
		        if (rs.next())
		        {
		        	userinfo.setId(rs.getLong(1));
		        	userinfo.setFirstName(rs.getString(2));
		        	userinfo.setLastName(rs.getString(3));
		        	userinfo.setUserName(rs.getString(4));
		        	userinfo.setEmail(rs.getString(5));
		        	userinfo.setAtributo(rs.getString(6));
		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {          //TODO esto esta bueno?
			try {
				conn.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
       
		return userinfo;
	}
	
	@Override
	public boolean isValid(String username, String password) {
		System.out.println("pasando por el is valid DAOImpl");
		Context initContext;
		Connection conn = null;
		ResultSet rs = null;
		try {
			//initContext = new InitialContext();
			 //Context envContext = (Context) initContext.lookup("java:comp/env");
			 	System.out.println("en el try catch is valid DAOImpl");
		        //DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MSSQLDS");
		        conn = dataConnection.getConnectionSoftland();
		        System.out.println("connection is valid: "+ conn);
		        Statement statement = conn.createStatement();
		        String sql = "select id, first_name, last_name, username, email, atributo from [database].dbo.usuario where username = '"+username +"' and password = '"+password+"'";
		        rs = statement.executeQuery(sql);
		        if(rs.next())
		        	return true;
		        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		return false;	
	}

	@Override
	public List<UserInfoVO> getAllUsers(int firstResult,int maxResults) {
		System.out.println("pasando por el getAllUsers DAOImpl");
		Context initContext;
		List<UserInfoVO> listUserInfo = new ArrayList<UserInfoVO>();
		Connection conn = null;
		ResultSet rs = null;
		try {
			//initContext = new InitialContext();
			 //Context envContext = (Context) initContext.lookup("java:comp/env");
			 	System.out.println("en el try catch getAllUsers DAOImpl");
		        //DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MSSQLDS");
		        conn = dataConnection.getConnectionSoftland();
		        System.out.println("connection is valid: "+ conn);
		        Statement statement = conn.createStatement();
		        String sql = "select id, first_name, last_name, username, email, atributo from [database].dbo.usuario";
		        rs = statement.executeQuery(sql);
		        while(rs.next()) {
		        	UserInfoVO userinfo = new UserInfoVO();
		        	userinfo.setId(rs.getLong(1));
		        	userinfo.setFirstName(rs.getString(2));
		        	userinfo.setLastName(rs.getString(3));
		        	userinfo.setUserName(rs.getString(4));
		        	userinfo.setEmail(rs.getString(5));
		        	userinfo.setAtributo(rs.getString(6));
		        	listUserInfo.add(userinfo);        	
		        }
		        	
		        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("en el catch de sql exception");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listUserInfo;
	}

	@Override
	public List<UserInfoVO> searchForUser(String username) {
		// TODO Auto-generated method stub
		System.out.println("pasando por el searchForUser DAOImpl");
		Context initContext;
		List<UserInfoVO> listUserInfo = new ArrayList<UserInfoVO>();
		Connection conn = null;
		ResultSet rs = null;
		try {
			//initContext = new InitialContext();
			 //Context envContext = (Context) initContext.lookup("java:comp/env");
			 	System.out.println("en el try catch searchForUser DAOImpl");
		        //DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MSSQLDS");
		        conn = dataConnection.getConnectionSoftland();
		        System.out.println("connection is valid: "+ conn);
		        Statement statement = conn.createStatement();
		        String sql = "select id, first_name, last_name, username, email, atributo from [database].dbo.usuario where lower(username) = '"+username+"'";
		        rs = statement.executeQuery(sql);
		        while(rs.next()) {
		        	UserInfoVO userinfo = new UserInfoVO();
		        	userinfo.setId(rs.getLong(1));
		        	userinfo.setFirstName(rs.getString(2));
		        	userinfo.setLastName(rs.getString(3));
		        	userinfo.setUserName(rs.getString(4));
		        	userinfo.setEmail(rs.getString(5));
		        	userinfo.setAtributo(rs.getString(6));
		        	listUserInfo.add(userinfo);	        	
		        }	
		        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("en el catch de sql exception");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listUserInfo;
	}

	@Override
	public boolean updateCredential(String id, String username, String email, String first_name, String last_name,
			String password) {
		
		Context initContext;
		Connection conn = null;
		
		try {
			//initContext = new InitialContext();
			 //Context envContext = (Context) initContext.lookup();
			 	System.out.println("en el try catch de updateCredential DAOImpl");
		        //DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MSSQLDS");
		        conn = dataConnection.getConnectionSoftland();
		        System.out.println("connection username: "+ conn);
		        Statement statement = conn.createStatement();
		        String sql = "UPDATE [database].dbo.usuario\n"
		        		+ "SET first_name='"+first_name+"', last_name='"+last_name+"', password='"+password+"', email='"+email+"', atributo='' where lower(username) = '"+username+"'";
		        statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}      
		return true;
	}


	@Override
	public UserInfoVO addUser(String id, String username, String email, String first_name, String last_name,
			String password, String atributo) {
		
		UserInfoVO userinfo = new UserInfoVO();
		Context initContext;
		Connection conn = null;
		try {
			//initContext = new InitialContext();
			 //Context envContext = (Context) initContext.lookup();
			 	System.out.println("en el try catch de addUser DAOImpl");
		        //DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MSSQLDS");
		        conn = dataConnection.getConnectionSoftland();
		        System.out.println("connection username: "+ conn);
		        Statement statement = conn.createStatement();
		        String sql = "INSERT INTO [database].dbo.usuario\n"
		        		+ "(id, first_name, last_name, username, password, email, atributo)	VALUES('"+id+"', '"+first_name+"', '"+last_name+"', '"+username+"', '"+password+"', '"+email+"', '"+atributo+"'); ";
		        ResultSet rs = statement.executeQuery(sql);
		        
		        if (rs.next())
		        {
		        	userinfo.setId(rs.getLong(1));
		        	userinfo.setFirstName(rs.getString(2));
		        	userinfo.setLastName(rs.getString(3));
		        	userinfo.setUserName(rs.getString(4));
		        	userinfo.setEmail(rs.getString(5));
		        	userinfo.setAtributo(rs.getString(6));
		        }
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	
	@Override
	public boolean removeUser(String id) {
		
		Context initContext;
		Connection conn = null;

		try {
			//initContext = new InitialContext();
			 //Context envContext = (Context) initContext.lookup();
			 	System.out.println("en el try catch de removeUser DAOImpl");
		        //DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MSSQLDS");
		        conn = dataConnection.getConnectionSoftland();
		        System.out.println("connection username: "+ conn);
		        Statement statement = conn.createStatement();
		        String sql = "DELETE FROM [database].dbo.usuario\n"
		        		+ "WHERE id='"+id+"' ";           // AND first_name='' AND last_name='' AND username='' AND password='' AND email='' AND atributo='';";
		        statement.executeUpdate(sql);
		        
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
