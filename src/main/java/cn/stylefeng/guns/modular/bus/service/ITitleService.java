package cn.stylefeng.guns.modular.bus.service;

import cn.stylefeng.guns.modular.bus.model.Title;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站标题 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-02-18
 */
public interface ITitleService extends IService<Title> {

    //查询最下方菜单栏
    Map<String,Object> selectDownMenu();

}
