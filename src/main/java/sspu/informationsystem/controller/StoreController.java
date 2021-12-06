package sspu.informationsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sspu.informationsystem.entity.Dishes;
import sspu.informationsystem.entity.Store;
import sspu.informationsystem.service.ApplyService;
import sspu.informationsystem.service.DishesService;
import sspu.informationsystem.service.OrderService;
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
    @Resource
    DishesService dishesService;
    @Resource
    OrderService orderService;

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
                case 1: session.setAttribute("store",check);
                    return "redirect:/toStoreMain";
                case 0: return "redirect:/store/registering";

            }
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

    @GetMapping("/store/list")
    public String toStoreList(Model model) {
        List<Store> storeList = storeService.getAllStore();
        storeList = storeService.getAddress(storeList);
        model.addAttribute("storeList",storeList);
        return "stores";
    }

    @GetMapping("/store/info/id={storeId}")
    public String toStoreInfo(@PathVariable("storeId")Integer storeId, Model model){
        model.addAttribute("dishesList",storeService.getDishesById(storeId));
        return "storeInfo";
    }

    @GetMapping("/toAdminStores")
    public String ToAdminStores(Model model) {
        List<Store> storeList = storeService.getAllStore();
        storeList = storeService.getAddress(storeList);
        model.addAttribute("storeList",storeList);
        return "adminStores";
    }

    @GetMapping("/toStoreMain")
    public String ToStoreMain(Model model, HttpSession session) {
        Store store = (Store) session.getAttribute("store");
        model.addAttribute("dishesList",storeService.getDishesById(store.getStoreId()));
        return "storeMainPage";
    }

    @GetMapping("/store/delete/id={storeId}")
    public String storeDelete(@PathVariable("storeId")Integer storeId){
        storeService.deleteStore(storeId);
        return "redirect:/toAdminStores";
    }

    @GetMapping("/store/infoForAdmin/id={storeId}")
    public String storeInfoForAdmin(@PathVariable("storeId")Integer storeId,Model model){
        model.addAttribute("dishesList",storeService.getDishesById(storeId));
        return "adminStoreInfo";
    }

    @GetMapping("/toStoreOrder")
    public String ToStoreOrder(Model model,HttpSession session) {
        Store store = (Store)session.getAttribute("store");
        model.addAttribute("orderList",orderService.getOrderByStoreId(store.getStoreId()));
        return "storeOrders";
    }

    @PostMapping("/store/alterDish/id={storeId}")
    public String storeAlterDish(@PathVariable("storeId")Integer storeId,Dishes dishes){
        if (dishes.getDishesId()==null){
            dishes.setDStoreId(storeId);
            dishesService.insert(dishes);
        }
        else {
            dishesService.updateDish(dishes);
        }
        return "redirect:/toStoreMain";

    }

    @GetMapping("/store/deleteDish/id={dishesId}")
    public String storeDeleteDish(@PathVariable("dishesId")Integer dishesId){
        dishesService.deleteDishById(dishesId);
        return "redirect:/toStoreMain";
    }

}
