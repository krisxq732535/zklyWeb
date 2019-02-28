package cn.stylefeng.guns.modular.bus.filter;

import cn.stylefeng.guns.core.util.DateUtils;
import cn.stylefeng.guns.core.util.IpUtil;
import cn.stylefeng.guns.modular.bus.dao.InfoMapper;
import cn.stylefeng.guns.modular.bus.dao.TitleMapper;
import cn.stylefeng.guns.modular.bus.model.Info;
import cn.stylefeng.guns.modular.bus.model.Title;
import io.swagger.models.auth.In;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author XinJinCan
 * @Date
 * @Param
 * @return
 * @Version 1.0
 **/
//点击量统计
public class HotFilter implements Filter {
    //点击量
    private static Map<String,Integer> map=new HashedMap();
    //客户端ip统计
    private static Map<String,Integer> ipMap=new HashedMap();

    @Resource
    InfoMapper infoMapper;
    @Resource
    TitleMapper titleMapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        if ("/web/list".equals(httpRequest.getServletPath())||"/web/list".equals(httpRequest.getServletPath())){
        String ipAddr = IpUtil.getIpAddr(httpRequest);
        String id = request.getParameter("id");
        if ("/web/list".equals(httpRequest.getServletPath())){//标题访问量
            id="title-"+id;
            ipAddr="title-"+id+"-"+ipAddr;
        }else if ("/web/info".equals(httpRequest.getServletPath())){//内容访问量
            id="info-"+id;
            ipAddr="info-"+id+"-"+ipAddr;
        }
        Integer integer = ipMap.get(ipAddr);
        if (integer==null){//该ip第一次访问,过滤同个ip重复访问
            Integer hot = map.get(id);
            if (hot!=null){
                map.put(id,hot+1);
            }else {
                map.put(id,1);
            }
            ipMap.put(ipAddr,1);//添加访问记录
        }
        }
    }

    @Override
    public void destroy() {

    }

    /*每十秒点击量同步到数据库*/
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void testTasks() {
        System.out.println(DateUtils.getTodayString());
        List<Title> titles=new ArrayList<>();
        List<Info> infos=new ArrayList<>();
        for (String s : map.keySet()) {
            String[] split = s.split("-");
                if (split[0].equals("title")){
                    Title title=new Title();
                    title.setId(Integer.valueOf(split[1]));
                    title.setHot(map.get(s));
                    titles.add(title);
                }else if (split[0].equals("info")){
                    Info info=new Info();
                    info.setId(Integer.valueOf(split[1]));
                    info.setHot(map.get(s));
                    infos.add(info);
                }
        }
        map.clear();
        ipMap.clear();
        if (infos.size()>0){
            infoMapper.updateInfoHot(infos);
        }
     if (titles.size()>0){
         titleMapper.updateTitleHot(titles);
     }

    }
}
