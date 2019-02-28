package cn.stylefeng.guns.modular.bus.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回安卓接口的对象
 *
 * @author chenyu
 */
public class Result implements Serializable {

    private int code = 0;//状态
    private String msg = "";//信息
    private String excMsg = "";//异常信息
    private String paramNames = "";//参数信息
    private Map<String, Object> data = new HashMap<>();// 数据集合

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getExcMsg() {
        return excMsg;
    }

    public void setExcMsg(String excMsg) {
        this.excMsg = excMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.data = dataMap;
    }

    public String getParamNames() {
        return paramNames;
    }

    public void setParamNames(String paramNames) {
        this.paramNames = paramNames;
    }

    public void setData(Map<String, Object> map) {
        this.data = map;
    }
}
