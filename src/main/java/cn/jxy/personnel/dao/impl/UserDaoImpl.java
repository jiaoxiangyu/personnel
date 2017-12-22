package cn.jxy.personnel.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import cn.jxy.personnel.base.BaseDao;
import cn.jxy.personnel.dao.IUserDao;
import cn.jxy.personnel.entity.User;
import cn.jxy.personnel.entity.ViewUser;
import cn.jxy.personnel.util.PageCut;
/**
 * 
 * @author Administrator
 * 人员管理数据层
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements IUserDao {

	@Override
	public boolean add(User user) {		
		return this.saveEntity(user);
	}

	@Override
	public boolean delete(int id) {
		return this.deleteEntity(id);
	}

	@Override
	public boolean update(User user) {
		return this.updateEntity(user);
	}

	@Override
	public User findOne(String condition,Object...objects) {
		if(condition.equals("login")) {
			String hql="from User user where (user.phone=? or user.email=? ) and user.password=? ";
			return (User)this.uniqueResult(hql,objects);
		}else if(condition.equals("id")) {
			return (User)this.getEntity((int)objects[0]);
		}else if(condition.equals("only")) {
			String hql="from User user where user.phone=? or user.email=?  ";
			return (User)this.uniqueResult(hql,objects);
		}
		return null;
	}

	@Override
	public List<ViewUser> count(String content) {
		List<ViewUser> list=new ArrayList<>();
		StringBuilder sql=new StringBuilder("select ").append(content)
				.append(",count(*) from tb_user group by ").append(content);
		logger.info(sql.toString());
		
		Query query = getSession().createSQLQuery(sql.toString());
		Iterator<Object[]> it = query.list().iterator();
	    while(it.hasNext()){
		    Object[] obj = it.next();
		    ViewUser viewUser = new ViewUser();
		    viewUser.setCondition(obj[0].toString());
		    viewUser.setNum(Integer.valueOf(obj[1].toString()));
		    list.add(viewUser);
	   }	  
	   return list;
	}

	@Override
	public PageCut<User> finds(int curr, int size, String condition, Object... objects) {
		if(condition.equals("all")) {
			String hql="from User";
			int count= this.getEntityNum(hql);
			PageCut<User> pc=new PageCut<>(curr, size, count);
			pc.setData(this.getEntityLimitList(hql, (curr-1)*size, size));
			return pc;
		}else if(condition.equals("excel")) {
			StringBuilder hql=null;
			if(objects[0].equals("all")) {
				hql=new StringBuilder("from User ");
			}else {
				hql=new StringBuilder("from User u where u.")
						.append(objects[0])
						.append(" like '%")
						.append(objects[1])
						.append("%'");					
			}
								
			PageCut<User> pc=new PageCut<>();			
			pc.setData(this.getEntityList(hql.toString()));
			return pc;
			
		}else if(condition.equals("search")) {
			StringBuilder hql=new StringBuilder("from User u where u.username like '%")
					.append(objects[0]).append("%' or u.sex like '%")
					.append(objects[0]).append("%' or u.education like '%")
					.append(objects[0]).append("%' or u.industry like '%")
					.append(objects[0]).append("%' or u.address like '%")
					.append(objects[0]).append("%' or u.politicalStatus like '%")					
					.append(objects[0]).append("%' or u.email like '%")
					.append(objects[0]).append("%'");
			int count= this.getEntityNum(hql.toString());
			logger.info(hql.toString());
			PageCut<User> pc=new PageCut<>(curr, size, count);
			pc.setData(this.getEntityLimitList(hql.toString(), (curr-1)*size, size));
			return pc;
		}
		
		return null;
	}

}
