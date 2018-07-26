package view;

import com.lppz.ehr.bean.DingUser;

import java.util.HashMap;

public class DingUserView  extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public DingUserView(DingUser dingUser) {
		put("userid", dingUser.getUserid());
		put("name", dingUser.getName());
		put("mobile", dingUser.getMobile());
		put("orderInDepts", dingUser.getOrderInDepts());
		put("jobnumber", dingUser.getJobnumber());
		put("avatar", dingUser.getAvatar());
		put("department", dingUser.getDepartment());
		put("email", dingUser.getEmail());


//		put("active", dingUser.getActive());
//		put("isAdmin", dingUser.getIsAdmin());
//		put("isBoss", dingUser.getIsBoss());
//		put("isHide", dingUser.getIsHide());
//		put("isLeaderInDepts", dingUser.getIsLeaderInDepts());
//		put("position", dingUser.getPosition());
//		put("remark", dingUser.getRemark());
//		put("senior", dingUser.getSenior());
//		put("tel", dingUser.getTel());
//		put("workPlace", dingUser.getWorkPlace());

	}
}
