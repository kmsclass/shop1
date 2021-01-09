package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import logic.Item;

public class ItemDao {
	private NamedParameterJdbcTemplate template;
	//Item 클래스의 프로퍼티와 db의 컬럼명을 매핑.
	private RowMapper<Item> mapper = 
	    new BeanPropertyRowMapper<Item>(Item.class);
	
	//dataSource : db 접속 객체. spring-db.xml에 설정됨.
	//             spring-mvc.xml 파일의 설정으로 ItemDao 객체에 주입됨. 
	public void setDataSource(DataSource dataSource) {
		//spring의 jdbc 프레임워크 
		this.template = new NamedParameterJdbcTemplate(dataSource);
	}
	public List<Item> list() {
		return template.query("select * from item",mapper);
	}
	public Item selectOne(Integer id) {
		Map<String,Integer> param = new HashMap<>();
		param.put("id", id);
		return template.queryForObject  //레코드 1개만 조회
				("select * from item where id=:id",param,mapper);
	}
}
