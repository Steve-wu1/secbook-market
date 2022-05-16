package sspu.informationsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sspu.informationsystem.entity.Book;
import sspu.informationsystem.entity.Order;
import sspu.informationsystem.entity.Store;
import sspu.informationsystem.service.ApplyService;
import sspu.informationsystem.service.BookService;
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
    BookService bookService;
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

        model.addAttribute("storeList",storeList);
        return "stores";
    }

    @GetMapping("/store/info/id={storeId}")
    public String toStoreInfo(@PathVariable("storeId")Integer storeId, Model model){
        model.addAttribute("bookList", storeService.getBookById(storeId));
        return "storeInfo";
    }

    @GetMapping("/toAdminStores")
    public String ToAdminStores(Model model) {
        List<Store> storeList = storeService.getAllStorePassed();

        model.addAttribute("storeList",storeList);
        return "adminStores";
    }

    @GetMapping("/toStoreMain")
    public String ToStoreMain(Model model, HttpSession session) {
        Store store = (Store) session.getAttribute("store");
        model.addAttribute("bookList", storeService.getBookById(store.getStoreId()));
        return "storeMainPage";
    }

    @GetMapping("/store/delete/id={storeId}")
    public String storeDelete(@PathVariable("storeId")Integer storeId){
        storeService.deleteStore(storeId);
        return "redirect:/toAdminStores";
    }

    @GetMapping("/store/infoForAdmin/id={storeId}")
    public String storeInfoForAdmin(@PathVariable("storeId")Integer storeId,Model model){
        model.addAttribute("bookList", storeService.getBookById(storeId));
        return "adminStoreInfo";
    }

    @GetMapping("/toStoreOrder")
    public String ToStoreOrder(Model model,HttpSession session) {
        Store store = (Store)session.getAttribute("store");
        List<Order> orderList = orderService.getOrderByStoreId(store.getStoreId());
        model.addAttribute("orderList",orderList);
        return "storeOrders";
    }

    @PostMapping("/store/alterBook/id={storeId}")
    public String storeAlterBook(@PathVariable("storeId") Integer storeId, Book book, MultipartFile file) {
        if (book.getBookId() == null) {
            book.setBPhoto(ftpUtil.uplaod(file));
            book.setBStoreId(storeId);
            bookService.insert(book);
        }
        else {
            if (!file.isEmpty()) {
                book.setBPhoto(ftpUtil.uplaod(file));
            }
            bookService.updateBook(book);
        }
        return "redirect:/toStoreMain";

    }

    @GetMapping("/store/deleteBook/id={dishesId}")
    public String storeDeleteBook(@PathVariable("dishesId") Integer dishesId) {
        bookService.deleteBookById(dishesId);
        return "redirect:/toStoreMain";
    }

    @GetMapping("/toDataAnalyze")
    public String ToDataAnalyze(Model model,HttpSession session) {
        Store store = (Store) session.getAttribute("store");
        //店铺评分
        model.addAttribute("rank",storeService.getRankById(store.getStoreId()));
        //本月明星产品
        List<Book> bookList = storeService.getBookOrderByCountForMonth(store.getStoreId());
        if (bookList != null && bookList.size() > 0) {
            model.addAttribute("starBook", bookList.get(0));

            model.addAttribute("saleStarBook", storeService.getMonthStarBookSale(bookList.get(0).getBookId()));
            //TOP3产品
            model.addAttribute("secBook", bookList.get(1));
            model.addAttribute("saleSecBook", storeService.getMonthStarBookSale(bookList.get(1).getBookId()));
            model.addAttribute("thrBook", bookList.get(2));
            model.addAttribute("saleThrBook", storeService.getMonthStarBookSale(bookList.get(2).getBookId()));
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
            //近五个月销售额
            List<Object> mcount = storeService.getStoreMCount(store.getStoreId());
            mcount.add(0, "本店订单数");
            model.addAttribute("mcount", mcount);
            //近五个月本平台各书店平均销售额
            List<Object> acount = storeService.getAllAverageMCount();
            acount.add(0, "二手书店商家平均订单数");
            model.addAttribute("acount", acount);
            //评分数据
            model.addAttribute("rankData1",storeService.getRankData(store.getStoreId(),1));
            model.addAttribute("rankData2",storeService.getRankData(store.getStoreId(),2));
            model.addAttribute("rankData3",storeService.getRankData(store.getStoreId(),3));
            model.addAttribute("rankData4",storeService.getRankData(store.getStoreId(),4));
            model.addAttribute("rankData5",storeService.getRankData(store.getStoreId(),5));
        }
        return "dataAnalyze";
    }

}
