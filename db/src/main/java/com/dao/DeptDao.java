package com.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.domain.DeptDO;

/**
 * 部门管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-12-19 21:41:36
 */
@Mapper
public interface DeptDao {

	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DeptDO dept);
	
	int update(DeptDO dept);
	
	int remove(Long dept_id);
	
	int batchRemove(Long[] deptIds);
}
