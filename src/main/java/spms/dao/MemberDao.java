package spms.dao;

import spms.vo.Member;

import java.util.List;
import java.util.Map;

/**
 * Created by wayne on 2017. 3. 17..
 *
 */
public interface MemberDao {
	List<Member> selectList(Map<String, Object> paramMap) throws Exception;

	int insert(Member member) throws Exception;

	int delete(int no) throws Exception;

	Member selectOne(int no) throws Exception;

	int update(Member member) throws Exception;

	Member exist(String email, String password) throws Exception;
}
