package com.mask.aspect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mask.constant.CookieConstant;
import com.mask.constant.RedisConstant;
import com.mask.exception.SellerAuthorizeException;
import com.mask.service.impl.OrderServiceImpl;
import com.mask.utils.CookieUtil;

/**
 * 用于对请求进行过滤，未登录时请求会被抛出异常，异常中定义了跳转的页面
 * @author Mask
 *
 */
@Aspect
@Component
public class SellerAuthorizeAspect  {
	
	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
    private StringRedisTemplate redisTemplate;
	/**
	 * 切入点为controller下的Seller开头的所有controller下的方法
	 * 并排除掉SellerUserController
	 */
	@Pointcut("execution(public * com.mask.controller.Seller*.*(..))" + 
	"&& !execution(public * com.mask.controller.SellerUserController.*(..))")
	public void verify() {}
	
	@Before("verify()")
	public void doVerify() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		//查询cookie
		Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
		if (cookie == null) {
			log.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
		}
		
		//去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
            throw new SellerAuthorizeException();
        }
	}
}
