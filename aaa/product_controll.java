package aaa;

import java.util.ArrayList;
import java.util.Arrays;
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
public class product_controll {
	String script = "";
	
	@Resource(name="product")
	private product pd;
	
	@Resource(name="se")
	private seat se;
	
	@RequestMapping("/seat_list.do")
	public String seat_list(@RequestParam(defaultValue = "", required = false) String search, @RequestParam(defaultValue = "", required = false) String part, @RequestParam(defaultValue = "", required = false) String no, Model m) {
		
		List<airplane_dto> list = null;
		List<airplane_dto> data = pd.select_product();
		List<seat_dto> result = se.search_pidx();
		
		
		if(no.equals("5")) {
			list = pd.resno_list(no);
		}
		else if(no.equals("6")) {
			list = pd.resok_list();
			m.addAttribute("nn",no);
		}
		else {
			
			if(search.equals("")) {
				
			}
			else {
				m.addAttribute("username",search);
				m.addAttribute("no",part);
				
				if(part.equals("3")) {
					list = pd.search_product(search, "3");
				}
				else if(part.equals("4")) {
					list = pd.search_product(search, "4");
				}
			}
		}
		
		
		m.addAttribute("all",data);
		m.addAttribute("seatok",result);
		m.addAttribute("list",list);
		
		return null;
	}
	
	@PostMapping("/aircode_modify.do")
	public String aircode_modify(@ModelAttribute("modify_code") aircode_dto dto, Model m) {
		this.script = "";
		
		int result = pd.update_code_modifyok(dto);
		if(result > 0) {
			this.script = "<script>"
					+ "alert('정상적으로 항공코드가 수정되었습니다.');"
					+ "location.href = 'air_list.do';"
					+ "</script>";
		}
		else {
			this.script = "<script>"
					+ "alert('시스템 오류로 인하여 항공코드 수정에 실패했습니다.');"
					+ "location.href = 'air_list.do';"
					+ "</script>";
		}
		
		m.addAttribute("script",this.script);
		return "script";
	}
	
	@GetMapping("/air_modifycode.do")
	public String air_modifycode(@RequestParam(defaultValue = "", required = false) String airidx, Model m) {
		
		aircode_dto data = pd.code_modifyview(airidx);
		
		m.addAttribute("data",data);
		
		return null;
	}
	
	@PostMapping("/product_newok.do")
	public String product_newok(@ModelAttribute("new") airplane_dto dto, Model m) {
		this.script = "";
				
			int result = pd.insert_airplane(dto);
			if(result > 0) {
				this.script = "<script>"
						+ "alert('정상적으로 신규항공편 등록이 완료되었습니다.');"
						+ "location.href = 'product_list.do';"
						+ "</script>";
			}
			else {
				this.script = "<script>"
						+ "alert('시스템 오류로 인하여 신규항공편 등록에 실패했습니다.');"
						+ "location.href = 'product_list.do';"
						+ "</script>";
			}
		
		m.addAttribute("script",this.script);
		
		return "script";
	}
	
	@GetMapping("/find_flight.do")
	public String find_flight(@RequestParam(defaultValue = "" , required = false) String aircode ,Model m) {
		
		aircode_dto data = pd.air_flight(aircode);
		
		m.addAttribute("fly",data);
		
		return null;
	}
	
	@GetMapping("/product_new.do")
	public String prdouct_new(@RequestParam(defaultValue = "", required = false) String airplane, @RequestParam(defaultValue = "" , required = false) String airname, @RequestParam(defaultValue = "" , required = false) String aircode ,Model m) {
		
		List<aircode_dto> list = pd.select_code();
		List<aircode_dto> code = pd.select_code2();
		aircode_dto data = pd.air_flight(aircode);
		List<aircode_dto> airn = pd.find_airname(airplane);
		List<aircode_dto> airc = pd.air_aircode(airname);
		
		m.addAttribute("airc",airc);
		m.addAttribute("airp",airplane);
		m.addAttribute("airn",airname);
		m.addAttribute("airname",airn);
		m.addAttribute("list",list);
		m.addAttribute("plane",code);
		
		return "product_new";
	}

	@GetMapping("/air_newcode.do")
	public String nowcode() {
		
		return "air_newcode";
	}
	
