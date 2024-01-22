package aaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("faq")
public class faq {

	@Resource(name="sqlSession3")
	private SqlSessionTemplate tm;
	
	public int update_faq(faq_dto dto) {
		
		int result = tm.update("seatdao.update_faq",dto);
		
		return result;
	}
	
	public faq_dto view_faq(String fidx){
		
		faq_dto list = tm.selectOne("seatdao.view_faq",fidx);
		
		return list;
	}
	
	public int del_faq(String fidx) {
		
		int result = tm.delete("seatdao.faq_delete",fidx);
		
		return result;
	}
	
	public List<faq_dto> search_faq(String search, int no){
		
		String num = Integer.toString(no);
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("search", search);
		m.put("no", num);
		
		List<faq_dto> list = new ArrayList<faq_dto>();
		list = tm.selectList("seatdao.faq_search",m);
		
		return list;
	}
	
	public Integer faq_search_ctn(String search) {
		
		Integer ctn = tm.selectOne("seatdao.count_faq_search",search);
		int ea = (int)Math.ceil(ctn/10f);
		
		return ea;
	}
	
	public Integer faq_ctn() {
		
		Integer ctn = tm.selectOne("seatdao.count_faq");
		int ea = (int)Math.ceil(ctn/10f);
		
		return ea;
	}
	
	public List<faq_dto> select_faq(int no){
		
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("no", no);

		List<faq_dto> list = new ArrayList<faq_dto>();
		list = tm.selectList("seatdao.select_faq",m);
		
		return list;
	}
	
	public int insert_faq(faq_dto dto) {
		
		int result = tm.insert("seatdao.insert_faq",dto);
		
		return result;
	}
	
}
