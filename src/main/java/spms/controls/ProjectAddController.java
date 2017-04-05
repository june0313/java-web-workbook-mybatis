package spms.controls;

import lombok.Setter;
import lombok.experimental.Accessors;
import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

import java.util.Map;

@Component("/project/add.do")
@Setter
@Accessors(chain = true)
public class ProjectAddController implements Controller, DataBinding {

	private ProjectDao projectDao;

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project) model.get("project");
		if (project.getTitle() == null) {
			return "/project/ProjectForm.jsp";
		}

		projectDao.insert(project);

		return "redirect:list.do";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[]{
			"project",
			Project.class
		};
	}
}
