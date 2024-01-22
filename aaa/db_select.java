package aaa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp.BasicDataSource;

public class db_select {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String result = null;
	
	
	public db_select(BasicDataSource dataSource) {
		try {
			this.con = dataSource.getConnection();
		}
		catch(Exception e) {
			System.out.println("데이터베이스 접속오류!!");
		}
		
	}
	
	public int overlap_tel(String atel) {
		int telok = 0;
		try {
			String sql = "select count(*) as ctn from admin where atel = ?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, atel);
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				telok = this.rs.getInt("ctn");
			}
			
			this.rs.close();
			this.ps.close();
			this.con.close();
		}
		catch(Exception e) {
			System.out.println("데이터베이스 오류!");
		}
		
		return telok;
	}
	
	public int overlap_email(String aemail) {
		int emailok = 0;
		try {
			String sql = "select count(*) as ctn from admin where aemail = ?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, aemail);
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				emailok = this.rs.getInt("ctn");
			}
			
			this.rs.close();
			this.ps.close();
			this.con.close();
		}
		catch(Exception e) {
			System.out.println("데이터베이스 오류!");
		}
		
		return emailok;
	}
	
	public int count_ck(String aid) {
		int counter = 0;
		
		try {
			String sql = "select acount from admin where aid=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, aid);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				counter = this.rs.getInt("acount");			
			}
			
			this.rs.close();
			this.ps.close();
			this.con.close();
		}
		catch(Exception e) {
			System.out.println("데이터베이스 문법오류!");
		}
		
		return counter;
	}
	
	public String usercheck(String aid, String apass) {
		
		security se = new security(apass);
		String pass = se.md5_se();		
		
		try {
			String sql = "select * from admin where aid=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, aid);
			this.rs = this.ps.executeQuery();

			while(this.rs.next()) {
				if(aid.equals(this.rs.getString("aid"))) {
					if(pass.equals(this.rs.getString("apass"))){
						this.result = this.rs.getString("aid")+","+this.rs.getString("aname")+","+this.rs.getString("aroval");						
					}
				}
			}
			this.rs.close();
			this.ps.close();
		}
		catch(Exception e) {
			System.out.println("sql 문법 오류 발생!");
		}
		
		return this.result;
	}

}
