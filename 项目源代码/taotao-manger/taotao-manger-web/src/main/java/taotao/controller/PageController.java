package taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 * 
 * @author 彭秉浪
 */
@Controller
public class PageController {

	/**
	 * 打开首页
	 * 
	 * @return
	 * @author 彭秉浪
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	/**
	 * 展示其他页面
	 * 
	 * @return
	 * @author 彭秉浪
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
