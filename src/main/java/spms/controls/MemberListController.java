package spms.controls;

import com.google.common.collect.Maps;
import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;

import java.util.Map;

/**
 * Created by wayne on 2017. 3. 15..
 *
 */
@Component("/member/list.do")
public class MemberListController implements Controller, DataBinding {

	private MemberDao memberDao;

	public MemberListController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("orderCond", model.get("orderCond"));
		model.put("members", memberDao.selectList(paramMap));
		return "/member/MemberList.jsp";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"orderCond", String.class
		};
	}
}
