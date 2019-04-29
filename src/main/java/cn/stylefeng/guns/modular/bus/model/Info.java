package cn.stylefeng.guns.modular.bus.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 详细内容
 * </p>
 *
 * @author stylefeng
 * @since 2019-02-18
 */
@TableName("web_info")
public class Info extends Model<Info> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 标题
     */
    private Integer type;
    @TableField(exist = false)
    private String typeName;
    /**
     * 标题
     */
    private String title;
    /**
     * 图标路径
     */
    @TableField("icon_path")
    private String iconPath;
    /**
     * 文本
     */
    private String content;
    /**
     * 简介
     */
    private String desc;
    /**
     * 浏览量
     */
    private Integer hot;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;
    /**
     * 修改人
     */
    @TableField("modify_user")
    private String modifyUser;
    /**
     * 修改时间
     */
    @TableField("modify_time")
    private String modifyTime;
    /**
     * 0不在底部显示 1在底部显示
     */
    @TableField("is_menu")
    private Integer isMenu;
    /**
     * 顺序
     */
    @TableField("sort_order")
    private Integer sortOrder;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Info{" +
        ", id=" + id +
        ", type=" + type +
        ", title=" + title +
        ", iconPath=" + iconPath +
        ", content=" + content +
        ", desc=" + desc +
        ", hot=" + hot +
        ", createUser=" + createUser +
        ", createTime=" + createTime +

        ", isMenu=" + isMenu +
        ", sortOrder=" + sortOrder +
        "}";
    }
}
