package cqc.satisfaction.common.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cqc.satisfaction.common.model.AnswerPage;
import cqc.satisfaction.common.model.Constant;
import cqc.satisfaction.common.model.SatisfactionSurvey;
import cqc.satisfaction.common.model.SurveyAnswer;
import cqc.satisfaction.common.model.SurveyInterviewee;
import cqc.satisfaction.common.service.FirstService;
import cqc.satisfaction.common.service.SecondService;
import cqc.satisfaction.common.util.IpUtil;
import cqc.satisfaction.common.util.RandomUtil;
import cqc.satisfaction.common.util.StringUtil;
import cqc.satisfaction.common.model.SurveyHead;
import cqc.satisfaction.common.model.SurveyQuestion;

@Controller
@RequestMapping("/surveys")
public class SurveyController
{
	private final int COUNT_PER_PAGE = 5;
	
	@Autowired
	private FirstService firstService;
	
	@Autowired
	private SecondService secondService;
	
	@RequestMapping
	public String surveys(HttpServletRequest request)
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
			List<SatisfactionSurvey> surveyList = firstService.findHql("from SatisfactionSurvey where state=? order by prority desc, createTime desc", (short)1);
			final int size = surveyList.size();
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
			List<SatisfactionSurvey> surveys = new ArrayList<SatisfactionSurvey>();
			for(int i = (curPage-1)*COUNT_PER_PAGE; i <= limit; i++)
				surveys.add(surveyList.get(i));
			request.setAttribute("surveys", surveys);
			request.setAttribute("page", page);
			request.setAttribute("size", size);
			request.setAttribute("cpp", COUNT_PER_PAGE);
		}
		return "survey/surveys";
	}
	
	@RequestMapping(value = "/{id}")
	public String show(@PathVariable String id, HttpServletRequest request)
	{
		if(StringUtil.isNotEmpty(id))
		{
			SurveyHead sh = firstService.getEntity(SurveyHead.class, id);
			List<SurveyQuestion> sqList = 
					firstService.findHql("from SurveyQuestion where headId=? order by orderNo asc", id);
			request.setAttribute("sHead", sh);
			request.setAttribute("sqList", sqList);
			request.setAttribute("num", sqList.size());
			return "survey/show";
		}
		else
		{
			request.setAttribute("message", "请求参数错误：空串。");
			return "errorPage";
		}
	}
	
	@RequestMapping(value = "/saveAnswers")
	public ModelAndView saveAnswers(AnswerPage aPage, HttpServletRequest request) throws ParseException
	{
		String headId = request.getParameter("sHeadId");
		String num = request.getParameter("num");
		String ip = IpUtil.getIpAddr(request);
		List<SurveyAnswer> answerList = aPage.getAnswerList();
		if(answerList != null)
		{
			if(answerList.size() < Integer.parseInt(num))
				return new ModelAndView("errorPage").addObject("message", "提交失败：请后退并完成所有题目再提交。");
			boolean wrong = false;
			for(SurveyAnswer a : answerList)
			{
				if(a.getChoiceId() == null || a.getChoiceId() == "")
				{
					wrong = true;
					break;
				}
			}
			if(wrong)
				return new ModelAndView("errorPage").addObject("message", "提交失败：请后退并完成所有题目再提交。");
		}
		else
			return new ModelAndView("errorPage").addObject("message", "提交失败：请后退并完成所有题目再提交。");
		
		SurveyHead sh = firstService.getEntity(SurveyHead.class, headId);
		int limitNum = sh.getLimitNum();
		List<SurveyInterviewee> siList = 
				firstService.findHql("from SurveyInterviewee where headId=? and ip=?", headId, ip);
		if(sh.getLimitState() == Constant.SINGLE_IP_STATE_VALUE) //同一IP限提交limitNum次
		{
			if(siList.size() >= limitNum)
				return new ModelAndView("errorPage").addObject("message", "抱歉，您已经提交过了，不允许重复提交。");
		}
		else if(sh.getLimitState() == Constant.WHITELIST_UNLIMITED_STATE_VALUE) //仅白名单IP不限提交次数
		{
			if(siList.size() >= limitNum && 
					firstService.findHql("from SurveyAdmit where headId=? and ip=?", headId, ip).size() <= 0)
				return new ModelAndView("errorPage").addObject("message", "抱歉，您已经提交过了，不允许重复提交。");
		}
		else if(sh.getLimitState() == Constant.BLACKLIST_LIMITED_STATE_VALUE) //黑名单IP限提交limitNum次，非黑名单IP不限提交次数
		{
			if(siList.size() >= limitNum && 
					firstService.findHql("from SurveyReject where headId=? and ip=?", headId, ip).size() > 0)
				return new ModelAndView("errorPage").addObject("message", "抱歉，您已经提交过了，不允许重复提交。");
		}
		else if(sh.getLimitState() == Constant.MIXED_STATE_VALUE) //仅白名单IP不限提交次数，且黑名单IP限提交limitNum次
		{
			if(siList.size() >= limitNum)
			{
				if(firstService.findHql("from SurveyAdmit where headId=? and ip=?", headId, ip).size() <= 0)
					return new ModelAndView("errorPage").addObject("message", "抱歉，您已经提交过了，不允许重复提交。");
				if(firstService.findHql("from SurveyReject where headId=? and ip=?", headId, ip).size() > 0)
					return new ModelAndView("errorPage").addObject("message", "抱歉，您已经提交过了，不允许重复提交。");
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SurveyInterviewee interviewee = new SurveyInterviewee();
			interviewee.setHeadId(headId);
			interviewee.setIp(ip);
			interviewee.setSubmitTime(sdf.parse(sdf.format(new Date())));
			interviewee.setId(UUID.randomUUID().toString().replaceAll("\\-", ""));
		
		try{
			for(SurveyAnswer answer : answerList)
			{
				answer.setIntervieweeId(interviewee.getId());
				secondService.save(answer);
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ModelAndView("errorPage").addObject("message", "问卷提交失败：系统内部错误。");
		}
		
		String randomCode = RandomUtil.getCharAndNumr(8);
		while(true)
		{
			if(firstService.findHql("from SurveyInterviewee where code=? and headId=?", randomCode, headId).size() > 0)
				randomCode = RandomUtil.getCharAndNumr(8);
			else
				break;
		}
		interviewee.setCode(randomCode);
		secondService.save(interviewee);
		return new ModelAndView("errorPage").addObject("message", "问卷提交成功：感谢您的参与，祝您生活愉快！" + "本次问卷调查的随机码是" + randomCode + "。");
	}
}
