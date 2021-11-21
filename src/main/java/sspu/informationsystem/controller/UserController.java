package sspu.informationsystem.controller;

import lombok.extern.slf4j.Slf4j;
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
        if (check.getUserId()==null)
        {
            return "redirect:/loginFailure";
        }
        if (check.getUPassword().equals(user.getUPassword()))
        {
            session.setAttribute("user",check);
            return "redirect:/toStoreList";
        }
        return "redirect:/loginFailure";
    }

    @PostMapping("/user/register")
    public String userRegister(User user,HttpSession session){
        userService.insert(user);
        session.setAttribute("user",userService.getUserInfoByAccount(user.getUAccount()));
        return "redirect:/toStoreList";

    }

    @PostMapping("/user/updateInfo")
    public String userUpdateInfo(User user, HttpSession session){
        User original =(User) session.getAttribute("user");
        user.setUserId(original.getUserId());
        userService.updateInfoById(user);
        session.setAttribute("user", userService.getUserInfoById(original.getUserId()));
        return "redirect:/toUserInfo";
    }
}
