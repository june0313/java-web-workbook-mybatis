package spms.dao;

import com.google.common.collect.Maps;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import spms.annotation.Component;
import spms.vo.Project;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component("projectDao")
@Setter
public class MySqlProjectDao implements ProjectDao {

	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Project> selectList(Map<String, Object> paramMap) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			return sqlSession.selectList("spms.dao.ProjectDao.selectList", paramMap);
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
			Project original = sqlSession.selectOne("spms.dao.ProjectDao.selectOne", project.getNo());
			Map<String, Object> paramMap = Maps.newHashMap();

			Optional.ofNullable(project.getTitle()).filter(title -> !title.equals(original.getTitle())).ifPresent(title -> paramMap.put("title", title));
			Optional.ofNullable(project.getContent()).filter(content -> !content.equals(original.getContent())).ifPresent(content -> paramMap.put("content", content));
			Optional.ofNullable(project.getStartDate()).filter(date -> date.compareTo(original.getStartDate()) != 0).ifPresent(date -> paramMap.put("startDate", date));
			Optional.ofNullable(project.getEndDate()).filter(date -> date.compareTo(original.getEndDate()) != 0).ifPresent(date -> paramMap.put("endDate", date));
			Optional.of(project.getState()).filter(state -> state != (original.getState())).ifPresent(state -> paramMap.put("state", state));
			Optional.ofNullable(project.getTags()).filter(tags -> !tags.equals(original.getTags())).ifPresent(tags -> paramMap.put("tags", tags));

			if (paramMap.size() > 0) {
				paramMap.put("no", project.getNo());
				int count = sqlSession.update("spms.dao.ProjectDao.update", paramMap);
				sqlSession.commit();
				return count;
			}

			return 0;
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
