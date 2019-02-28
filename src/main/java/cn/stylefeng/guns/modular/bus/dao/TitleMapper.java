package cn.stylefeng.guns.modular.bus.dao;

import cn.stylefeng.guns.modular.bus.model.Title;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 网站标题 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-02-18
 */
public interface TitleMapper extends BaseMapper<Title> {
    List<Title> selectDownMenu();
    Integer updateTitleHot(List<Title> list);

}
