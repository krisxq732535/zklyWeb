package cn.stylefeng.guns.modular.bus.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;


import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 网站标题
 * </p>
 *
 * @author stylefeng
 * @since 2019-02-18
 */
@TableName("web_title")
public class Title extends Model<Title> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 标题名字
     */
    private String name;
    /**
     * 父标题id 默认0
     */
    private Integer pid;
    /**
     * 图片路径
     */
    @TableField("file_path")
    private String filePath;
    /**
     * 排序
     */
    @TableField("sort_order")
    private Integer sortOrder;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 修改时间
     */
    @TableField("modify_time")
    private String modifyTime;
    /**
     * 修改人
     */
    @TableField("modify_user")
    private String modifyUser;
    @TableField("is_menu")
    private String isMenu;

    @TableField(exist = false)
    private List<Info> infos;
    @TableField(exist = false)
    private Integer type;
    private Integer hot;

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }



    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Info> getInfos() {
        return infos;
    }

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Title{" +
        ", id=" + id +
        ", name=" + name +
        ", pid=" + pid +
        ", filePath=" + filePath +
        ", sortOrder=" + sortOrder +
        ", createTime=" + createTime +
        ", createUser=" + createUser +

        "}";
    }
}
