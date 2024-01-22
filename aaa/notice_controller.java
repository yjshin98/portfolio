package aaa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class notice_controller {
	String script = "";
	
	@Resource(name="notice")
	private notice no;
	
	@PostMapping("/modifyok_notice.do")
	public String modifyok_motice(@ModelAttribute("modify") notice_dto dto, @RequestParam("files") MultipartFile nfile, Model m) {
		this.script = "";
		
		if(nfile.getOriginalFilename() == "") {
			if(dto.getNprint() == null) {
				dto.setNprint("N");
				int result = no.update_notice(dto);
				if(result > 0) {
					this.script = "<script>"
							+ "alert('정상적으로 공지사항이 수정되었습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
				else {
					this.script = "<script>"
							+ "alert('시스템 오류로 인하여 공지사항 수정에 실패했습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
			}else {
				int result = no.update_notice(dto);
				if(result > 0) {
					this.script = "<script>"
							+ "alert('정상적으로 공지사항이 수정되었습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
				else {
					this.script = "<script>"
							+ "alert('시스템 오류로 인하여 공지사항 수정에 실패했습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
			}
		}
		else {
			if(dto.getNprint() == null) {
				dto.setNprint("N");
				String url = no.fileok(nfile);
				dto.setNfile(url);
				int result = no.update_notice(dto);
				
				if(result > 0) {
					this.script = "<script>"
							+ "alert('정상적으로 공지사항이 수정되었습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
				else {
					this.script = "<script>"
							+ "alert('시스템 오류로 인하여 공지사항 수정에 실패했습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
			}else {
				String url = no.fileok(nfile);
				dto.setNfile(url);
				int result = no.update_notice(dto);
				
				if(result > 0) {
					this.script = "<script>"
							+ "alert('정상적으로 공지사항이 수정되었습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
				else {
					this.script = "<script>"
							+ "alert('시스템 오류로 인하여 공지사항 수정에 실패했습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
			}
			
			
		}
		
		m.addAttribute("script",this.script);
		return "script";
	}
	
	@GetMapping("/notice_write.do")
	public String notice_write() {
		
		return "admin_notice_write";
	}
	
	
	@GetMapping("/modify_notice.do")
	public String modify_notice(@RequestParam(defaultValue = "", required = false) String nidx, Model m) {
		this.script = "";
		notice_dto result = null;
		if(nidx.equals("")) {
			this.script = "<script>"
					+ "alert('잘못된 접근입니다.');"
					+ "location.href='./notice_list.do';"
					+ "</script>";
		}
		else {
			result = no.noticeview(nidx);
			m.addAttribute("list",result);
		}
		m.addAttribute("script",this.script);
		
		return "notice_modify";
	}
	
	@GetMapping("/delete_notice.do")
	public String deleteok_notice(@RequestParam(defaultValue = "", required = false) String nidx, Model m) {
		this.script = "";
		int result = no.delete_notice(nidx);
		if(result > 0) {
			this.script = "<script>"
					+ "alert('정상적으로 공지사항이 삭제되었습니다.');"
					+ "location.href='./notice_list.do';"
					+ "</script>";
		}
		else {
			this.script = "<script>"
					+ "alert('시스템 오류로 인하여 공지사항이 삭제되지 않았습니다.');"
					+ "location.href='./notice_list.do';"
					+ "</script>";
		}
		m.addAttribute("script",this.script);
		return "script";
	}
	
	@GetMapping("/notice_check.do")
	public String notice_check(@RequestParam(defaultValue = "", required = false) String nidx, @RequestParam(defaultValue = "", required = false) String part, Model m) {
		this.script = "";
		
		int result = no.update_check(nidx, part);
		if(result > 0) {
			this.script = "<script>"
					+ "alert('정상적으로 수정되었습니다.');"
					+ "location.href='./notice_view.do?nidx="+nidx+"';"
					+ "</script>";
		}
		else {
			this.script = "<script>"
					+ "alert('시스템 오류로 인하여 수정되지 않았습니다.');"
					+ "location.href='./notice_view.do?nidx="+nidx+"';"
					+ "</script>";
		}
		m.addAttribute("script",this.script);
		
		return "script";
	}
	
	@GetMapping("/notice_view.do")
	public String notice_view(@RequestParam(defaultValue = "", required = false) String nidx, Model m) {
		this.script = "";
		notice_dto result = null;
		int success = 0;
		if(nidx.equals("")) {
			this.script = "<script>"
					+ "alert('잘못된 접근입니다.');"
					+ "location.href='./notice_list.do';"
					+ "</script>";
		}
		else {
			result = no.noticeview(nidx);
			success = no.count(nidx);
			m.addAttribute("list",result);
		}
		m.addAttribute("script",this.script);
		
		return "notice_view";
	}

	@GetMapping("/notice_list.do")
	public String notice_list(Model m) {
		
		List<notice_dto> data = new ArrayList<notice_dto>();
		data = no.select_notice();
		
		m.addAttribute("data",data);
		
		return "notice_list";
	}
	
	@PostMapping("/notice_writeok.do")
	public String notice_write(@ModelAttribute("nwrite") notice_dto dto,@RequestParam("files") MultipartFile nfile, Model m) {
		this.script = "";
		
		if(nfile.getOriginalFilename() == "") {
			if(dto.getNprint() == null) {
				dto.setNprint("N");
				int result = no.notice_insert(dto);
				if(result > 0) {
					this.script = "<script>"
							+ "alert('정상적으로 공지사항이 등록되었습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
				else {
					this.script = "<script>"
							+ "alert('시스템 오류로 인하여 공지사항 등록에 실패했습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
			}
			else {
				int result = no.notice_insert(dto);
				if(result > 0) {
					this.script = "<script>"
							+ "alert('정상적으로 공지사항이 등록되었습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
				else {
					this.script = "<script>"
							+ "alert('시스템 오류로 인하여 공지사항 등록에 실패했습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
			}
		}
		else {
			if(dto.getNprint() == null) {
				dto.setNprint("N");
				String url = no.fileok(nfile);
				dto.setNfile(url);
				int result = no.notice_insert(dto);
				
				if(result > 0) {
					this.script = "<script>"
							+ "alert('정상적으로 공지사항이 등록되었습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
				else {
					this.script = "<script>"
							+ "alert('시스템 오류로 인하여 공지사항 등록에 실패했습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
			}else {
				String url = no.fileok(nfile);
				dto.setNfile(url);
				int result = no.notice_insert(dto);
				
				if(result > 0) {
					this.script = "<script>"
							+ "alert('정상적으로 공지사항이 등록되었습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
				else {
					this.script = "<script>"
							+ "alert('시스템 오류로 인하여 공지사항 등록에 실패했습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				}
			}
			
			
		}
		
		m.addAttribute("script",this.script);
		return "script";
	}
	
}
