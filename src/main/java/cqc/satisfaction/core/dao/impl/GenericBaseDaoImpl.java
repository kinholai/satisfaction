package cqc.satisfaction.core.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import cqc.satisfaction.core.dao.GenericBaseDao;

/**
 * DAO层核心基础接口实现
 * @author Kinho
 *
 */
@SuppressWarnings("hiding")
public abstract class GenericBaseDaoImpl<T, PK extends Serializable> implements GenericBaseDao
{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	public <T> Serializable save(T entity)
	{
		try 
		{
			Serializable id = getSession().save(entity);
			getSession().flush();
			return id;
		} 
		catch (RuntimeException e) 
		{
			throw e;
		}
	}

	
	public <T> void saveOrUpdate(T entity)
	{
		try 
		{
			getSession().saveOrUpdate(entity);
			getSession().flush();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		}
	}

	
	public <T> void delete(T entitie)
	{
		try 
		{
			getSession().delete(entitie);
			getSession().flush();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		}
	}

	
	public <T> T get(Class<T> entityName, Serializable id)
	{
		return (T) getSession().get(entityName, id);
	}

	
	public <T> T getEntity(Class entityName, Serializable id)
	{
		T t = (T) getSession().get(entityName, id);
		if (t != null) 
			getSession().flush();
		return t;
	}

	private <T> Criteria createCriteria(Class<T> entityClass, boolean isAsc,
			Criterion... criterions) 
	{
		Criteria criteria = createCriteria(entityClass, criterions);
		if (isAsc) 
			criteria.addOrder(Order.asc("asc"));
		else 
			criteria.addOrder(Order.desc("desc"));
		return criteria;
	}
	
	private <T> Criteria createCriteria(Class<T> entityClass,
			Criterion... criterions) 
	{
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) 
			criteria.add(c);
		return criteria;
	}
	
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value)
	{
		Assert.hasText(propertyName);
		return (List<T>) createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
	}

	
	public <T> void deleteEntityById(Class entityName, Serializable id)
	{
		delete(get(entityName, id));
		getSession().flush();
	}

	
	public <T> void deleteAllEntitie(Collection<T> entities)
	{
		for (Object entity : entities) 
		{
			getSession().delete(entity);
			getSession().flush();
		}
	}

	public <T> List<T> loadAll(final Class<T> entityClass) 
	{
		Criteria criteria = createCriteria(entityClass);
		return criteria.list();
	}
	
	public <T> void updateEntitie(T pojo)
	{
		getSession().update(pojo);
		getSession().flush();
	}

	
	public <T> void updateEntityById(String className, Object id)
	{
		getSession().update(className, id);
		getSession().flush();
	}

	
	public <T> List<T> findHql(String hql, Object... param)
	{
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) 
			for (int i = 0; i < param.length; i++) 
				q.setParameter(i, param[i]);
		return q.list();
	}

	
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc)
	{
		Assert.hasText(propertyName);
		return createCriteria(entityClass, isAsc,
				Restrictions.eq(propertyName, value)).list();
	}

	public Integer executeHql(String hql)
	{
		Query q = getSession().createQuery(hql);
		return q.executeUpdate();
	}

	public <T> List<T> limit(String hql, int first, int max)
	{
		Query q = getSession().createQuery(hql);
			q.setFirstResult(first);
			q.setMaxResults(max);
		return q.list();
	}
}
