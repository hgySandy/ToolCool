package com.example.ljy.model;

import java.util.List;

/**
 * Created by LJY on 2016/12/13.
 */
public class SearchtopicBean {
    /**
     * success : true
     * topics : [{"id":11100050,"name":"Cisco IOS","image":"http://ttimg2.tuicool.com/11100050.png!middle","followed":false},{"id":10250001,"name":"iOS","image":"http://ttimg1.tuicool.com/10250001.png!middle","followed":false},{"id":10250045,"name":"iOS7","image":"http://ttimg1.tuicool.com/10250045.png!middle","followed":false},{"id":10250032,"name":"iOS6","image":"http://ttimg0.tuicool.com/10250032.png!middle","followed":false},{"id":10250028,"name":"iOS5","image":"http://ttimg0.tuicool.com/10250028.png!middle","followed":false},{"id":10250027,"name":"iOS4","image":"http://ttimg3.tuicool.com/10250027.png!middle","followed":false},{"id":10250104,"name":"iOS 9","image":"http://ttimg0.tuicool.com/10250104.png!middle","followed":false},{"id":10250078,"name":"iOS8","image":"http://ttimg2.tuicool.com/10250078.png!middle","followed":false},{"id":11140099,"name":"Xamarin.iOS","image":"http://ttimg3.tuicool.com/11140099.png!middle","followed":false},{"id":10250199,"name":"iOS 10","image":"http://ttimg3.tuicool.com/10250199.png!middle","followed":false},{"id":11080009,"name":"iOS开发","image":"http://ttimg1.tuicool.com/11080009.png!middle","followed":false},{"id":10000025,"name":"移动应用","image":"http://ttimg1.tuicool.com/10000025.png!middle","followed":false}]
     */

    private boolean success;
    private List<TopicsBean> topics;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<TopicsBean> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicsBean> topics) {
        this.topics = topics;
    }

    public static class TopicsBean {
        /**
         * id : 11100050
         * name : Cisco IOS
         * image : http://ttimg2.tuicool.com/11100050.png!middle
         * followed : false
         */

        private int id;
        private String name;
        private String image;
        private boolean followed;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }
    }
}
