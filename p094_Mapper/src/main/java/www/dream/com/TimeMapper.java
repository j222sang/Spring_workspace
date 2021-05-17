package www.dream.com;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	//제한적으로만 쓰임
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTimeByXML();
}
