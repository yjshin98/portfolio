package aaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository("notice")
public class notice {
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate tm;
	
	public int count(String nidx) {
		
		int result = tm.update("admin.notice_count",nidx);
		
		return result;
	}
	
	public int update_notice(notice_dto dto) {
		
		int result = tm.update("admin.update_notice",dto);
		
		return result;
	}
	
	public int delete_notice(String nidx) {
		
		int result = tm.delete("admin.delete_notice",nidx);
		
		return result;
	}
	
	public int update_check(String nidx, String part) {
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("nidx", nidx);
		m.put("part", part);
		
		int result = tm.update("admin.update_check",m); 
		
		return result;
	}
	
	public notice_dto noticeview(String nidx) {
		
		List<notice_dto> list = new ArrayList<notice_dto>();
		notice_dto dto  = tm.selectOne("admin.notice_select",nidx);
		
		return dto;
	}
	
	public int notice_insert(notice_dto dto) {
		
		int result = tm.insert("admin.insert_notice",dto);
		
		return result;
	}
	
	
	public List<notice_dto> select_notice(){
		
		List<notice_dto> data = new ArrayList<notice_dto>();
		data = tm.selectList("admin.select_notice");
		
		return data;
	}
	
	
	public String fileok(MultipartFile nfile) {
		
		FTPClient ftp = new FTPClient();
	    ftp.setControlEncoding("utf-8");
	    FTPClientConfig cf = new FTPClientConfig();
	    String url = "";
	    
	    if(nfile.equals("")) {
	    	url = "";
	    }
	    else {
		    try {
		       String filename = nfile.getOriginalFilename();
		       String host = "iup.cdn1.cafe24.com";
		       String user = "tlsdudwns98";
		       String pass = "shinweb98!";
		       int port = 21;
		       ftp.configure(cf);
		       ftp.connect(host,port);   
		         
		       if(ftp.login(user,pass)) {
		          ftp.setFileType(FTP.BINARY_FILE_TYPE);
		          int result = ftp.getReplyCode();
		          boolean ok = ftp.storeFile("/www/"+filename, nfile.getInputStream());
		          url = "http://tlsdudwns98.cdn1.cafe24.com/" + filename;
		          if(ok == true) {
		             System.out.println("정상적으로 업로드 되었습니다.");
		          }
		          else {
		             System.out.println("FTP 경로 오류발생 되었습니다.");
		          }
		       }
		       else {
		          System.out.println("접속오류");
		       }
		      }
		      catch(Exception e){
		         System.out.println("FTP 접속 정보 오류 및 접속 사용자 아이디/패스워드 오류!!");
		      }
		      finally {
		         try {
		            ftp.disconnect();
		         }
		         catch(Exception ee) {
		            System.out.println("서버 루프로 인하여 접속종류 장애발생!!");
		         }
		      }
	    }
		return url;
	}

}
