package cn.jxy.personnel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jxy.personnel.dao.IUserDao;
import cn.jxy.personnel.entity.User;
import cn.jxy.personnel.entity.ViewUser;
import cn.jxy.personnel.service.IUserService;
import cn.jxy.personnel.util.PageCut;
/**
 * 
 * @author Administrator
 * 人员管理业务层
 */
@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	
	@Override
	public boolean add(User user) {
		return userDao.add(user);
	}

	@Override
	public boolean delete(int id) {
		return userDao.delete(id);
	}

	@Override
	public boolean update(User user) {
		return userDao.update(user);
	}

	@Override
	public User findOne(String condition,Object...objects) {
		return userDao.findOne(condition,objects);
	}
	
	@Override
	public PageCut<User> finds(int curr, int size, String condition, Object... objects) {
		
		return userDao.finds(curr, size, condition, objects);
	}

	@Override
	public List<ViewUser> count(String content) {
		
		return userDao.count(content);
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	
	
}
