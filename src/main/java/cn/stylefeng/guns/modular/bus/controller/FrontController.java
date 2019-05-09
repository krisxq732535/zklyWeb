package cn.stylefeng.guns.modular.bus.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController extends BaseController {

    private String PREFIX = "/front/";

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("id", 1);
        return PREFIX + "product.html";
    }
}
