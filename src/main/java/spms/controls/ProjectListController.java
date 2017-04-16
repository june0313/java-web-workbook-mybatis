package spms.controls;

import com.google.common.collect.Maps;
import lombok.Setter;
import lombok.experimental.Accessors;
import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

import java.util.List;
import java.util.Map;

@Setter
@Accessors(chain = true )
@Component("/project/list.do")
public class ProjectListController implements Controller, DataBinding {

	private ProjectDao projectDao;

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("orderCond", model.get("orderCond"));

		List<Project> projects = projectDao.selectList(paramMap);
		model.put("projects", projects);
		return "/project/ProjectList.jsp";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"orderCond", String.class
		};
	}
}
