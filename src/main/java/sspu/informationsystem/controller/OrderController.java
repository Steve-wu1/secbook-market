package sspu.informationsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sspu.informationsystem.entity.OrderDishesBind;
import sspu.informationsystem.entity.User;
import sspu.informationsystem.service.OrderService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class OrderController {

    @Resource
    OrderService orderService;

    @PostMapping("/order/submit")
    public String orderSubmit(@RequestBody String request, HttpSession session){
        User user = (User)session.getAttribute("user");
        //json解析
        request = request.substring(1,request.length()-1);
        String dealtRequest = StringEscapeUtils.unescapeJavaScript(request);
        List<OrderDishesBind> orderDishesBindList = JSONArray.parseArray(dealtRequest,OrderDishesBind.class);
        //订单创建绑定和检验
        orderDishesBindList = orderService.checkDishesInOrder(orderDishesBindList);
        orderService.addOrder(user.getUserId(),orderDishesBindList);
        return "redirect:/store/list";
    }

}