package aaa;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.dbcp.BasicDataSource;

public class db_update {
	Connection con = null;
	PreparedStatement ps = null;
	String result = null;
	
	public db_update(BasicDataSource dataSource) {
		try {
			this.con = dataSource.getConnection();
		}
		catch(Exception e) {
			System.out.println("DB연결 오류 발생!!");
		}
	}
	
	public String roval(String aidx, String aid , String aroval) {
		
		try {
			String sql = "update admin set acount=0, aroval=? where aidx=? and aid=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, aroval);
			this.ps.setString(2, aidx);
			this.ps.setString(3, aid);			
			int success = this.ps.executeUpdate();
			if(success > 0) {
				this.result = "ok"; 
			}
			else {
				this.result = "no";
			}
			this.ps.close();
			this.con.close();
		}
		catch(Exception e) {
			System.out.println("SQL문법 오류!!");
		}
		
		return this.result;
	}
	
	public String reset_count(String aid) {

		try {
			String sql = "update admin set acount=0 where aid=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, aid);			
			this.ps.executeUpdate();
			
			this.ps.close();
			this.con.close();
		}
		catch(Exception e) {
			System.out.println("SQL문법 오류!!");
		}
		
		return null;
	}
	
	public String log_count(String aid) {
		try {
			String sql = "update admin set acount=acount+1 where aid=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, aid);			
			int success = this.ps.executeUpdate();
			if(success > 0) {
				this.result = "ok"; 
			}
			else {
				this.result = "no";
			}
			this.ps.close();
			this.con.close();
		}
		catch(Exception e) {
			System.out.println("SQL문법 오류!!");
		}
		return this.result;
	}
	
	public String fail5(String aid) {
		try {
			String sql = "update admin set aroval=? where aid=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, "미승인");			
			this.ps.setString(2, aid);			
			int success = this.ps.executeUpdate();
			if(success > 0) {
				this.result = "ok"; 
			}
			else {
				this.result = "no";
			}
			this.ps.close();
			this.con.close();
		}
		catch(Exception e) {
			System.out.println("SQL문법 오류!!");
		}
		return this.result;
	}
}
