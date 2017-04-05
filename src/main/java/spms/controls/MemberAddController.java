package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

import java.util.Map;

/**
 * Created by wayne on 2017. 3. 15..
 *
 */
@Component("/member/add.do")
public class MemberAddController implements Controller, DataBinding {

	private MemberDao memberDao;

	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member) model.get("member");

		if (member.getEmail() == null) {
			return "/member/MemberForm.jsp";
		}

		memberDao.insert(member);

		return "redirect:list.do";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"member", Member.class
		};
	}
}
