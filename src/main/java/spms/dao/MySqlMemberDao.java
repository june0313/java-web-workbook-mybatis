package spms.dao;

// DataSource 활용 

import com.google.common.collect.Maps;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import spms.annotation.Component;
import spms.vo.Member;

import java.util.List;
import java.util.Map;

@Component("memberDao")
@Setter
public class MySqlMemberDao implements MemberDao {
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Member> selectList(Map<String, Object> paramMap) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			return sqlSession.selectList("spms.dao.MemberDao.selectList", paramMap);
		}
	}

	@Override
	public int insert(Member member) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			int count = sqlSession.insert("spms.dao.MemberDao.insert", member);
			sqlSession.commit();
			return count;
		}
	}

	@Override
	public int delete(int no) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			int count = sqlSession.delete("spms.dao.MemberDao.delete", no);
			sqlSession.commit();
			return count;
		}
	}

	@Override
	public Member selectOne(int no) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			 return sqlSession.selectOne("spms.dao.MemberDao.selectOne", no);
		}
	}

	@Override
	public int update(Member member) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			Member original = sqlSession.selectOne("spms.dao.MemberDao.selectOne", member.getNo());

			Map<String, Object> paramMap = Maps.newHashMap();

			if (!original.getEmail().equals(member.getEmail())) {
				paramMap.put("email", member.getEmail());
			}

			if (!original.getName().equals(member.getName())) {
				paramMap.put("name", member.getName());
			}

			if (!paramMap.isEmpty()) {
				paramMap.put("no", member.getNo());
				int count = sqlSession.update("spms.dao.MemberDao.update", paramMap);
				sqlSession.commit();
				return count;
			}

			return 0;
		}
	}

	@Override
	public Member exist(String email, String password) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			Map<String, Object> paramMap = Maps.newHashMap();
			paramMap.put("email", email);
			paramMap.put("password", password);
			return sqlSession.selectOne("spms.dao.MemberDao.exist", paramMap);
		}
	}
}
