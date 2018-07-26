package com.lppz.ehr.mapper;

import com.lppz.ehr.bean.DingUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/** 
 * @Description: TODO
 * @author: DONG
 * @date: 2018年6月20日  
 */
@Component
public interface DingUserMapper extends MapperI<DingUser>{
		void insert(DingUser dingUser);
		void update(DingUser dingUser);
		DingUser  selectAllByDingid(@Param("jobnumber") String jobnumber);
		Map<String, Object>  selectSapidByEmployee(Map<String, Object> pars);
		Map<String, Object>  selectDeptBysapid(String sapid);
		Map<String, Object>  selectPDeptBysapid(String parentid);
		List<Map<String, Object>>  selectSonDeptBysapid(String sapid);
		List<Map<String, Object>> selectPersonInfoBysapid(String sapid);
		List<Map<String, Object>> selectDeptInfoByDescOrDeptName(Map<String, Object> map);
		List<Map<String, Object>> selectPersonInfoByNameOrJobNo(Map<String, Object> map);
		List<Map<String, Object>> selectPersonInfoByJobNo(Map<String, Object> map);
		Map<String, Object> selectAvatarByJobNo(String jobNo);
		Map<String, Object> selectPostByStationId(String stationId);
}
