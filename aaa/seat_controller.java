package aaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class seat_controller {
	String script = "";

	@Resource(name="se")
	private seat se;
	
	@PostMapping("/user_search.do")
	public String user_search(@RequestParam(defaultValue = "", required = false) String search, @RequestParam(defaultValue = "", required = false) String part, Model m) {
		
		List<airuser_dto> list = se.search_user(search, part);
		m.addAttribute("search",search);
		m.addAttribute("part",part);
		m.addAttribute("list",list);
		
		return "ticketing_list";
	}
	
	@GetMapping("/userdelok.do")
	public String userdelok(@RequestParam(defaultValue = "", required = false) String uidx, Model m) {
		this.script = "";
		
		int result = se.delete_user(uidx);
		if(result > 0) {
			this.script = "<script>"
					+ "alert('정상적으로 예매를 취소하였습니다.');"
					+ "location.href='ticketing_list.do';"
					+ "</script>";
		}
		else {
			this.script = "<script>"
					+ "alert('시스템 오류로 인하여 예매 취소에 실패했습니다.');"
					+ "location.href='ticketing_list.do';"
					+ "</script>";
		}
		
		m.addAttribute("script",this.script);
		
		return "script";
	}
	
	@GetMapping("/ticketing_list.do")
	public String ticketing_list(Model m) {
		
		List<airuser_dto> list = null;
		
		list = se.select_user();			
				
		m.addAttribute("list",list);
		
		return null;
	}
	
	@GetMapping("/goseat.do")
	public String goseat(Model m) {
		
		List<seat_dto> result = se.search_pidx();
		
		m.addAttribute("ok",result.size());
		
		return "seat_list";
	}
	
	@PostMapping("/resok.do")
	public String resok(@ModelAttribute("seat") seat_dto dto, Model m) {
		this.script = "";
		
		if(dto.getPidx().equals(null)) {
			this.script = "<script>"
					+ "alert('입력한 값은 등록이 완료되었습니다.');"
					+ "history.go(-1);"
					+ "</script>";
		}
		else{
			int result = se.insert_seat(dto);
			if(result > 0) {
				this.script = "<script>"
						+ "alert('좌석 및 예약설정 등록이 완료되었습니다.');"
						+ "location.href='seat_list.do';"
						+ "</script>";
			}
			else {
				this.script = "<script>"
						+ "alert('시스템 오류로 인하여 좌석 및 예약설정 등록에 실패했습니다.');"
						+ "location.href='seat_list.do';"
						+ "</script>";
			}
		}
		m.addAttribute("script",this.script);
		return "script";
	}
	
}
