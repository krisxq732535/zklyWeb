package cn.stylefeng.guns.modular.bus.controller;

import cn.stylefeng.guns.modular.bus.model.Info;
import cn.stylefeng.guns.modular.bus.model.Result;
import cn.stylefeng.guns.modular.bus.model.Title;
import cn.stylefeng.guns.modular.bus.service.IInfoService;
import cn.stylefeng.guns.modular.bus.service.ITitleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author XinJinCan
 * @Date
 * @Param
 * @return
 * @Version 1.0
 **/
@Controller()
@RequestMapping("/web")
public class WebController {
    private String PREFIX = "/front/";
    @Autowired
    private ITitleService titleService;

    @Autowired
    private IInfoService infoService;

    //页面跳转
    //产品
    @RequestMapping("/product")
    public String product(Model model,Integer id) {
        model.addAttribute("id",id);
        return PREFIX + "product.html ";
    }
    //观点
    @RequestMapping("/viewpoint")
    public String viewpoint(Model model,Integer id) {
        model.addAttribute("id",id);
        return PREFIX + "viewpoint.html ";
    }
    //案例
    @RequestMapping("/case")
    public String toCase(Model model,Integer id) {
        model.addAttribute("id",id);
        return PREFIX + "case.html ";
    }
    //服务
    @RequestMapping("/service")
    public String service(Model model,Integer id) {
        model.addAttribute("id",id);
        return PREFIX + "service.html ";
    }
    //联系我们
    @RequestMapping("/connection")
    public String connection(Model model,Integer id) {
        model.addAttribute("id",id);
        return PREFIX + "connection.html ";
    }
    //产品
    @RequestMapping("/product")
    public String titleAdd(Model model,Integer id) {
        model.addAttribute("id",id);
        return PREFIX + "product.html ";
    }


    //获取全部菜单栏,和底部子菜单栏
    @RequestMapping("menu")
    @ResponseBody
    public Result menu(){
        Result result=new Result();
        result.setDataMap(titleService.selectDownMenu());
        return result;
    }

    //根据菜单id查询数据
    @RequestMapping("list")
    @ResponseBody
    public Result list(Integer id){
        Result result=new Result();
        Map<String, Object> map = new HashedMap();
        Title title = titleService.selectById(id);
        if (title.getPid()==0){

        }
        Wrapper<Title> wrapper = new EntityWrapper<Title>();
        wrapper.eq("pid",id);
        wrapper.orderBy("sort_order");
        List<Title> titles = titleService.selectList(wrapper);
        if (titles.size()==0){
            Wrapper<Info> wrapper1 = new EntityWrapper<Info>();
            wrapper.eq("type",id);
            wrapper.orderBy("sort_order");
            List<Info> infos = infoService.selectList(wrapper1);
            title.setInfos(infos);
            titles.add(title);
        }else {
            Wrapper<Info> wrapper1 = new EntityWrapper<Info>();
            for (Title title1 : titles) {
                wrapper.eq("type",title1.getId());
                wrapper.orderBy("sort_order");
                List<Info> infos = infoService.selectList(wrapper1);
                title1.setInfos(infos);
            }
        }
        result.getData().put("infos",titles);
        return result;
    }
    //根据数据id查询详细信息
    @RequestMapping("info")
    @ResponseBody
    public Result info(Integer id){
        Result result=new Result();
        Info info = infoService.selectById(id);
        result.getData().put("info",info);
        return result;
    }
}
