package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;

import java.util.Map;

/**
 * Created by wayne on 2017. 3. 15..
 *
 */
@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {

	private MemberDao memberDao;

	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Integer no = (Integer) model.get("no");

		memberDao.delete(no);

		return "redirect:list.do";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"no", Integer.class
		};
	}
}
