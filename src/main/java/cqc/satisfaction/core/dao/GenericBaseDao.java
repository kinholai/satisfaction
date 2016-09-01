package cqc.satisfaction.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

/**
 * Dao层核心基础接口
 * @author Kinho
 *
 */
public interface GenericBaseDao
{
	public Session getSession();
	
	public <T> Serializable save(T entity);

	public <T> void saveOrUpdate(T entity);
	
	public <T> void delete(T entitie);
	
	public <T> T get(Class<T> entityName, Serializable id);
	
	public <T> T getEntity(Class entityName, Serializable id);
	
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value);
	
	public <T> void deleteEntityById(Class entityName, Serializable id);
	
	public <T> void deleteAllEntitie(Collection<T> entities);
	
	public <T> void updateEntitie(T pojo);

	public <T> void updateEntityById(String className, Object id);
	
	public <T> List<T> findHql(String hql, Object... param);
	
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc);
	
	public Integer executeHql(String hql);

	public <T> List<T> loadAll(final Class<T> entityClass);
	
	public <T> List<T> limit(String hql, int first, int max);
}
