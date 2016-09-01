package cqc.satisfaction.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cqc.satisfaction.common.model.SatisfactionImage;
import cqc.satisfaction.common.service.FirstService;

@Controller
@RequestMapping("/home")
public class HomeController
{
	private final int COUNT_PER_PAGE = 3;
	
	@Autowired
	private FirstService firstService;
	
	@RequestMapping
	public String home(HttpServletRequest request)
	{
		return "home";
	}
	
	@RequestMapping(value = "/getImg")
	@ResponseBody
	public Map<String, Object> getImg(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		List<SatisfactionImage> image = firstService.limit("from SatisfactionImage order by prority desc, createTime desc", 0, COUNT_PER_PAGE);
		map.put("image", image);
		return map;
	}
}
