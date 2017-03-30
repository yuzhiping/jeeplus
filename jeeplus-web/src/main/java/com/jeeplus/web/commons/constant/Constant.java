package com.jeeplus.web.commons.constant;

/**
 * 常量
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 16:20.
 */
public class Constant {

    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;

    /**
     * 菜单类型
     */
    public enum MenuType{
        /**

         * 目录

         */
        CATALOG(0),
        /**

         * 菜单

         */
        MENU(1),
        /**

         * 按钮

         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**

         * 正常

         */
        NORMAL(0),
        /**

         * 暂停

         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**

     * 云服务商

     */
    public enum CloudService {
        /**

         * 阿里云

         */
        QINIU(1),
        /**

         * 阿里云

         */
        ALIYUN(2),
        /**

         * 腾讯云

         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
