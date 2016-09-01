package cqc.satisfaction.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cqc.satisfaction.core.service.CommonService;

public interface FirstService extends CommonService
{
	public HttpServletResponse downloadFile(HttpServletRequest request, 
			HttpServletResponse response, String fileRealname);
}
