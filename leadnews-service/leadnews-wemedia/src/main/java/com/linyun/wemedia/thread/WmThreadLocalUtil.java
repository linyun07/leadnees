package com.linyun.wemedia.thread;

import com.linyun.model.wemedia.pojos.WmUser;

/**
 * @author linyun
 * @since 2023/7/19 10:37
 */


public class WmThreadLocalUtil {

    public static final ThreadLocal<WmUser> WM_USER_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 线程中添加数据
     * @param wmUser
     */
    public static void setUser(WmUser wmUser) {
        WM_USER_THREAD_LOCAL.set(wmUser);
    }

    /**
     * 获取线程中的数据
     * @param wmUser
     * @return
     */
    public static WmUser getUser() {
        return WM_USER_THREAD_LOCAL.get();
    }

    /**
     * 清理线程中的数据
     * @param wmUser
     */
    public static void cleanUser() {
        WM_USER_THREAD_LOCAL.remove();
    }
}
