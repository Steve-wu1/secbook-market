package sspu.informationsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sspu.informationsystem.entity.Store;
import sspu.informationsystem.service.ApplyService;
import sspu.informationsystem.service.StoreService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class StoreController {

    @Resource
    StoreService storeService;
    @Resource
    ApplyService applyService;

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
            switch (storeService.checkApplyState(check.getStoreId())){
                case 2: return "redirect:/store/registerFailure";
                case 1: session.setAttribute("storeAccount",store.getSAccount());
                    return "redirect:/toStoreMain";
                case 0: return "redirect:/store/registering";

            }


            session.setAttribute("storeAccount",store.getSAccount());
            return "redirect:/toStoreMain";

        }
        return "redirect:/loginFailure";
    }

    @PostMapping("/store/register")
    public String userRegister(Store store){
        storeService.insert(store);
        applyService.addStoreRegisterApply(store.getStoreId());
        return "redirect:/store/registering";

    }


    /**
     * 临时顶替 后期可能用页面
     */
    @ResponseBody
    @GetMapping("/store/registering")
    public String userRegistering(){
        return "注册申请正在审核中 请耐心等待";

    }

    /**
     * 临时顶替 后期可能用页面
     */
    @ResponseBody
    @GetMapping("/store/registerFailure")
    public String userFailure(){
        return "您的申请已被管理员拒绝！";

    }

    @GetMapping("/toStoreList")
    public String toStoreList(Model model) {
        List<Store> storeList = storeService.getAllStore();
        storeList = storeService.getAddress(storeList);
        model.addAttribute("storeList",storeList);
        return "stores";
    }

    @GetMapping("/store/info/id={storeId}")
    public String toStoreInfo(@PathVariable("storeId")Integer storeId, Model model){
        model.addAttribute("store",storeService.getStoreInfoById(storeId));
        model.addAttribute("dishList",storeService.getDishesById(storeId));
        return "storeInfo";
    }

    @GetMapping("/toAdminStores")
    public String ToAdminStores(Model model) {
        List<Store> storeList = storeService.getAllStore();
        storeList = storeService.getAddress(storeList);
        model.addAttribute("storeList",storeList);
        return "adminStores";
    }

    @GetMapping("/store/delete/id={storeId}")
    public String storeDelete(@PathVariable("storeId")Integer storeId){
        storeService.deleteStore(storeId);
        return "redirect:/toAdminStores";
    }

}
