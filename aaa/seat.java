package aaa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("se")
public class seat {
	
	@Resource(name="sqlSession3")
	private SqlSessionTemplate tm;
	
	public List<airuser_dto> search_user(String search, String part){
		
		List<airuser_dto> list = new ArrayList<airuser_dto>();
		Map<String, String> m = new HashMap<String, String>();
		m.put("search", search);
		m.put("part", part);
		
		list = tm.selectList("seatdao.search_user",m);
		
		return list;
	}
	
	public int delete_user(String uidx) {
		
		int result = tm.delete("seatdao.delete_user",uidx);
		
		return result;
	}
	
	public List<airuser_dto> select_user(){
		
		List<airuser_dto> list = new ArrayList<airuser_dto>();
		list = tm.selectList("seatdao.select_user");
		
		return list;
	}
	
	public List<seat_dto> search_pidx() {
		
		List<seat_dto> result = new ArrayList<seat_dto>();
		
		result = tm.selectList("seatdao.count_pidx");
		
		return result;
	}
	
	public int insert_seat(seat_dto dto) {
		int result = 0;
				
		String[] pidx = dto.getPidx().split(",");
		String[] start_date = dto.getStart_date().split(",");
		String[] end_date = dto.getEnd_date().split(",");
		String[] rprice = dto.getRprice().split(",");

		Map<String, String> m = new HashMap<String, String>();
		
		if(pidx.length == 1) {
			result = tm.insert("seatdao.insert_seat",dto);
			tm.update("seatdao.update_resok",pidx[0]);
		}
		else if(pidx.length > 1){
			int i = 0;
			while(i<pidx.length) {
				
				if(!start_date[i].equals("")  && !end_date[i].equals("") && !rprice[i].equals("")) {
					m.put("pidx",pidx[i]);
					m.put("start_date",start_date[i]);
					m.put("end_date",end_date[i]);
					m.put("rprice",rprice[i]);
					
					result = tm.insert("seatdao.insert_seat2",m);
					tm.update("seatdao.update_resok",pidx[i]);
				}
				i++;
			}
		}
		
		return result;
	}

}
