package spms.controls;

import lombok.Setter;
import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;

import java.util.Map;

@Setter
@Component("/project/delete.do")
public class ProjectDeleteController implements Controller, DataBinding {

	private ProjectDao projectDao;

	@Override
	public Object[] getDataBinders() {
		return new Object[]{
			"no", Integer.class
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		int no = (int) model.get("no");

		projectDao.delete(no);

		return "redirect:list.do";
	}
}
