package aaa;


import java.sql.Connection;
import java.sql.PreparedStatement;

import aaa.add_dto;
import org.apache.commons.dbcp.BasicDataSource;

public class db_insert {
	Connection con = null;
	PreparedStatement ps = null;
	int result = 0;
	
	public db_insert(BasicDataSource dataSource) {
		try {
			this.con = dataSource.getConnection();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public int add_insert(String pass, add_dto ad) {
		try {
			String sql = "insert into admin values('0',?,?,?,?,?,?,?,'미승인',now(),0)";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, ad.getAid());
			this.ps.setString(2, pass);
			this.ps.setString(3, ad.getAname());
			this.ps.setString(4, ad.getAemail());
			this.ps.setString(5, ad.getAtel());
			this.ps.setString(6, ad.getAdep());
			this.ps.setString(7, ad.getApost());
			int result = this.ps.executeUpdate();
			this.ps.close();
			this.con.close();
		}
		catch(Exception e) {
			System.out.println("sql 문법 오류!");
		}
		
		return this.result;
	}

}
