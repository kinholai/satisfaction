package cqc.satisfaction.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 上下文路径 
 * @author Kinho
 *
 */
public class ApplicationContextUtil implements ApplicationContextAware 
{

	private static ApplicationContext context;

	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException 
	{
		this.context = context;
	}

	public static ApplicationContext getContext() 
	{
		return context;
	}
}
