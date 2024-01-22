package aaa;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class webpage {
	String script = "";
	
	@Resource(name="info")
	private info in;
	
	@GetMapping("/foot.do")
	public String footer(Model m) {
		
		siteinfo_dto dto = in.select_siteinfo();
		
		m.addAttribute("foot",dto);
		
		return "foot";
	}

	@PostMapping("/siteinfook.do")
	public String siteinfook(@ModelAttribute("sd") siteinfo_dto dto, Model m) {
		
		int result = in.update_info(dto);
		
		if(result > 0) {
			this.script = "<script>"
					+ "alert('정상적으로 등록 완료되었습니다.');"
					+ "location.href='./siteinfo.do';"
					+ "</script>";
		}
		else {
			this.script = "<script>"
					+ "alert('시스템 오류로 인하여 등록이 되지 않았습니다.');"
					+ "location.href='./siteinfo.do';"
					+ "</script>";
		}
		
		m.addAttribute("script",this.script);
		
		return "script";
	}
	
	@GetMapping("/siteinfo.do")
	public String siteinfo(Model m) {
		
		siteinfo_dto dto = in.select_siteinfo();
		
		m.addAttribute("list",dto);
		
		return "admin_siteinfo";
	}
}
