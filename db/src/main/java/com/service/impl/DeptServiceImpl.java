package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DeptDao;
import com.domain.DeptDO;
import com.service.DeptService;

import java.util.List;
import java.util.Map;





@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao deptDao;
	
	@Override
	public DeptDO get(Long deptId){
		return deptDao.get(deptId);
	}
	
	@Override
	public List<DeptDO> list(Map<String, Object> map){
		return deptDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deptDao.count(map);
	}
	
	@Override
	public int save(DeptDO dept){
		return deptDao.save(dept);
	}
	
	@Override
	public int update(DeptDO dept){
		return deptDao.update(dept);
	}
	
	@Override
	public int remove(Long deptId){
		return deptDao.remove(deptId);
	}
	
	@Override
	public int batchRemove(Long[] deptIds){
		return deptDao.batchRemove(deptIds);
	}
	
}
