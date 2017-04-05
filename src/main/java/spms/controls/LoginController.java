package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wayne on 2017. 3. 15..
 *
 */
@Component("/auth/login.do")
public class LoginController implements Controller, DataBinding {

	private MemberDao memberDao;

	public LoginController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		Member loginInfo = (Member) model.get("loginInfo");

		String email = loginInfo.getEmail();
		String password = loginInfo.getPassword();

		if (email == null && password == null) {
			return "/auth/LogInForm.jsp";
		}

		Member member = memberDao.exist(email, password);

		if (member != null) {
			session.setAttribute("member", member);
			return "redirect:../member/list.do";
		}

		return "redirect:/auth/LogInFail.jsp";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"loginInfo", Member.class
		};
	}
}
