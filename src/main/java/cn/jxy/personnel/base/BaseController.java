package cn.jxy.personnel.base;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.jxy.personnel.service.IUserService;


public class BaseController {
	
	protected Logger logger = null;
	
	public BaseController(){
		super();
		this.logger = Logger.getLogger(this.getClass());
		this.logger.info(this.getClass()+" is instance");
	}
	
	@Resource
	protected IUserService userService;
	
	
}
