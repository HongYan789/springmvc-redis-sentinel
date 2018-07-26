package view;

import com.lppz.ehr.bean.DingUser;
import com.lppz.ehr.bean.User;
import com.lppz.ehr.util.DateTool;

import java.util.HashMap;

public class UserView extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public UserView(User user, DingUser dingUser) {
		if(user == null) {
			return;
		}
		
		put("id", user.getId());
		put("name", user.getName());

		if(user.getCreateTime() != null) {
			put("createTime", DateTool.standardSdf.format(user.getCreateTime()));
		}

	}
	
}
