package second.sample.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import second.sample.dto.LoginDTO;
import second.sample.service.UserService;
import second.sample.user.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource(name="userService")
	private UserService service;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto){
		
	}
	
	@RequestMapping(value="/loginPost",method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto,HttpSession session,Model model) throws Exception{
		UserVO vo = service.login(dto);
		
		if(vo == null){
			return;
		}
		
		model.addAttribute("userVO", vo);
	}
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public void joinGET(@ModelAttribute("vo") UserVO vo){
		
	}
	
	@RequestMapping(value="/joinPost",method=RequestMethod.POST)
	public String joinPOST(UserVO vo,Model model) throws Exception{
		service.join(vo);
		
		return "redirect:/user/login";
	}
	
	@RequestMapping(value="/idCheck",method=RequestMethod.GET)
	public ModelAndView idCheck(@RequestParam("id") String id) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		UserVO vo = service.idCheck(id);
		if(vo.getId() == null){
			mav.addObject("id", false);
		}else{
			mav.addObject("id", true);
		}
		return mav;
	}
}
