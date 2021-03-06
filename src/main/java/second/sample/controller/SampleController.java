package second.sample.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import second.common.common.CommandMap;
import second.sample.service.SampleService;

@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	@RequestMapping("/sample/index2")
	public void index2()throws Exception{
		
	}
	
	@RequestMapping(value="/sample/openBoardList")
	public ModelAndView openBoardList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardList");
		
		return mv;
	}
	
	@RequestMapping(value="/sample/selectBoardList")
	public ModelAndView selectBoardList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		
		List<Map<String,Object>> list = sampleService.selectBoardList(commandMap.getMap());
		mv.addObject("list",list);
		if(list.size() > 0){
			mv.addObject("TOTAL",list.get(0).get("TOTAL_COUNT"));
		}
		else{
			mv.addObject("TOTAL",0);
		}
		return mv;
	}
	
	@RequestMapping(value="/sample/boardReply")
	public ModelAndView boardReply(CommandMap commandMap) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mav.addObject("map",map.get("map"));
		mav.setViewName("/sample/boardReply");
		
		return mav;
	}
	
	@RequestMapping(value="/sample/testMapArgumentResolver")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("");
		
		if(commandMap.isEmpty() == false){
			Iterator<Entry<String,Object>> iterator = commandMap.getMap().entrySet().iterator();
			Entry<String,Object> entry = null;
			while(iterator.hasNext()){
				entry = iterator.next();
				log.debug("key : "+entry.getKey()+", value : "+entry.getValue());
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardWrite")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardWrite");
		
		return mv;
	}
	
	@RequestMapping(value="/sample/insertBoard")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList");
		
		String contents = ((String)commandMap.get("CONTENTS")).replace("\r\n","<br>");
		commandMap.put("CONTENTS", contents);
		
		sampleService.insertBoard(commandMap.getMap(),request);
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardDetail")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardDetail");
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardUpdate")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardUpdate");
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map",map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/sample/updateBoard")
	public ModelAndView updateBoard(CommandMap commandMap,HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail");
		
		sampleService.updateBoard(commandMap.getMap(),request);
		
		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}
	
	@RequestMapping(value="/sample/deleteBoard")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList");
		
		sampleService.deleteBoard(commandMap.getMap());
		
		return mv;
	}
	
	@RequestMapping(value="/sample/replyBoard")
	public ModelAndView replyBoard(CommandMap commandMap,HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/sample/openBoardList");
		
		String contents = ((String)commandMap.get("CONTENTS")).replace("\r\n","<br>");
		commandMap.put("CONTENTS", contents);
		
		sampleService.replyBoard(commandMap.getMap(),request);
		
		return mav;
	}
}
