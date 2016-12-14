package com.example.ljy.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LJY on 2016/11/26.
 */
public class ArticleContextBean {
    /**
     * success : true
     * has_next : true
     * articles : [{"id":"6zQ7Rf3","title":"如果有快到窒息的网速，你会用在哪些地方？","time":"11-26 18:05","rectime":"11-26 20:58","uts":1480165098983,"feed_title":"爱范儿","img":"http://aimg2.tuicool.com/uIvuQjJ.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"NFzIj2","title":"33岁，4年时间，身价从0到600亿，这个人很有可能超越马化腾！","time":"11-26 18:12","rectime":"11-26 19:19","uts":1480159173889,"feed_title":"新芽","img":"","abs":"","cmt":1,"st":0,"go":0},{"id":"riyqQbE","title":"是时候支持 HTTPS 了","time":"11-26 16:20","rectime":"11-26 18:58","uts":1480157927978,"feed_title":"Tim[后端技术]","img":"http://aimg2.tuicool.com/j6F3YnI.png","abs":"","cmt":0,"st":0,"go":0},{"id":"VzmArim","title":"闲扯设计模式之装饰者模式","time":"11-26 17:47","rectime":"11-26 18:29","uts":1480156173931,"feed_title":"RoadToGeek技术小屋","img":"http://aimg1.tuicool.com/zEBRJnq.png","abs":"","cmt":0,"st":0,"go":0},{"id":"fymuiy6","title":"除了看片和游戏 这家纽约创企还想用VR改造囚犯","time":"11-26 15:09","rectime":"11-26 18:20","uts":1480155633685,"feed_title":"新浪科技","img":"http://aimg0.tuicool.com/7bIfua2.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"NBBvIfJ","title":"服务器端人工智能，FPGA和GPU到底谁更强？","time":"11-26 13:12","rectime":"11-26 18:20","uts":1480155632487,"feed_title":"机器之心","img":"http://aimg2.tuicool.com/feQRNja.gif","abs":"","cmt":0,"st":0,"go":0},{"id":"7rmUZrz","title":"古巴未来或将成为中美科技巨头对决的战场","time":"11-26 09:35","rectime":"11-26 18:12","uts":1480155163989,"feed_title":"中国软件资讯网","img":"http://aimg0.tuicool.com/RRjaieN.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"2qmI3qU","title":"年底了压力山大？这篇教你和大公司签下百万大单","time":"11-26 08:24","rectime":"11-26 17:53","uts":1480153991619,"feed_title":"i黑马","img":"http://aimg2.tuicool.com/EFZF3uu.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"vuiYZrZ","title":"成都百余辆共享单车被拉回城管大院 城管：执法有误","time":"11-26 10:42","rectime":"11-26 17:28","uts":1480152490889,"feed_title":"新浪科技","img":"http://aimg1.tuicool.com/uu6rqef.jpg","abs":"","cmt":1,"st":0,"go":0},{"id":"rE3qmeu","title":"Airbnb收购小猪短租？携程留给他们的时间不多了","time":"11-26 13:57","rectime":"11-26 16:57","uts":1480150671536,"feed_title":"虎嗅网","img":"http://aimg0.tuicool.com/RFBZ7fj.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"Af2q6r","title":"周末推荐几部和大数据应用相关的影片","time":"11-26 15:12","rectime":"11-26 16:49","uts":1480150172776,"feed_title":"中国统计网","img":"http://aimg0.tuicool.com/aqeAbmf.png","abs":"","cmt":0,"st":0,"go":0},{"id":"U7B7VrE","title":"金融科技公司Stripe融资1.5亿美元 估值增至92亿美元","time":"11-26 10:14","rectime":"11-26 16:09","uts":1480147795794,"feed_title":"凤凰科技","img":"http://aimg0.tuicool.com/BNva2aR.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"emaIZnr","title":"卓有成效的程序员\u2014\u2014Mac篇","time":"11-26 15:09","rectime":"11-26 15:59","uts":1480147173705,"feed_title":"arganzheng's Weblog","img":"","abs":"","cmt":0,"st":0,"go":0},{"id":"Yn2QBbe","title":"谷歌DeepMind部门招聘人工智能专家 预防AI毁灭人类","time":"11-26 07:24","rectime":"11-26 15:43","uts":1480146215918,"feed_title":"腾讯科技","img":"http://aimg1.tuicool.com/neueAzZ.png","abs":"","cmt":0,"st":0,"go":0},{"id":"Yr6b2i7","title":"Redis 和 I/O 多路复用","time":"11-26 14:08","rectime":"11-26 15:09","uts":1480144173037,"feed_title":"DeltaX","img":"http://aimg1.tuicool.com/VnUBJbR.png","abs":"","cmt":0,"st":0,"go":0},{"id":"zUvURfY","title":"看似普通的眼镜，但它其实拥有超乎想象的功能","time":"11-26 12:43","rectime":"11-26 14:19","uts":1480141173968,"feed_title":"设计癖","img":"http://aimg2.tuicool.com/QN3AVj7.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"VrU7bqU","title":"怕电池爆炸，\u201c伤不起\u201d的LG将在下一代旗舰手机G6上使用可拆卸电池设计","time":"11-26 13:25","rectime":"11-26 13:27","uts":1480138063066,"feed_title":"36氪","img":"http://aimg1.tuicool.com/yIr6Ffa.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"aEnaeaq","title":"八成印度智能手机用户玩游戏但是不交钱，怎么破？","time":"11-25 22:05","rectime":"11-26 12:57","uts":1480136263179,"feed_title":"竺道","img":"http://aimg0.tuicool.com/u63q2yZ.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"i6BVvua","title":"越用越慢的安卓系统 还能够自我救赎吗","time":"11-26 08:27","rectime":"11-26 12:42","uts":1480135353950,"feed_title":"新浪手机","img":"http://aimg1.tuicool.com/ZRzmaqU.jpg","abs":"","cmt":1,"st":0,"go":0},{"id":"rUVZnuz","title":"年薪百万、身价上亿的年轻人是怎样的？","time":"11-26 10:51","rectime":"11-26 12:31","uts":1480134685058,"feed_title":"新芽","img":"http://aimg0.tuicool.com/uEbUjaE.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"eay6RjR","title":"一文带你看懂：2000多家网贷平台，都是在拼什么呢？","time":"11-26 07:12","rectime":"11-26 12:28","uts":1480134485742,"feed_title":"虎嗅网","img":"http://aimg2.tuicool.com/eeyiQbq.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"Fzym6j3","title":"\u201c听禅去\u201d刷屏企业家朋友圈，禅修旅行成潮流？","time":"11-26 10:17","rectime":"11-26 12:09","uts":1480133394067,"feed_title":"百度百家","img":"http://aimg0.tuicool.com/JruENny.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"Av6bq2i","title":"是时候，聊一聊交互设计知识体系了！","time":"11-26 06:57","rectime":"11-26 11:49","uts":1480132174860,"feed_title":"产品100","img":"http://aimg1.tuicool.com/A3IJjyI.png","abs":"","cmt":0,"st":0,"go":0},{"id":"fI3UFvE","title":"\u201cC轮死\u201d魔咒？我们盘点了已获C轮融资的互金企业，看他们过得怎么样","time":"11-26 06:57","rectime":"11-26 11:43","uts":1480131834187,"feed_title":"虎嗅网","img":"http://aimg2.tuicool.com/AZZFzy6.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"VfQRzua","title":"今年美国\u201c黑五\u201d在线消费将达30亿美元 创新纪录","time":"11-26 10:12","rectime":"11-26 11:37","uts":1480131443822,"feed_title":"凤凰科技","img":"http://aimg1.tuicool.com/Jv2yIz2.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"aQr6nqJ","title":"看起来像牙科诊所设备一样的办公椅，是趋势吗？","time":"11-26 09:15","rectime":"11-26 10:58","uts":1480129088218,"feed_title":"网易科技","img":"http://aimg1.tuicool.com/rIZnqif.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"iMF7ji","title":"段子手薛之谦刷爆了朋友圈？那不过是一场市场公关们的自嗨","time":"11-26 02:51","rectime":"11-26 10:36","uts":1480127771037,"feed_title":"PingWest","img":"http://aimg0.tuicool.com/emEf2yi.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"UJNnii7","title":"58同城获腾讯连续三次增持 共118.81万股","time":"11-26 09:28","rectime":"11-26 10:12","uts":1480126371866,"feed_title":"亿邦动力网","img":"","abs":"","cmt":0,"st":0,"go":0},{"id":"YjUBre","title":"创业一年，一些技术上的总结","time":"11-25 12:13","rectime":"11-26 10:11","uts":1480126302958,"feed_title":"票牛技术博客","img":"http://aimg2.tuicool.com/YbIf6nm.jpg","abs":"","cmt":0,"st":0,"go":0},{"id":"BfqURfj","title":"滴滴专车司机醉驾致一死一伤八辆车受损 被判无期","time":"11-26 04:44","rectime":"11-26 10:09","uts":1480126182774,"feed_title":"新浪科技","img":"","abs":"","cmt":0,"st":0,"go":0}]
     * lang : 1
     * pn : 0
     * cats : {"id":0,"desc":"热门","seo":"热门文章"}
     * cid : 0
     */

