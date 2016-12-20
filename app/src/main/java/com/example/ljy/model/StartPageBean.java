package com.example.ljy.model;

import java.util.List;

/**
 * Created by LJY on 2016/12/20.
 */
public class StartPageBean {
    /**
     * success : true
     * items : [{"id":"http://static0.tuicool.com/images/start_page/0/1.jpg","time":1},{"id":"http://static0.tuicool.com/images/start_page/0/2.jpg","time":2},{"id":"http://static0.tuicool.com/images/start_page/0/3.jpg","time":3},{"id":"http://static0.tuicool.com/images/start_page/0/4.jpg","time":4},{"id":"http://static0.tuicool.com/images/start_page/0/5.jpg","time":5},{"id":"http://static0.tuicool.com/images/start_page/0/6.jpg","time":6},{"id":"http://static0.tuicool.com/images/start_page/0/7.jpg","time":7},{"id":"http://static0.tuicool.com/images/start_page/0/8.jpg","time":8},{"id":"http://static0.tuicool.com/images/start_page/0/9.jpg","time":9}]
     */

    private boolean success;
    private List<ItemsBean> items;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * id : http://static0.tuicool.com/images/start_page/0/1.jpg
         * time : 1
         */

        private String id;
        private int time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
