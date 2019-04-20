package cn.stylefeng.guns.modular.bus.controller;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.util.DateUtils;
import cn.stylefeng.guns.core.util.FileUtils;
import cn.stylefeng.guns.core.util.StringUtil2;
import cn.stylefeng.guns.modular.bus.model.Title;
import cn.stylefeng.guns.modular.bus.service.ITitleService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.bus.model.Info;
import cn.stylefeng.guns.modular.bus.service.IInfoService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 内容控制器
 *
 * @author fengshuonan
 * @Date 2019-02-18 11:30:38
 */
@Controller
@RequestMapping("/info")
public class InfoController extends BaseController {

    private String PREFIX = "/bus/info/";
    @Autowired
    private  GunsProperties properties;
    @Autowired
    private IInfoService infoService;
    @Autowired
    private ITitleService titleService;
    @RequestMapping("/toPage")
    public String toPage() {
        return PREFIX + "web_info.html";
    }

    /**
     * 跳转到内容首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "info.html";
    }

    /**
     * 跳转到添加内容
     */
    @RequestMapping("/info_add")
    public String infoAdd(Model model) {
        List<Title> titles = titleService.selectList(null);
        model.addAttribute("titles",titles);
        return PREFIX + "info_add.html";
    }

    /**
     * 跳转到修改内容
     */
    @RequestMapping("/info_update/{infoId}")
    public String infoUpdate(@PathVariable Integer infoId, Model model) {
        Info info = infoService.selectById(infoId);
        if (StringUtil2.isNotEmpty(info.getIconPath())){
            info.setIconPath(properties.getFileServerUrl()+info.getIconPath());
        }
        model.addAttribute("item",info);
        List<Title> titles = titleService.selectList(null);
        model.addAttribute("titles",titles);
        LogObjectHolder.me().set(info);
        return PREFIX + "info_edit.html";
    }

    /**
     * 获取内容列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition, HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println(realPath);
        List<Info> infos = infoService.selectList(null);
        for (Info info : infos) {
            if (StringUtil2.isNotEmpty(info.getIconPath())){
                info.setIconPath(properties.getFileServerUrl()+info.getIconPath());
            }
        }
        return infos;
    }

    /**
     * 新增内容
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Info info) throws Exception {
//        if (!file.isEmpty()){
//            String uploads = FileUtils.uploads("/title", file);
//            info.setIconPath(uploads);
//        }
        info.setCreateTime(DateUtils.getTodayString());
        info.setCreateUser(ShiroKit.getUser().getId()+"");
        infoService.insert(info);
        return SUCCESS_TIP;
    }

    /**
     * 删除内容
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer infoId) {
        infoService.deleteById(infoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改内容
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Info info ,MultipartFile file) throws Exception {
        if (!file.isEmpty()){
            String uploads = FileUtils.uploads("/title", file);
            info.setIconPath(uploads);
        }
        info.setModifyTime(DateUtils.getTodayString());
        info.setModifyUser(ShiroKit.getUser().getId()+"");
        infoService.updateById(info);
        return SUCCESS_TIP;
    }

    /**
     * 内容详情
     */
    @RequestMapping(value = "/detail/{infoId}")
    @ResponseBody
    public Object detail(@PathVariable("infoId") Integer infoId) {
        return infoService.selectById(infoId);
    }
}
