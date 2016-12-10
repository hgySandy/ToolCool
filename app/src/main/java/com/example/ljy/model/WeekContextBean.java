package com.example.ljy.model;

import java.util.List;

/**
 * Created by LJY on 2016/11/27.
 */
public class WeekContextBean {
    /**
     * success : true
     * items : [{"name":"科技周刊","type":2,"items":[{"id":"5837da3da8d90d1450499002","type":2,"title":"第五十四期","time":1480059983000},{"id":"582ea236a8d90d14381a0720","type":2,"title":"第五十三期","time":1479455013000},{"id":"58256741a8d90d6ccc942065","type":2,"title":"第五十二期","time":1478849854000},{"id":"581c2b57a8d90d6ccc681caf","type":2,"title":"第五十一期","time":1478245031000},{"id":"5812fa79a8d90d6ccc39faea","type":2,"title":"第五十期","time":1477643527000}]},{"name":"创业周刊","type":3,"items":[{"id":"5835306ea8d90d1447396c37","type":3,"title":"第五十三期","time":1479884825000},{"id":"582bf5d6a8d90d14380ad4fa","type":3,"title":"第五十二期","time":1479280730000},{"id":"5822bf41a8d90d6fb908578f","type":3,"title":"第五十一期","time":1478675665000},{"id":"581aa0d6a8d90d6ccf613b66","type":3,"title":"第五十期","time":1478143295000},{"id":"58104b42a8d90d6dd82aeb6d","type":3,"title":"第四十九期","time":1477465457000}]},{"name":"设计匠艺","type":1,"items":[{"id":"5832fdc9a8d90d143e2f692c","type":1,"title":"第七十八期","time":1479737201000},{"id":"582079d4a8d90d6cd27d323d","type":1,"title":"第七十七期","time":1478523736000},{"id":"580f0503a8d90d6cd826f630","type":1,"title":"第七十六期","time":1477380769000},{"id":"57fb9eaaa8d90d6694270bc2","type":1,"title":"第七十五期","time":1476108310000},{"id":"57e9152aa8d90d42830706e7","type":1,"title":"第七十四期","time":1474893724000}]},{"name":"编程狂人","type":0,"items":[{"id":"583690bba8d90d14473f275c","type":0,"title":"第一五二期","time":1479974282000},{"id":"582d4c86a8d90d1444114a98","type":0,"title":"第一五一期","time":1479366634000},{"id":"58241334a8d90d6cc38c85ba","type":0,"title":"第一五零期","time":1478761810000},{"id":"581adb01a8d90d6dd85d0170","type":0,"title":"第一四九期","time":1478157418000},{"id":"5811a769a8d90d6cd832c53e","type":0,"title":"第一四八期","time":1477554196000}]},{"name":"Guru Weekly","type":4,"items":[{"id":"5834f562a8d90d3ad02f7b85","type":4,"title":"Issue #52","time":1479867860000},{"id":"582aee75a8d90d144105a82d","type":4,"title":"Issue #51","time":1479212995000},{"id":"58227f8ca8d90d6fb906ee60","type":4,"title":"Issue #50","time":1478658875000},{"id":"581868c1a8d90d6dd850fba7","type":4,"title":"Issue #49","time":1477999837000},{"id":"581014cba8d90d6ccc2c154f","type":4,"title":"Issue #48","time":1477451423000}]}]
     */

    private boolean success;
    private List<ItemsBeanX> items;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ItemsBeanX> getItems() {
        return items;
    }

    public void setItems(List<ItemsBeanX> items) {
        this.items = items;
    }

    public static class ItemsBeanX {
        /**
         * name : 科技周刊
         * type : 2
         * items : [{"id":"5837da3da8d90d1450499002","type":2,"title":"第五十四期","time":1480059983000},{"id":"582ea236a8d90d14381a0720","type":2,"title":"第五十三期","time":1479455013000},{"id":"58256741a8d90d6ccc942065","type":2,"title":"第五十二期","time":1478849854000},{"id":"581c2b57a8d90d6ccc681caf","type":2,"title":"第五十一期","time":1478245031000},{"id":"5812fa79a8d90d6ccc39faea","type":2,"title":"第五十期","time":1477643527000}]
         */

        private String name;
        private int type;
        private List<ItemsBean> items;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * id : 5837da3da8d90d1450499002
             * type : 2
             * title : 第五十四期
             * time : 1480059983000
             */

            private String id;
            private int type;
            private String title;
            private long time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }
        }
    }
}
