package cn.jxy.personnel.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.jxy.personnel.base.BaseController;
import cn.jxy.personnel.entity.User;
import cn.jxy.personnel.entity.ViewUser;
import cn.jxy.personnel.util.ExcelUtil;
import cn.jxy.personnel.util.PageCut;

/**
 * 
 * @author Administrator
 *人员管理控制类
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseController{
	private ModelAndView mv;
	
	//进入登录页面
	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}
	
	//注销
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
		
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(String username,String password,HttpSession session) {
		/*username="12345678913";
		password="123";*/
		logger.info(username+"#"+password);
		
		mv=new ModelAndView();
		User user=userService.findOne("login",username,username,password);
		if(user!=null) {
			System.out.println(user);
			session.setAttribute("user", user);
			mv.setViewName("index");
			return mv;
		}else {
			mv.addObject("msg", "密码或用户名错误！");
			mv.setViewName("login");
			return mv;
		}
	}
	
	//进入首页
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(String username,String password,HttpSession session) {
		mv=new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	/**
	 * 统计分析
	 * @return 
	 */
	@RequestMapping(value = "getJson", method = RequestMethod.POST)
	public @ResponseBody List<ViewUser> getJson(String name){
		System.out.println("######name="+name);
		List<ViewUser> list=userService.count(name);
		for(ViewUser v:list)
			System.out.println(v);		
		return list;	
	}
	
	//获得人员数据
	@RequestMapping(value = "getData", method = RequestMethod.GET)
	public ModelAndView getData(
			@RequestParam(defaultValue = "0" , name = "curr") int curr,	
			@RequestParam(defaultValue = "8" , name = "size") int size,
			@RequestParam(defaultValue = "" , name = "search") String search,			
			HttpSession session
			) throws Exception {
		mv=new ModelAndView();
		search=new String (search.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("search="+search);
		String msg=(String)session.getAttribute("msg");
		System.out.println("msg="+msg);
		if( !(msg==null  || msg.equals(""))) {
			session.removeAttribute("msg");
			mv.addObject("msg", msg);
		}
		PageCut<User> pc=null;
		if(search.equals("") || search==null) {
			pc=userService.finds(curr, size, "all");
		}else {
			pc=userService.finds(curr, size, "search",search);
			mv.addObject("search",search);
		}
		for(User u:pc.getData())
			System.out.println(u);
		
		mv.addObject("pc", pc);
		mv.setViewName("userMge");
		return mv;
	}
	
	//进入添加用户
	@RequestMapping(value = "toAdd", method = RequestMethod.GET)
	public ModelAndView toAdd(){
		mv=new ModelAndView();
		mv.setViewName("add");
		return mv;
	}
	
	//添加用户
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ModelAndView add(User user){
		mv=new ModelAndView();
		User u=userService.findOne("only", user.getPhone(),user.getEmail());
		if(u!=null) {
			mv.addObject("msg","注册失败,手机号或邮箱已被注册！");
		}else {
			boolean bool=userService.add(user);
			if(bool==true) {
				mv.addObject("msg","注册成功！");
			}else {
				mv.addObject("msg","注册失败！");
			}
		}		
		mv.setViewName("add");
		return mv;
	}	
	
	//获得个人信息
	@RequestMapping(value = "toUpdate", method = RequestMethod.GET)
	public ModelAndView toUpdate(int id){
		mv=new ModelAndView();
		User user=userService.findOne("id", id);
		mv.addObject("userInfo", user);
		mv.setViewName("info");
		return mv;
	}
	
	
	//修改个人信息
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(User user){
		System.out.println(user);
		mv=new ModelAndView();
		User oldUser=userService.findOne("id", user.getId());
		user.setType(oldUser.getType());
		boolean bool=userService.update(user);
		user=userService.findOne("id", user.getId());
		if(bool==true) {
			mv.addObject("userInfo",user);
			mv.addObject("msg","修改成功！");
		}else {
			mv.addObject("userInfo",user);
			mv.addObject("msg","修改失败！");
		}
		mv.setViewName("info");
		return mv;
	}
	
	//删除用户
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(int id,HttpSession session){
		/*mv=new ModelAndView();*/
		boolean bool=userService.delete(id);
		String msg="";
		
		if(bool==true) {
			msg="删除成功！";
		}else {
			msg="删除失败！";
		}
		session.setAttribute("msg", msg);		
		return "redirect:getData"; 
	}
	
	//导入人员信息excel表
	@RequestMapping(value = "inputExcel", method = RequestMethod.POST)
	public ModelAndView inputExcel(@RequestParam("file") MultipartFile file,
			HttpSession session) throws Exception{
		mv=new ModelAndView();
		String fileName=file.getOriginalFilename();
		System.out.println(fileName);
		InputStream is=file.getInputStream();
		List<User> users=ExcelUtil.readExcel(fileName, is);		
		for(User u:users)
			userService.add(u);
		session.setAttribute("msg", "导入成功");
		mv.setViewName("redirect:getData");
		return mv;
	}
	
	//导出人员信息excel表
	@RequestMapping(value = "outputExcel", method = RequestMethod.POST)
	public ModelAndView outputExcel(String condition,String content,
			HttpSession session){
		mv=new ModelAndView();
		List<User> users=userService.finds(0, 0, "excel", condition,content).getData();
		try {
			ExcelUtil.creat2003Excel(users,condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("msg", "导出成功！");				
		mv.setViewName("redirect:getData");
		return mv;
	}
			
			
}
