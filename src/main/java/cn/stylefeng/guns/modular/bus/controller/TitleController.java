package cn.stylefeng.guns.modular.bus.controller;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.util.DateUtils;
import cn.stylefeng.guns.core.util.FileUtils;
import cn.stylefeng.guns.core.util.StringUtil2;
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
import cn.stylefeng.guns.modular.bus.model.Title;
import cn.stylefeng.guns.modular.bus.service.ITitleService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * 标题控制器
 *
 * @author fengshuonan
 * @Date 2019-02-18 11:09:15
 */
@Controller
@RequestMapping("/title")
public class TitleController extends BaseController {

    private String PREFIX = "/bus/title/";
    @Autowired
    private  GunsProperties properties;

    @Autowired
    private ITitleService titleService;
    @RequestMapping("/toPage")
    public String toPage() {
        return PREFIX + "web_title.html";
    }
    /**
     * 跳转到标题首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "title.html";
    }

    /**
     * 跳转到添加标题
     */
    @RequestMapping("/title_add")
    public String titleAdd() {
        return PREFIX + "title_add.html";
    }

    /**
     * 跳转到修改标题
     */
    @RequestMapping("/title_update/{titleId}")
    public String titleUpdate(@PathVariable Integer titleId, Model model) {
        Title title = titleService.selectById(titleId);
        title.setFilePath(properties.getFileServerUrl()+title.getFilePath());
        model.addAttribute("item",title);
        LogObjectHolder.me().set(title);
        return PREFIX + "title_edit.html";
    }

    /**
     * 获取标题列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()
        List<Title> titles = titleService.selectList(null);
        for (Title title : titles) {
            if (StringUtil2.isNotEmpty(title.getFilePath())) {
                title.setFilePath(properties.getFileServerUrl() + title.getFilePath());
            }
        }
        return titles;
    }

    /**
     * 新增标题
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Title title,MultipartFile file) throws Exception {
        if (file!=null){
            String uploads = FileUtils.uploads("/title", file);
            title.setFilePath(uploads);
        }
        title.setCreateTime(DateUtils.getTodayString());
        title.setCreateUser(ShiroKit.getUser().getId()+"");
        titleService.insert(title);
        return SUCCESS_TIP;
    }

    /**
     * 删除标题
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer titleId) {
        titleService.deleteById(titleId);
        return SUCCESS_TIP;
    }

    /**
     * 修改标题
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Title title, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        if (!file.isEmpty()){
            //String realPath = request.getSession().getServletContext().getRealPath("/")+"/static/webImage";
            String uploads = FileUtils.uploads("/title",file);
            title.setFilePath(uploads);
        }
        title.setModifyTime(DateUtils.getTodayString());
        title.setModifyUser(ShiroKit.getUser().getId()+"");
        titleService.updateById(title);
        return SUCCESS_TIP;
    }

    /**
     * 标题详情
     */
    @RequestMapping(value = "/detail/{titleId}")
    @ResponseBody
    public Object detail(@PathVariable("titleId") Integer titleId) {
        return titleService.selectById(titleId);
    }
}
