package aaa;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class faq_controller {
	String script = "";
	
	@Resource(name="faq")
	private faq f;
	
	@PostMapping("/modifyok.do")
	public String modifyok(@ModelAttribute("modify") faq_dto dto, Model m) {
		
		int result = f.update_faq(dto);
		if(result > 0) {
			this.script = "<script>"
					+ "alert('정상적으로 FAQ가 수정되었습니다.');"
					+ "location.href = './faq_list.do';"
					+ "</script>";
		}
		else {
			this.script = "<script>"
					+ "alert('시스템오류로 인하여 FAQ수정에 실패했습니다.');"
					+ "location.href = './faq_list.do';"
					+ "</script>";
		}
	
		m.addAttribute("script",this.script);
		
		return "script";
	}
	
	@GetMapping("/faq_modify.do")
	public String faq_modify(@RequestParam(defaultValue = "", required = false) String fidx, Model m) {
		
		faq_dto list = f.view_faq(fidx);
		
		m.addAttribute("list",list);
		
		return null;
	}
	
	@GetMapping("/delete_faq.do")
	public String delete_faq(@RequestParam(defaultValue = "", required = false) String fidx, Model m) {
		this.script = "";
		
		int result = f.del_faq(fidx);
		if(result > 0) {
			this.script = "<script>"
					+ "alert('정상적으로 FAQ가 삭제되었습니다.');"
					+ "location.href = './faq_list.do';"
					+ "</script>";
		}
		else {
			this.script = "<script>"
					+ "alert('시스템오류로 인하여 FAQ삭제에 실패했습니다.');"
					+ "location.href = './faq_list.do';"
					+ "</script>";
		}
		
		m.addAttribute("script",this.script);
		
		return "script";
	}
	
	@PostMapping("/faqok.do")
	public String faqok(@ModelAttribute("faq") faq_dto dto, Model m) {
		this.script = "";
		
		int result = f.insert_faq(dto);
		if(result > 0) {
			this.script = "<script>"
					+ "alert('정상적으로 FAQ등록이 완료되었습니다.');"
					+ "location.href = './faq_list.do';"
					+ "</script>";
		}
		else {
			this.script = "<script>"
					+ "alert('시스템오류로 인하여 FAQ등록에 실패했습니다.');"
					+ "location.href = './faq_list.do';"
					+ "</script>";
		}
		
		m.addAttribute("script",this.script);
		
		return "script";
	}
	
	@GetMapping("/faq_write.do")
	public String faq_write() {
		
		return null;
	}
	
	@RequestMapping("/faq_list.do")
	public String faq_list(@RequestParam(defaultValue = "", required = false) String search, @RequestParam(defaultValue = "", required = false) String page, Model m) {
		List<faq_dto> list = null;
		int no = 0;
		int view_no = 1;
		int ea = 0;
		
		if(page.equals("")) {
			no = 0;
		}
		else {
			int r = (Integer.parseInt(page)*10) - 10;
			view_no = Integer.parseInt(page);
			no = r;
		}
		
		
		m.addAttribute("view_no",view_no);
		
		if(search.equals("")) {
			ea = f.faq_ctn();
			m.addAttribute("ea",ea);
			list = f.select_faq(no);
		}
		else {
			m.addAttribute("search",search);
			ea = f.faq_search_ctn(search);
			m.addAttribute("ea",ea);
			list = f.search_faq(search,no);
		}
		
		m.addAttribute("list",list);
		
		return null;
	}
}
