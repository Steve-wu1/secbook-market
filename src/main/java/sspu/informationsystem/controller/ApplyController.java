package sspu.informationsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sspu.informationsystem.entity.User;
import sspu.informationsystem.service.ApplyService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class ApplyController {

    @Resource
    ApplyService applyService;

    @GetMapping("/apply/list")
    public String applyList(Model model){
        model.addAttribute("applyList",applyService.getAllApplyUndealed());
        return "adminApply";
    }

    @GetMapping("/apply/pass/id={applyId}")
    public String applyPass(@PathVariable("applyId")Integer applyId,HttpSession session){
        log.debug("到达此方案！！！！！！！");
        User admin = (User) session.getAttribute("user");
        applyService.setApplyPass(applyId,admin.getUserId());
        return "redirect:/apply/list";
    }

    @GetMapping("/apply/deny/id={applyId}")
    public String applyDeny(@PathVariable("applyId")Integer applyId,HttpSession session){
        log.debug("到达此方案！！！！！！！");
        User admin = (User) session.getAttribute("user");
        applyService.setApplyDeny(applyId,admin.getUserId());
        return "redirect:/apply/list";
    }

    @GetMapping("/toAdminApply")
    public String ToAdminApply() {
        return "adminApply";
    }
}
