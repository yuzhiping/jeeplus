package com.jeeplus.web.utils;

/**
 * 常量
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 16:20.
 */
public class Constant {

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

}
