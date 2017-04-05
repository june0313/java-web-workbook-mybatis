package spms.controls;

import lombok.Setter;
import lombok.experimental.Accessors;
import spms.annotation.Component;
import spms.dao.ProjectDao;
import spms.vo.Project;

import java.util.List;
import java.util.Map;

@Setter
@Accessors(chain = true )
@Component("/project/list.do")
public class ProjectListController implements Controller {

	private ProjectDao projectDao;

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		List<Project> projects = projectDao.selectList();
		model.put("projects", projects);
		return "/project/ProjectList.jsp";
	}

}
