package spms.controls;

import lombok.Setter;
import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

import java.util.Map;

@Setter
@Component("/project/update.do")
public class ProjectUpdateController implements Controller, DataBinding {

	private ProjectDao projectDao;

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project) model.get("project");
		if (project.getTitle() == null) {
			Integer no = (Integer) model.get("no");
			model.put("project", projectDao.selectOne(no));
			return "/project/ProjectUpdateForm.jsp";
		}

		projectDao.update(project);
		return "redirect:list.do";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[]{
			"no", Integer.class,
			"project", Project.class
		};
	}
}
