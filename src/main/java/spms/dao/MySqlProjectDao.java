package spms.dao;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import spms.annotation.Component;
import spms.vo.Project;

import java.util.List;

@Component("projectDao")
@Setter
public class MySqlProjectDao implements ProjectDao {

	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Project> selectList() throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			return sqlSession.selectList("spms.dao.ProjectDao.selectList");
		}
	}

	@Override
	public int insert(Project project) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			int count = sqlSession.insert("spms.dao.ProjectDao.insert", project);
			sqlSession.commit();
			return count;
		}
	}

	@Override
	public Project selectOne(int no) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			return sqlSession.selectOne("spms.dao.ProjectDao.selectOne", no);
		}
	}

	@Override
	public int update(Project project) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			int count = sqlSession.update("spms.dao.ProjectDao.update", project);
			sqlSession.commit();
			return count;
		}
	}

	@Override
	public int delete(int no) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			int count = sqlSession.delete("spms.dao.ProjectDao.delete", no);
			sqlSession.commit();
			return count;
		}
	}

}
