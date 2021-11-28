package sspu.informationsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sspu.informationsystem.entity.Store;
import sspu.informationsystem.service.StoreService;
import sspu.informationsystem.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    @GetMapping("/loginFailure")
    public String loginFailure(Model model) {
        model.addAttribute("loginFail", "用户名或密码错误");
        return "login";
    }






    @GetMapping("/toMyOrders")
    public String ToMyOrders() { return "myOrders"; }


    @GetMapping("/toRegister")
    public String ToRegister(Model model) {
        model.addAttribute("phoneList",userService.getAllPhone());
        model.addAttribute("accountList",userService.getAllAccount());
        return "register";
    }

    @GetMapping("/toStoreRegister")
    public String ToStoreRegister(Model model) {
        model.addAttribute("phoneList",storeService.getALlPhone());
        model.addAttribute("accountList",storeService.getAllAccount());
        return "storeRegister";
    }

    @GetMapping("/toStoreMain")
    public String ToStoreMain() {
        return "storeMainPage";
    }

    @GetMapping("/toStoreOrder")
    public String ToStoreOrder() {
        return "storeOrders";
    }

    @GetMapping("/toDataAnalyze")
    public String ToDataAnalyze() {
        return "dataAnalyze";
    }

    @GetMapping("/toStoreInfo")
    public String ToStoreInfo() {
        return "storeInfo";
    }


}
