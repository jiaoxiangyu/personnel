package cn.jxy.personnel.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/*
* @author 焦
* @time 2017年2月28日 下午10:07:24
* 类说明,为与其子类区分，此类中所有非private方法（除getSession）全部带有entity,并不允许重写。
*/
public class BaseDao<T> {
	@Resource
	private SessionFactory sessionFactory;
	
	protected Class<T> clazz;
	
	protected Logger logger = null;
	
	@SuppressWarnings("unchecked")
	public BaseDao(){
		this.logger = Logger.getLogger(this.getClass());
		this.logger.info(this.getClass()+" is instance");
		Type type=this.getClass().getGenericSuperclass();
		if(!(type instanceof ParameterizedType)){
			type=type.getClass().getSuperclass().getGenericSuperclass();
		}
		clazz=(Class<T>)((ParameterizedType) type).getActualTypeArguments()[0];
	}
	
	protected Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	protected final boolean saveEntity(T t){
		this.getSession().save(t);
		return true;
	}
	protected final boolean updateEntity(T t){
		
		this.getSession().saveOrUpdate(t);
		return true;
	}
	protected final boolean deleteEntity(T t){
		this.getSession().delete(t);
		return true;
	}
	protected final boolean deleteEntity(int id){
		this.deleteEntity(this.getEntity(id));
		return true;
	}
	
	@SuppressWarnings("unchecked")
	protected final T getEntity(int id){
		return (T)this.getSession().get(clazz, id);
	}
	
	protected final int updateEntity(String hql,Object ...objects){
		Query query = this.getSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return query.executeUpdate();
	}
	protected final List<T> getEntityList(String hql,Object ...objects){
		Query query=this.getSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		@SuppressWarnings("unchecked")
		List<T> list=query.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	protected final List<T> getEntityLimitList(String hql,int first,int max,Object ...objects){
		Query query=this.getSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	protected final Object uniqueResult(String hql, Object... objects) {
		Query query = getSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	protected final List<T> executeSQLQuery(String sql, Object ...objects){
		SQLQuery sQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if(clazz!=null){//这样可以直接的将投影查询之后可以将数组包装成实体类型的数据格式
			sQuery.addEntity(clazz);
		}
		for(int i=0;i<objects.length;i++){
			sQuery.setParameter(i, objects[i]);
		}
		return sQuery.list();
	}
	protected final void executeSQL(String sql, Object ...objects){
		SQLQuery sQuery = getSession().createSQLQuery(sql);
		for(int i=0;i<objects.length;i++){
			sQuery.setParameter(i, objects[i]);
		}
		sQuery.executeUpdate();
	}

	protected final void executeHql(String hql,Object ...objects){
		Query query=this.getSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}
	
	/**
	 * @param hql,可为null或者"",此时会返回总数量
	 * @param objects
	 * @return
	 * 注意，此方法可能出现问题，若有问题，
	 */
	protected final int getEntityNum(String hql) {	
		return ((Long) this.uniqueResult("select count(*) "+hql)).intValue();
	}
	
	

}
