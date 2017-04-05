package spms.controls;

import spms.annotation.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wayne on 2017. 3. 15..
 *
 */
@Component("/auth/logout.do")
public class LogOutController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		session.invalidate();
		return "redirect:../member/list.do";
	}

}
