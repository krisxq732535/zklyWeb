package cn.stylefeng.guns.modular.bus.service.impl;

import cn.stylefeng.guns.modular.bus.dao.InfoMapper;
import cn.stylefeng.guns.modular.bus.model.Info;
import cn.stylefeng.guns.modular.bus.model.Title;
import cn.stylefeng.guns.modular.bus.dao.TitleMapper;
import cn.stylefeng.guns.modular.bus.service.ITitleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站标题 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-02-18
 */
@Service
public class TitleServiceImpl extends ServiceImpl<TitleMapper, Title> implements ITitleService {

    @Resource
    TitleMapper titleMapper;
    @Resource
    InfoMapper infoMapper;

    @Override
    public Map<String,Object> selectDownMenu() {
        Map<String,Object> map=new HashedMap();
        Wrapper<Title> wrapper=new EntityWrapper<Title>();
        wrapper.eq("pid",0);
        Title title2=new Title();
        title2.setDesc("gywm");
        Title title3 = titleMapper.selectOne(title2);
        System.out.println(title3);
        List<Title> titleOn = titleMapper.selectList(wrapper);
        for (Title title : titleOn) {
            title.setType(1);
        }
        List<Title> titleDown = titleMapper.selectDownMenu();
        for (Title title : titleDown) {
            title.setType(1);
            if (title.getId().intValue()==title3.getId()){
                title.getInfos().clear();
                //查询关于我们
                wrapper=new EntityWrapper<Title>();
                wrapper.eq("pid",title3.getId());
                wrapper.eq("is_menu",1);
                List<Title> guanyu = titleMapper.selectList(wrapper);
                if (guanyu.size()>0){
                    for (Title title1 : guanyu) {
                        Info info=new Info();
                        info.setId(title1.getId());
                        info.setTitle(title1.getName());
                        title.getInfos().add(info);
                    }
                }
            }else {
                for (Info info : title.getInfos()) {

                    info.setType(2);
                }
            }

        }
        map.put("titleOn",titleOn);
        map.put("titleDown",titleDown);
        return map;
    }
}
