package sspu.informationsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sspu.informationsystem.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import sspu.informationsystem.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class UserController {

    @Resource
    UserService userService;

    /**
     * 用户登录验证
     *
     * @param user 用户填写信息
     * @return 用户登录信息校验
     */
    @PostMapping("/user/login")
    public String userLogin(User user, HttpSession session) {
        User check = userService.getUserInfoByAccount(user.getUAccount());
        if (check == null)
        {
            return "redirect:/loginFailure";
        }
        if (check.getUPassword().equals(user.getUPassword()))
        {
            session.setAttribute("user",check);
            if (userService.checkAdminState((check.getUserId()))){
                return "redirect:/apply/list";
            }
            else{
                return "redirect:/store/list";
            }

        }
        return "redirect:/loginFailure";
    }

    @PostMapping("/user/register")
    public String userRegister(User user,HttpSession session){
        userService.insert(user);
        session.setAttribute("user",userService.getUserInfoByAccount(user.getUAccount()));
        return "redirect:/store/list";

    }

    @PostMapping("/user/updateInfo")
    public String userUpdateInfo(User user, HttpSession session){
        User original =(User) session.getAttribute("user");
        user.setUserId(original.getUserId());
        userService.updateInfoById(user);
        session.setAttribute("user", userService.getUserInfoById(original.getUserId()));
        return "redirect:/toUserInfo";
    }

    @GetMapping("/toUserInfo")
    public String ToUserInfo(Model model, HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        return "userInfo";
    }

    @GetMapping("/toAdminUsers")
    public String ToAdminUsers(Model model) {
        model.addAttribute("userList",userService.getAllUser());
        return "adminUsers";
    }

    @GetMapping("/user/delete/id={userId}")
    public String userDelete(@PathVariable("userId")Integer userId){
        userService.deleteUser(userId);
        return "redirect:/toAdminUsers";
    }
}
