package cn.jxy.personnel.dao;

import java.util.List;

import cn.jxy.personnel.entity.User;
import cn.jxy.personnel.entity.ViewUser;
import cn.jxy.personnel.util.PageCut;

public interface IUserDao {
	
	public boolean add(User user);
	
	public boolean delete(int id);
	
	public boolean update(User user);
	
	public User findOne(String condition,Object...objects);
	
	public PageCut<User> finds(int curr,int size,String condition,Object...objects);
	
	public List<ViewUser> count(String content);
}
