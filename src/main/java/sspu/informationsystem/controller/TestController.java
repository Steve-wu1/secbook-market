package sspu.informationsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class TestController {
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello spring boot!~";
    }

    /**
     * 系统登录页面
     *
     * @return 若成功返回相应页面，若失败则返回登陆页面
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/loginFailure")
    public String loginFailure(Model model) {
        model.addAttribute("loginFail", "用户名或密码错误");
        return "/login";
    }

    /**
     * 测试用链接
     *
     * @return 前往商店列表页面
     */
    @GetMapping("/toStoreList")
    public String toStoreList() { return "/stores"; }

    @GetMapping("/toUserInfo")
    public String ToUserInfo() { return "/userInfo"; }

    @GetMapping("/toMyOrders")
    public String ToMyOrders() { return "/myOrders"; }


    @GetMapping("/toRegister")
    public String ToRegister() {
        return "/register";
    }

    @GetMapping("/toStoreRegister")
    public String ToStoreRegister() {
        return "/storeRegister";
    }
}
