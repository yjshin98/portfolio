package aaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("product")
public class product {

	@Resource(name="sqlSession2")
	private SqlSessionTemplate tm;
	
	public List<airplane_dto> resok_list(){
		
		List<airplane_dto> result = tm.selectList("product.resok");
		
		return result;
	}
	
	public List<airplane_dto> resno_list(String no) {
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("part",no);
		
		List<airplane_dto> result = tm.selectList("product.product_search",m);
		
		return result;
	}
	
	public int update_code_modifyok(aircode_dto dto) {
		
		int result = tm.update("product.update_code",dto);
		
		return result;
	}
	
	public aircode_dto code_modifyview(String airidx){
		
		aircode_dto result = tm.selectOne("product.modifycode_view",airidx);
		
		return result;
	}
	
	public int delete_airlist(String airidx) {
		
		int result = tm.delete("product.delete_airlist",airidx);
		
		return result;
	}
	
	public int delete_product(String pidx) {
		
		int result = tm.delete("product.delete_product",pidx);
		
		return result;
	}
	
	public int insert_airplane(airplane_dto dto) {
		
		int result = tm.insert("product.insert_airplane",dto);
		
		return result;
	}
	
	public List<aircode_dto> find_airname(String airplane){
		
		List<aircode_dto> list = new ArrayList<aircode_dto>();
		list = tm.selectList("product.find_airname",airplane);
		
		return list;
	}
	
	public List<aircode_dto> air_aircode(String airname) {
		
		List<aircode_dto> list = new ArrayList<aircode_dto>();
		list = tm.selectList("product.find_code",airname);
		
		
		return list;
	}
	
	public aircode_dto air_flight(String aircode) {
		
		aircode_dto result = tm.selectOne("product.find_flight",aircode);
		
		return result;
	}
	
	public List<aircode_dto> search_code(String search, String part){
		List<aircode_dto> list = new ArrayList<aircode_dto>();
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("search", search);
		m.put("part", part);
		
		list = tm.selectList("product.code_search",m);
		
		return list;
	}
	
	public List<aircode_dto> select_code(){
		
		List<aircode_dto> result = new ArrayList<aircode_dto>();
		
		result = tm.selectList("product.product_code");
		
		return result;
	}
	
	public List<aircode_dto> select_code2(){
		
		List<aircode_dto> result = new ArrayList<aircode_dto>();
		Map<String, String> m = new HashMap<String, String>();
		m.put("part", "1");
		
		result = tm.selectList("product.product_code",m);
		
		return result;
	}
	
	public int insert_aircode(aircode_dto dto) {
		
		int result = tm.insert("product.insert_product",dto);
		
		return result;
	}
	
	public List<aircode_dto> code_check(String ck, String part) {
		List<aircode_dto> result = new ArrayList<aircode_dto>();
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("ck", ck);
		m.put("part", part);
		
		result = tm.selectList("product.productck",m);		
		
		return result;
	}
	
	public List<airplane_dto> search_product(String search, String part){
		List<airplane_dto> list = new ArrayList<airplane_dto>();
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("search", search);
		m.put("part", part);
		
		list = tm.selectList("product.product_search",m);
		
		return list;
	}
	
	public List<airplane_dto> select_product(){
		List<airplane_dto> list = new ArrayList<airplane_dto>();
		
		list = tm.selectList("product.product_list");
		
		return list;
	}
	
}
