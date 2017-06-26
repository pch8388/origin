package second.sample.controller;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService service;
	
	@RequestMapping(value="/login")
	public String loginGET(HttpServletRequest request,HttpServletResponse response)throws IOException, NoSuchAlgorithmException, InvalidKeySpecException{
		HttpSession session = request.getSession();
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		KeyPair keyPair = generator.generateKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		
		session.setAttribute("_RSA_WEB_Key_", privateKey);   //세션에 RSA개인키 저장
		
		RSAPublicKeySpec publicSpec = (RSAPublicKeySpec)keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		String publicKeyModulus = publicSpec.getModulus().toString(16);
		String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
		
		request.setAttribute("RSAModulus", publicKeyModulus);
		request.setAttribute("RSAExponent", publicKeyExponent);
		
		return "/user/login";
	}
	
	@RequestMapping(value="/loginCheck")
	public ModelAndView loginCheck(HttpServletRequest request,@RequestParam("id")String id,@RequestParam("pw")String pw)throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		HttpSession session = request.getSession();
		
		PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");  //세션에 저장된 개인키 가져옴
		log.info("id: " +id);
		log.info("pw: " +pw);
		if(privateKey == null){
			mav.addObject("pw", false);
		}else{
			try{
				//복호화 처리
				String uid = service.decryptRsa(privateKey, id);
				String pwd = service.decryptRsa(privateKey, pw);
				LoginDTO dto = new LoginDTO();
				dto.setId(uid);
				dto.setPw(pwd);
				UserVO vo = service.login(dto);
				if(vo == null){
					mav.addObject("pw", false);
				}else{
					mav.addObject("pw", true);
				}
			}catch(Exception e){
				mav.addObject("pw", false);
			}
		}

		return mav;
	}
	
	@RequestMapping(value="/loginPost")
	public void loginPOST(@RequestParam("id")String id,Model model) throws Exception{
		UserVO vo = service.idCheck(id);
		if(vo.getId() == null){
			model.addAttribute("userVO", null);
		}else{
			model.addAttribute("userVO", vo);
		}
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
