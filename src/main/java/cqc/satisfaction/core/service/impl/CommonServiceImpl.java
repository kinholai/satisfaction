package cqc.satisfaction.core.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cqc.satisfaction.core.dao.GenericBaseDao;
import cqc.satisfaction.core.service.CommonService;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService
{
	public GenericBaseDao commonDao = null;
	
	@Resource
	public void setCommonDao(GenericBaseDao commonDao)
	{
		this.commonDao = commonDao;
	}
	
	public Session getSession()
	{
		return commonDao.getSession();
	}

	
	public <T> Serializable save(T entity)
	{
		return commonDao.save(entity);
	}

	
	public <T> void saveOrUpdate(T entity)
	{
		commonDao.saveOrUpdate(entity);
	}

	
	public <T> void delete(T entity)
	{
		commonDao.delete(entity);
	}

	
	public <T> T get(Class<T> class1, Serializable id)
	{
		return commonDao.get(class1, id);
	}

	
	public <T> T getEntity(Class entityName, Serializable id)
	{
		return commonDao.getEntity(entityName, id);
	}

	
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value)
	{
		return commonDao.findByProperty(entityClass, propertyName, value);
	}

	
	public <T> void deleteEntityById(Class entityName, Serializable id)
	{
		commonDao.deleteEntityById(entityName, id);
	}

	
	public <T> void deleteAllEntitie(Collection<T> entities)
	{
		commonDao.deleteAllEntitie(entities);
	}

	
	public <T> void updateEntitie(T pojo)
	{
		commonDao.updateEntitie(pojo);
	}

	
	public <T> List<T> getList(Class clas)
	{
		return commonDao.loadAll(clas);
	}

	
	public <T> List<T> findHql(String hql, Object... param)
	{
		return this.commonDao.findHql(hql, param);
	}

	public <T> List<T> limit(String hql, int first, int max)
	{
		return this.commonDao.limit(hql, first, max);
	}
}
