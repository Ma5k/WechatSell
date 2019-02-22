package com.mask.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mask.VO.ResultVO;
import com.mask.config.ProjectUrlConfig;
import com.mask.exception.SellException;
import com.mask.exception.SellerAuthorizeException;
import com.mask.utils.ResultVOUtil;

@ControllerAdvice
public class SellExceptionHandler {
	
	@Autowired
	private ProjectUrlConfig projectUrlConfig;
	
	//拦截登录异常
	@ExceptionHandler(value = SellerAuthorizeException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ModelAndView handlerAuthorizeException() {
		return new ModelAndView("redirect:"
		        .concat(projectUrlConfig.getWechatOpenAuthorize())
		        .concat("/sell/wechat/qrAuthorize")
		        .concat("?returnUrl=")
		        .concat(projectUrlConfig.getSell())
		        .concat("/sell/seller/login"));
	}
	
	@ExceptionHandler(value = SellException.class)
	@ResponseBody
	public ResultVO handlerSellerException(SellException e) {
		return ResultVOUtil.error(e.getCode(), e.getMessage());
	}
}
