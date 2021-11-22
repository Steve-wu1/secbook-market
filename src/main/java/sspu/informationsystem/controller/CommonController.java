package sspu.informationsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sspu.informationsystem.service.StoreService;
import sspu.informationsystem.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Controller
public class CommonController {

    @Resource
    UserService userService;
    @Resource
    StoreService storeService;

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


    @GetMapping("/toStoreList")
    public String toStoreList(Model model) {
        model.addAttribute("storeList",storeService.getAllStore());
        return "/stores"; }

    @GetMapping("/toUserInfo")
    public String ToUserInfo() { return "/userInfo"; }

    @GetMapping("/toMyOrders")
    public String ToMyOrders() { return "/myOrders"; }


    @GetMapping("/toRegister")
    public String ToRegister(Model model) {
        List<String> phoneList = userService.getAllPhone();
        model.addAttribute("phoneList",phoneList);
        List<String> accountList = userService.getAllAccount();
        model.addAttribute("accountList",accountList);
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

    @GetMapping("/toDataAnalyze")
    public String ToDataAnalyze() {
        return "/dataAnalyze";
    }

    @GetMapping("/toStoreInfo")
    public String ToStoreInfo() {
        return "/storeInfo";
    }
}
