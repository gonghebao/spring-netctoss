package cn.tedu.netctoss.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.netctoss.entity.Cost;
import cn.tedu.netctoss.service.CostService;
@Controller
public class CostController {
	@Resource(name="costService")
	private CostService cs;
	@RequestMapping("findcost.do")
	public String toCost(HttpServletRequest request){
		List<Cost> list = null;
//		try {
		list = cs.checkData();
//			
//		} catch (Exception e) {
//			if(e instanceof AppException){
//				request.setAttribute("costMessage",e.getMessage());
//			}else{
//				return "error";
//			}
//		}
		request.setAttribute("list",list);
		System.out.println("list:"+list);
		return "cost/fee_list";
	}
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		
		return "index";
	}
}
