package aaa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class webpage2 {
	
	@Autowired
	BasicDataSource dataSource;
	
	@GetMapping("/admin_main.do")
	public void admin_main() {
		
	}

	
	@PostMapping("/roval.do")
	public String roval(@RequestParam(defaultValue = "") String aidx,
			@RequestParam(defaultValue = "") String aid,
			@RequestParam(defaultValue = "") String aroval,
			Model m) {
		
		String script = "";
		String result = null;
		
		if(aidx.equals("") || aid.equals("")) {
			script = "<script>"
					+ "alert('올바른 값이 전달되지 않았습니다.');"					
					+ "</script>";
		}
		else {
			db_update db = new db_update(dataSource);
			result = db.roval(aidx, aid, aroval);
			if(result == "ok") {
				script = "<script>"
						+ "alert('승인여부 변경이 완료되었습니다!');"
						+ "location.href='./admin_main.do'"
						+ "</script>";
			}
			else {
				script = "<script>"
						+ "alert('시스템 오류로 인하여 승인(미승인)에 실패했습니다.');"
						+ "location.href='./admin_main.do'"
						+ "</script>";
			}
		}
		
		m.addAttribute("script",script);
		return "api_json";
	}

	
	@PostMapping("/addok.do")
	public String addok(@RequestParam String aid,
			@RequestParam String apass,
			@RequestParam String aname,
			@RequestParam String aemail,
			@RequestParam String atel,
			@RequestParam String adep,
			@RequestParam String apost,			
			Model m) {
		
		security sc = new security(apass);
		String pass = sc.md5_se();
		db_insert insert = new db_insert(dataSource);
		db_select select = new db_select(dataSource); 
		add_dto ad = new add_dto();
		String script="";
		
		int emailok = select.overlap_email(aemail);
		int telok = select.overlap_tel(atel);
		
		if(emailok > 0) {
			script = "<script>alert('이미 등록된 이메일 입니다.'); history.go(-1);</script>";
		}
		else {
			if(telok > 0) {
				script = "<script>alert('이미 등록된 전화번호 입니다.'); history.go(-1);</script>";
			}
			else {
				ad.setAid(aid);
				ad.setAname(aname);
				ad.setAemail(aemail);
				ad.setAtel(atel);
				ad.setAdep(adep);
				ad.setApost(apost);
				int success = insert.add_insert(pass,ad);
				
				if(success >= 0) {
					script = "<script>alert('관리자 등록이 정상적으로 완료되었습니다.'); location.href='./index.jsp'</script>";
				}
				else {
					script = "<script>alert('관리자 등록에 문제가 발생하였습니다.'); location.href='./index.jsp'</script>";
				}
			}
		}
		m.addAttribute("script", script);
		
		return null;
	}
	
	@GetMapping("/idck.do")
	public String idck(@RequestParam String aid, Model m) {
		String script = "";
		String msg = null;
		try {
		Connection con = dataSource.getConnection();
		String sql = "select count(*) as ctn from admin where aid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, aid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			if(rs.getString("ctn").equals("0")) {
				msg = "0";					
			}
			else {
				msg = "1";
			}
		}
		rs.close();
		ps.close();
		con.close();
		}
		catch(Exception e) {
			msg = "error";
		}
		m.addAttribute("msg",msg);	
		return null;
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpServletRequest req, Model m) {
		HttpSession session = req.getSession();
		session.removeAttribute("aid");
		session.removeAttribute("aname");
		String script = "<script> alert('로그아웃 되었습니다'); location.href='./index.do';</script>";
		m.addAttribute("script",script);
		
		return "script";
	}
	
	@PostMapping("/loginok.do")
	public String loginok(HttpServletRequest req, Model m, @RequestParam String aid, @RequestParam String apass) {
		
		db_select ds = new db_select(dataSource);
		String success = ds.usercheck(aid, apass);
		String script = "";		
		db_update db = new db_update(dataSource);
		
		
		if(success == null || success == "") {
			String re = db.log_count(aid);
			if(re == "ok") {
				script = "<script> alert('아이디및 비밀번호를 확인해 주세요!'); history.go(-1); </script>";
				int counter = ds.count_ck(aid);
				
				if(counter >= 5) {
					String result = db.fail5(aid);
					if(result.equals("ok")) {
						script = "<script> alert('5번 이상 로그인에 실패해 아이디가 잠겼습니다. 관리자에게 문의하세요.'); history.go(-1); </script>";
					}
				}
				
			}
		}
		else{						
			String user_info[] = success.split(",");
			if(user_info[2].equals("승인")) {
				HttpSession session = req.getSession();
				session.setAttribute("aid", user_info[0]);
				session.setAttribute("aname", user_info[1]);
				
				script = "<script> alert('정상적으로 로그인 되었습니다'); location.href='./admin_main.do'</script>";
				db.reset_count(aid);
			}
			else {
				script = "<script> alert('승인되지 않은 아이디 입니다'); location.href='./index.do'; </script>";
			}
		}
		
		m.addAttribute("script",script);
		
		return "script";
	}
	
	@RequestMapping("/api_json.do")
	public String api_server(Model m) {
		api_json aj = new api_json(dataSource);
		String datalist = aj.api();
		m.addAttribute("datalist",datalist);
		return null;
	}
	
	@GetMapping("/index.do")
	public String index() {
		
		return "index";
	}
}
