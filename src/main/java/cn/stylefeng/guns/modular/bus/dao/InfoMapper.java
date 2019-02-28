package cn.stylefeng.guns.modular.bus.dao;

import cn.stylefeng.guns.modular.bus.model.Info;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 详细内容 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-02-18
 */
public interface InfoMapper extends BaseMapper<Info> {
   Integer updateInfoHot(List<Info> list);
}
