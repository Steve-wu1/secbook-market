package sspu.informationsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sspu.informationsystem.entity.Dishes;
import sspu.informationsystem.entity.Order;
import sspu.informationsystem.entity.Store;
import sspu.informationsystem.service.ApplyService;
import sspu.informationsystem.service.DishesService;
import sspu.informationsystem.service.OrderService;
import sspu.informationsystem.service.StoreService;
import sspu.informationsystem.utils.FtpUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class StoreController {

    @Resource
    FtpUtil ftpUtil;
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
    public String userRegister(Store store,MultipartFile file){
        String fileName = ftpUtil.uplaod(file);
        store.setSPhoto(fileName);
        log.debug("storeInfo is "+ store);
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
        List<Store> storeList = storeService.getAllStorePassed();
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
        List<Store> storeList = storeService.getAllStorePassed();
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
        List<Order> orderList = orderService.getOrderByStoreId(store.getStoreId());
        model.addAttribute("orderList",orderList);
        return "storeOrders";
    }

    @PostMapping("/store/alterDish/id={storeId}")
    public String storeAlterDish(@PathVariable("storeId")Integer storeId,Dishes dishes,MultipartFile file){
        if (dishes.getDishesId()==null){
            dishes.setDPhoto(ftpUtil.uplaod(file));
            dishes.setDStoreId(storeId);
            dishesService.insert(dishes);
        }
        else {
            if (!file.isEmpty()) {
                dishes.setDPhoto(ftpUtil.uplaod(file));
            }
            dishesService.updateDish(dishes);
        }
        return "redirect:/toStoreMain";

    }

    @GetMapping("/store/deleteDish/id={dishesId}")
    public String storeDeleteDish(@PathVariable("dishesId")Integer dishesId){
        dishesService.deleteDishById(dishesId);
        return "redirect:/toStoreMain";
    }

    @GetMapping("/toDataAnalyze")
    public String ToDataAnalyze(Model model,HttpSession session) {
        Store store = (Store) session.getAttribute("store");
        //店铺评分
        model.addAttribute("rank",storeService.getRankById(store.getStoreId()));
        //本月明星产品
        List<Dishes> dishesList = storeService.getDishesOrderByCountForMonth(store.getStoreId());
        model.addAttribute("starDish",dishesList.get(0));
        model.addAttribute("saleStarDish",storeService.getMonthStarDishSale(dishesList.get(0).getDishesId()));
        //TOP3产品
        model.addAttribute("secDish",dishesList.get(1));
        model.addAttribute("saleSecDish",storeService.getMonthStarDishSale(dishesList.get(1).getDishesId()));
        model.addAttribute("thrDish",dishesList.get(2));
        model.addAttribute("saleThrDish",storeService.getMonthStarDishSale(dishesList.get(2).getDishesId()));
        //本日销售数据及订单数
        model.addAttribute("saleToday",storeService.getSaleToday(store.getStoreId()));
        model.addAttribute("orderCountToday",storeService.getOrderCountToday(store.getStoreId()));
        //同一食堂本日销售额
        String tempAddress = store.getSAddress();
        tempAddress = tempAddress.substring(0,1);
        tempAddress += "_";
        model.addAttribute("saleSameAddressToday",storeService.getSaleSameAddressToday(tempAddress));
        //本周销售额及订单数
        model.addAttribute("saleWeek",storeService.getSaleWeek(store.getStoreId()));
        model.addAttribute("orderCountWeek",storeService.getOrderCountWeek(store.getStoreId()));
        //本月销售额及订单数
        model.addAttribute("saleMonth",storeService.getSaleMonth(store.getStoreId()));
        model.addAttribute("orderCountMonth",storeService.getOrderCountMonth(store.getStoreId()));
        //近五个月
        List<Object> month = storeService.getRecentFiveMonth();
        month.add(0, 'x');
        model.addAttribute("month", month);
        log.debug("ces" + month);
        //近五个月销售额
        List<Object> mcount = storeService.getStoreMCount(store.getStoreId());
        mcount.add(0, "本店订单数");
        model.addAttribute("mcount", mcount);
        log.debug("ces" + mcount);
        //近五个月本平台各食堂平均销售额
        List<Object> acount = storeService.getAllAverageMCount();
        acount.add(0, "食堂商家平均订单数");
        model.addAttribute("acount", acount);
        log.debug("ces" + acount);
        return "dataAnalyze";
    }

}