    private boolean success;
    private boolean has_next;
    private int lang;
    private int pn;
    private CatsBean cats;
    private int cid;
    private List<ArticlesBean> articles;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isHas_next() {
        return has_next;
    }

    public void setHas_next(boolean has_next) {
        this.has_next = has_next;
    }

    public int getLang() {
        return lang;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public CatsBean getCats() {
        return cats;
    }

    public void setCats(CatsBean cats) {
        this.cats = cats;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public static class CatsBean {
        /**
         * id : 0
         * desc : 热门
         * seo : 热门文章
         */

        private int id;
        private String desc;
        private String seo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getSeo() {
            return seo;
        }

        public void setSeo(String seo) {
            this.seo = seo;
        }
    }
    @Table(name="like")
    public static class ArticlesBean  extends Model implements Serializable,Cloneable{
        private static final long serialVersionUID = -881433050769321127L;
        /**
         * id : 6zQ7Rf3
         * title : 如果有快到窒息的网速，你会用在哪些地方？
         * time : 11-26 18:05
         * rectime : 11-26 20:58
         * uts : 1480165098983
         * feed_title : 爱范儿
         * img : http://aimg2.tuicool.com/uIvuQjJ.jpg
         * abs :
         * cmt : 0
         * st : 0
         * go : 0
         */
        @Column(name="articleurl")
        private String id;
        @Column
        private String title;
        @Column
        private String time;
        @Column
        private String rectime;
        @Column
        private long uts;
        @Column
        @SerializedName("feed_title")
        private String feed_title;
        @Column
        private String img;
        @Column
        private String abs;
        @Column
        private int cmt;
        @Column
        private int st;
        @Column
        private int go;

        @Override
        public ArticlesBean clone() {
            try {
                return (ArticlesBean) super.clone();
            } catch (CloneNotSupportedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }


        public String getArticleUrl() {
            return id;
        }

        public void setArticleUrl(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getRectime() {
            return rectime;
        }

        public void setRectime(String rectime) {
            this.rectime = rectime;
        }

        public long getUts() {
            return uts;
        }

        public void setUts(long uts) {
            this.uts = uts;
        }

        public String getFeed_title() {
            return feed_title;
        }

        public void setFeed_title(String feed_title) {
            this.feed_title = feed_title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getAbs() {
            return abs;
        }

        public void setAbs(String abs) {
            this.abs = abs;
        }

        public int getCmt() {
            return cmt;
        }

        public void setCmt(int cmt) {
            this.cmt = cmt;
        }

        public int getSt() {
            return st;
        }

        public void setSt(int st) {
            this.st = st;
        }

        public int getGo() {
            return go;
        }

        public void setGo(int go) {
            this.go = go;
        }
    }
}
