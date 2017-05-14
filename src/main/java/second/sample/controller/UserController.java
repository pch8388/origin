package second.sample.controller;

import javax.annotation.Resource;

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
	
	@RequestMapping(value="/loginCheck",method=RequestMethod.POST)
	public ModelAndView loginCheck(@RequestParam("id")String id,@RequestParam("pw")String pw)throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		LoginDTO dto = new LoginDTO();

		dto.setId(id);
		dto.setPw(pw);
		UserVO vo = service.login(dto);
		if(vo == null){
			mav.addObject("pw", false);
		}else{
			mav.addObject("pw", true);
		}
		return mav;
	}
	
	@RequestMapping(value="/loginPost",method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto,Model model) throws Exception{
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
