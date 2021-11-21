package sspu.informationsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import sspu.informationsystem.entity.Store;
import sspu.informationsystem.entity.User;
import sspu.informationsystem.service.StoreService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class StoreController {

    @Resource
    StoreService storeService;

    /**
     * 商家登录验证
     *
     * @param store 商家填写信息
     * @return 用户登录信息校验
     */
    @PostMapping("/store/login")
    public String storeLogin(Store store, HttpSession session) {
        log.debug("账号"+store.getSAccount()+"密码"+store.getSPassword());
        Store check = storeService.getStoreInfoByAccount(store.getSAccount());
        if (check.getStoreId()==null)
        {
            return "redirect:/loginFailure";
        }
        if (check.getSPassword().equals(store.getSPassword()))
        {
            session.setAttribute("storeAccount",store.getSAccount());
            return "redirect:/toStoreMain";
        }
        return "redirect:/loginFailure";
    }
}
