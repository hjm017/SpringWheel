package com.prototype.controller.support;

import com.prototype.common.constants.YKConstant;
import com.prototype.controller.form.BaseSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/26
 * Time: 18:21
 * To change this template use File | Settings | File Templates.
 */
public class ControllerHelper {

    /**
     * 分页参数处理
     * @param <T>
     * @param t
     * @param records
     * @return
     */
    public static <T extends BaseSearchForm> T dealWithPage(T t, Page records) {
        t.setTotalRecord((int) records.getTotalElements());
        t.setTotalPage(records.getTotalPages());
        //如果当前页大于总页数，把当前页设置为1
        if (t.getCurrentPage() > t.getTotalPage()) {
            t.setCurrentPage(1);
        }
        return t;
    }

    /**
     * 显示信息提示页面
     * @return
     * @param map
     * @param code
     */
    public static String showMsgPage(ModelMap map, String code){
        map.addAttribute("code",code);
        return YKConstant.MSG_PAGE;
    }
}
