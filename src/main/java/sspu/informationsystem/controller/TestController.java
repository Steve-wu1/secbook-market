package sspu.informationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello spring boot!~";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/toStoreList")
    public String ToStoreList() {
        return "/stores";
    }

    @GetMapping("/toUserInfo")
    public String ToUserInfo() {
        return "/userInfo";
    }

    @GetMapping("/toMyOrders")
    public String ToMyOrders() {
        return "/myOrders";
    }

    @GetMapping("/toRegister")
    public String ToRegister() {
        return "/register";
    }

    @GetMapping("/toStoreRegister")
    public String ToStoreRegister() {
        return "/storeRegister";
    }

    @GetMapping("/toStoreMain")
    public String ToStoreMain() {
        return "/storeMainPage";
    }

    @GetMapping("/toStoreOrder")
    public String ToStoreOrder() {
        return "/storeOrders";
    }

    @GetMapping("/toDataAnalysize")
    public String ToDataAnalysize() {
        return "/dataAnalysize";
    }
}
