package aaa;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("info")
public class info {

	@Resource(name="sqlSession")
	private SqlSessionTemplate tm;

	public int update_info(siteinfo_dto dto) {
		
		int result = tm.update("admin.update_siteinfo",dto);
		
		return result;
	}
	
	public siteinfo_dto select_siteinfo() {
		
		siteinfo_dto dto = tm.selectOne("admin.select_info");
		
		return dto;
	}
	
}
