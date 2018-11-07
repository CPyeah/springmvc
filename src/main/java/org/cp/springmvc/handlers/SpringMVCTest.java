package org.cp.springmvc.handlers;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Map;

import org.cp.springmvc.entities.Address;
import org.cp.springmvc.entities.User;
import org.cp.springmvc.exceptions.MyNewException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("test")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRequestMethod",method=RequestMethod.POST)
	public String testRequestMethod() {
		System.out.println("testRequestMethod");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testParams",params= {"id","age!=10"})
	public String testParams() {
		System.out.println("testParams");
		return SUCCESS;
	}
	
	@RequestMapping(value="/test/*/Path")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable --> ID is :" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRequestParams")
	public String testRequestParams(@RequestParam(value="name") String name,
			@RequestParam(value="age",required=false,defaultValue="18") Integer age) {
		System.out.println("testRequestParams --> name is :" + name +
				" age is : " + age);
		return SUCCESS;
	}
	
	//Access-Control-Allow-Origin
	@RequestMapping(value="/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept") String accept) {
		System.out.println("testRequestHeader -->Accept is :" + accept);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testCookieValue")
	public String testCookieValue(@CookieValue(value="JSESSIONID", required=false) String sessionId) {
		System.out.println("testCookieValue -->sessionId is :" + sessionId);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testPOJO")
	public String testPOJO(User user) {
		System.out.println("testPOJO -->user is :" + user);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testServletAPI")
	public void testServletAPI(Writer out) {
		System.out.println("testServletAPI -->Writer is :" + out);
		try {
			out.write("hello writer spirng");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return SUCCESS;
	}
	
	@RequestMapping(value="/testModelAndView")
	public ModelAndView testModelAndView() {
		ModelAndView mv = new ModelAndView(SUCCESS);
		mv.addObject("time", new Date());
		return mv ;
	}
	
	@RequestMapping(value="/testModelAttribute")
	public String testModelAttribute(@ModelAttribute("pUser")User pUser) {
		System.out.println("testModelAttribute -->newUser is :" + pUser);
		return SUCCESS;
	}
	
	@ModelAttribute
	public void getUser(@RequestParam(value="userName",required=false) String userName,Map map) {
		if(userName!=null) {
			//获取原数据
			Address address = new Address();
			address.setProvince("AnHui");
			address.setCity("AnQing");
			User pUser = new User();
			pUser.setUserName("CP");
			pUser.setPassword("123456");
			pUser.setEmail("cp@qq.com");
			pUser.setAddress(address);
			System.out.println("原数据： " + pUser);
			map.put("pUser", pUser);
		}
	}
	
	@RequestMapping(value="/testRedirect")
	public String testRedirect() {
		System.out.println("testRedirect");
		return "redirect:/index.html";
	}
	
	@ResponseBody
	@RequestMapping(value="/testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body) {
		System.out.println(body);
		return "testHttpMessageConverter :" + new Date();
	}
	
	@RequestMapping(value="/testExceptionResolver")
	public String testExceptionResolver(@RequestParam("i") Integer i) throws MyNewException {
		System.out.println(10/i);
		if(i==2) {
			throw new RuntimeException("i不能为2");
		}
		if(i==13) {
			throw new MyNewException();
		}
		return SUCCESS;
	}
	
	@RequestMapping(value="/testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(@RequestParam("i") Integer i) throws MyNewException {
		String[] array = new String[10];
		System.out.println(array[i]);
		return SUCCESS;
	}
	
	@ExceptionHandler({ArithmeticException.class})
	public String arithmeticExceptionHandle(Exception ex) {
		System.out.println(ex.getMessage());
		return "error";
	}

}
