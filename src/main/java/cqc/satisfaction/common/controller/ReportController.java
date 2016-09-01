package cqc.satisfaction.common.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.servlet.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cqc.satisfaction.common.model.SatisfactionReport;
import cqc.satisfaction.common.service.FirstService;
import cqc.satisfaction.common.util.StringUtil;

@Controller
@RequestMapping("/reports")
public class ReportController
{
	private final int COUNT_PER_PAGE = 5;
	
	@Autowired
	private FirstService firstService;
	
	@RequestMapping
	public String reports(HttpServletRequest request)
	{
		String page = request.getParameter("page");
		if(StringUtil.isEmpty(page))
		{
			request.setAttribute("message", "请求参数非法：空串。");
			return "errorPage";
		}
		else if(!StringUtil.isPositiveNumberic(page))
		{
			request.setAttribute("message", "请求参数非法：非数字。");
			return "errorPage";
		}
		else
		{
			int curPage = Integer.valueOf(page);
			List<SatisfactionReport> reportList = firstService.findHql("from SatisfactionReport order by prority desc, createTime desc");
			final int size = reportList.size();
			if(size < (curPage-1)*COUNT_PER_PAGE+1)
			{
				request.setAttribute("message", "当前页暂无数据内容。");
				return "errorPage";
			}
			int limit = 0;
			if(size < curPage*COUNT_PER_PAGE)
				limit = size - 1;
			else
				limit = curPage*COUNT_PER_PAGE - 1;
			List<SatisfactionReport> reports = new ArrayList<SatisfactionReport>();
			for(int i = (curPage-1)*COUNT_PER_PAGE; i <= limit; i++)
				reports.add(reportList.get(i));
			request.setAttribute("reports", reports);
			request.setAttribute("page", page);
			request.setAttribute("size", size);
			request.setAttribute("cpp", COUNT_PER_PAGE);
		}
		return "report/reports";
	}
	
	@RequestMapping(value = "/{id}")
	public String show(@PathVariable String id, HttpServletRequest request)
	{
		if(StringUtil.isNotEmpty(id))
		{
			SatisfactionReport report = firstService.getEntity(SatisfactionReport.class, id);
			request.setAttribute("report", report);
			
			if(StringUtil.isNotEmpty(report.getAttachment()))
			{
				String attachment = report.getAttachment();
				attachment = URLDecoder.decode(attachment);
				attachment = URLDecoder.decode(attachment);
				String ctxname = attachment.substring(0, attachment.lastIndexOf("/upload"));
	//			String filename = attachment.substring(attachment.lastIndexOf("/"), attachment.length());
				System.out.println("...................." + ctxname);
	//			System.out.println("...................." + filename);
				request.setAttribute("ctxname", ctxname);
			}
			return "report/show";
		}
		else
		{
			request.setAttribute("message", "请求参数错误：空串。");
			return "errorPage";
		}
	}
	
	@RequestMapping(value = "/{id}/download")
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response)
	{
		if(StringUtil.isNotEmpty(id))
		{
			SatisfactionReport report = firstService.getEntity(SatisfactionReport.class, id);
			request.setAttribute("report", report);
//			String filename = report.getTitle();
			String attachment = report.getAttachment();
			System.out.println(".............." + attachment);
			attachment = URLDecoder.decode(attachment);
			attachment = URLDecoder.decode(attachment);
			System.out.println(".............." + attachment);
			
			firstService.downloadFile(request, response, attachment);
		}
	}
}
