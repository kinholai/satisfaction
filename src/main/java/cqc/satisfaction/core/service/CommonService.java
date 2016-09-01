package cqc.satisfaction.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

public interface CommonService
{
	public Session getSession();
	
	public <T> Serializable save(T entity);

	public <T> void saveOrUpdate(T entity);

	public <T> void delete(T entity);
	
	public <T> T get(Class<T> class1, Serializable id);
	
	public <T> T getEntity(Class entityName, Serializable id);
	
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value);
	
	public <T> void deleteEntityById(Class entityName, Serializable id);
	
	public <T> void deleteAllEntitie(Collection<T> entities);
	
	public <T> void updateEntitie(T pojo);
	
	public <T> List<T> getList(Class clas);
	
	public <T> List<T> findHql(String hql, Object... param);
	
	public <T> List<T> limit(String hql, int first, int max);
}
