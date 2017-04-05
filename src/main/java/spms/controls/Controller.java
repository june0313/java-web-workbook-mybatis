package spms.controls;

import java.util.Map;

/**
 * Created by wayne on 2017. 3. 15..
 *
 */
public interface Controller {
	String execute(Map<String, Object> model) throws Exception;
}
