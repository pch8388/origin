package second.common.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e){
		ModelAndView mav = new ModelAndView("/common/error_common");
		mav.addObject("exception", e);
		return mav;
	}
}