	@PostMapping("/create_code.do")
	public String create_code(@ModelAttribute("code") aircode_dto dto, Model m) {
		this.script = "";
		
		int result = pd.insert_aircode(dto);
		if(result > 0) {
			this.script = "<script>"
					+ "alert('정상적으로 항공코드가 생성되었습니다.');"
					+ "location.href='./air_list.do';"
					+ "</script>";
		}
		else {
			this.script = "<script>"
					+ "alert('시스템오류로 인하여 항공코드 생성에 실패했습니다.');"
					+ "location.href='./air_list.do';"
					+ "</script>";
		}
		
		m.addAttribute("script",this.script);
		return "script";
	}
	
	@GetMapping("/check.do")
	public String check(@RequestParam(defaultValue = "", required = false) String airplane,
			@RequestParam(defaultValue = "", required = false) String airname,
			@RequestParam(defaultValue = "", required = false) String aircode,
			@RequestParam(required = false) String part, Model m) {
			String msg = "";
			
			List<aircode_dto> data = null;
			
			if(part == null) {
				msg = "error";
			}
			else {
				if(part.equals("2")) {
					data = pd.code_check(airname, "2");
					
					if(data.size() == 0) {
						msg = "ok";
					}
					else {
						int i=0;
						while(i<data.size()) {
							if(data.get(i).getAirname().equals(airname)) {
								msg = "no";
								break;
							}
							else {
								msg = "ok";
							}
							i++;
						}
					}
				}
				else {
					data = pd.code_check(aircode, "3");
					
					if(data.size() == 0) {
						msg = "ok";
					}
					else {
						int i=0;
						while(i<data.size()) {
							if(data.get(i).getAircode().equals(aircode)) {
								msg = "no";
								break;
							}
							else {
								msg = "ok";
							}
							i++;
						}
					}
				}
			}
			
			m.addAttribute("msg",msg);
		
		return null;
	}
	
	@GetMapping("/delete_airlist.do")
	public String delete_air(@RequestParam(defaultValue = "" , required = false) String airidx, Model m) {
		
		int result = pd.delete_airlist(airidx);
		if(result > 0) {
			this.script = "<script>"
					+ "alert('정상적으로 항공코드가 삭제되었습니다.');"
					+ "location.href='./product_list.do';"
					+ "</script>";
		}
		else {
			this.script = "<script>"
					+ "alert('시스템 오류로 인하여 항공코드 삭제에 실패했습니다.');"
					+ "location.href='./product_list.do';"
					+ "</script>";
		}
		
		m.addAttribute("script",this.script);
		
		return "script";
	}
	
	@GetMapping("/delete_product.do")
	public String delete_list(@RequestParam(defaultValue = "" , required = false) String pidx, Model m) {
		
		int result = pd.delete_product(pidx);
		if(result > 0) {
			this.script = "<script>"
					+ "alert('정상적으로 항공편이 삭제되었습니다.');"
					+ "location.href='./product_list.do';"
					+ "</script>";
		}
		else {
			this.script = "<script>"
					+ "alert('시스템 오류로 인하여 항공편 삭제에 실패했습니다.');"
					+ "location.href='./product_list.do';"
					+ "</script>";
		}
		
		m.addAttribute("script",this.script);
		
		return "script";
	}

	
	@RequestMapping("/air_list.do")
	public String air_list(@RequestParam(defaultValue = "", required = false) String search, @RequestParam(defaultValue = "", required = false) String part, Model m) {
		
		List<aircode_dto> list = null;
		list = pd.select_code();
		
		if(search.equals("")) {
		}
		else {
			m.addAttribute("username",search);
			m.addAttribute("no",part);
			
			if(part.equals("1")) {
				list = pd.search_code(search, "1");
			}
			else if(part.equals("2")) {
				list = pd.search_code(search, "2");
			}

		}
		
		m.addAttribute("list",list);
		
		return null;
	}
	
	@RequestMapping("/product_list.do")
	public String product_list(@RequestParam(defaultValue = "", required = false) String search, @RequestParam(defaultValue = "", required = false) String part, Model m) {
		
		List<airplane_dto> list = null;
		
		if(search.equals("")) {
			list = pd.select_product();
		}
		else {
			m.addAttribute("username",search);
			m.addAttribute("no",part);
			
			if(part.equals("1")) {
				list = pd.search_product(search, "1");
			}
			else if(part.equals("2")) {
				list = pd.search_product(search, "2");
			}			
		}
		m.addAttribute("list",list);
		
		return null;
	}
	
}
