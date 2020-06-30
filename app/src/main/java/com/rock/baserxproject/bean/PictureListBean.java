package com.rock.baserxproject.bean;

import com.rock.basemodel.http.retrofit.rx.BaseReponse;

import java.util.List;

/**
 * @author: ruan
 * @date: 2020/5/15
 */
public class PictureListBean  {


    /**
     * category : Girl
     * data : [{"_id":"5e5149256e7524f833c3f7a4","author":"鸢媛","category":"Girl","createdAt":"2020-02-27 08:00:00","desc":"原是今生今世已惘然， 山河岁月空惆怅， 而我，终将是要等着你的。","images":["http://gank.io/images/02eb8ca3297f4931ab64b7ebd7b5b89c"],"likeCounts":3,"publishedAt":"2020-02-27 08:00:00","stars":1,"title":"第8期","type":"Girl","url":"http://gank.io/images/02eb8ca3297f4931ab64b7ebd7b5b89c","views":1321},{"_id":"5e5944fe2bae9a858b571142","author":"鸢媛","category":"Girl","createdAt":"2020-03-16 08:00:00","desc":"翻着我们的照片，想念若隐若现，去年的秋天，我们笑得很甜。 \u200b\u200b\u200b\u200b","images":["http://gank.io/images/e56da642238a43c4971f12d4e3395f30"],"likeCounts":2,"publishedAt":"2020-03-16 08:00:00","stars":1,"title":"第26期","type":"Girl","url":"http://gank.io/images/e56da642238a43c4971f12d4e3395f30","views":883},{"_id":"5e529d2a2adf62a2ba021cfb","author":"鸢媛","category":"Girl","createdAt":"2020-03-01 08:00:00","desc":"念念不忘的东西，一开始都美好的不像话。 \u200b\u200b\u200b\u200b","images":["http://gank.io/images/3e4423173d0a4c5e8447c0335b4458fc"],"likeCounts":1,"publishedAt":"2020-03-01 08:00:00","stars":4,"title":"第11期","type":"Girl","url":"http://gank.io/images/3e4423173d0a4c5e8447c0335b4458fc","views":633},{"_id":"5e52aa6c8ad0cb82d1976160","author":"鸢媛","category":"Girl","createdAt":"2020-03-05 08:00:00","desc":"我喜欢你，所以希望你被簇拥包围，所以你走的路要繁花盛开，要人声鼎沸。 \u200b\u200b\u200b","images":["http://gank.io/images/1af9d69bc60242d7aa2e53125a4586ad"],"likeCounts":1,"publishedAt":"2020-03-05 08:00:00","stars":1,"title":"第15期","type":"Girl","url":"http://gank.io/images/1af9d69bc60242d7aa2e53125a4586ad","views":370},{"_id":"5e5533b2efd6f28e2554f494","author":"鸢媛","category":"Girl","createdAt":"2020-03-14 08:00:00","desc":"不要停止奔跑，不要回顾来路，来路无可眷恋，值得期待的只有前方。 \u200b\u200b\u200b\u200b","images":["http://gank.io/images/50e3dd62dd9e4b0db957b3c5e296d6b1"],"likeCounts":1,"publishedAt":"2020-03-14 08:00:00","stars":1,"title":"第24期","type":"Girl","url":"http://gank.io/images/50e3dd62dd9e4b0db957b3c5e296d6b1","views":397},{"_id":"5e72320965c5e56de1a75587","author":"鸢媛","category":"Girl","createdAt":"2020-03-19 08:00:00","desc":"闲时与你立黄昏，灶前笑问粥可温。","images":["http://gank.io/images/5ba77f3415b44f6c843af5e149443f94"],"likeCounts":1,"publishedAt":"2020-03-19 08:00:00","stars":1,"title":"第29期","type":"Girl","url":"http://gank.io/images/5ba77f3415b44f6c843af5e149443f94","views":572},{"_id":"5e7773b1d4bf7c272db642d4","author":"鸢媛","category":"Girl","createdAt":"2020-03-22 08:00:00","desc":"如花美眷，似水流年，回得了过去，回不了当初。","images":["http://gank.io/images/882afc997ad84f8ab2a313f6ce0f3522"],"likeCounts":1,"publishedAt":"2020-03-22 08:00:00","stars":1,"title":"第32期","type":"Girl","url":"http://gank.io/images/882afc997ad84f8ab2a313f6ce0f3522","views":1750},{"_id":"5e777432b8ea09cade05263f","author":"鸢媛","category":"Girl","createdAt":"2020-03-25 08:00:00","desc":"这世界总有人在笨拙地爱着你，想把全部的温柔都给你。","images":["http://gank.io/images/624ade89f93f421b8d4e8fafd86b1d8d"],"likeCounts":1,"publishedAt":"2020-03-25 08:00:00","stars":1,"title":"第35期","type":"Girl","url":"http://gank.io/images/624ade89f93f421b8d4e8fafd86b1d8d","views":970},{"_id":"5e8200918402c5364e3ac153","author":"鸢媛","category":"Girl","createdAt":"2020-04-02 08:00:00","desc":"时光太瘦，指缝太宽，\n不经意的一瞥，已隔经年。","images":["http://gank.io/images/9770422c45294684af50f725049d7c07"],"likeCounts":1,"publishedAt":"2020-04-02 08:00:00","stars":1,"title":"第43期","type":"Girl","url":"http://gank.io/images/9770422c45294684af50f725049d7c07","views":267},{"_id":"5e8c808e31ec89ebfc601f0f","author":"鸢媛","category":"Girl","createdAt":"2020-04-09 08:00:00","desc":"希望你以后喜欢的人，\n不会让你哭不会让你受委屈\n不会不理你更不会放弃你\n最好一直陪着你，保护你\n最重要的是深深的无可救药的喜欢你。","images":["http://gank.io/images/e831e004436f4fffb657a77aef48b9ca"],"likeCounts":1,"publishedAt":"2020-04-09 08:00:00","stars":1,"title":"第50期","type":"Girl","url":"http://gank.io/images/e831e004436f4fffb657a77aef48b9ca","views":152},{"_id":"5e4e092004a2d596d4b67d0c","author":"鸢媛","category":"Girl","createdAt":"2020-02-20 08:00:00","desc":"念念不忘，必有回响。","images":["http://gank.io/images/31f92f7845f34f05bc10779a468c3c13"],"likeCounts":0,"publishedAt":"2020-02-20 08:00:00","stars":6,"title":"第1期","type":"Girl","url":"http://gank.io/images/31f92f7845f34f05bc10779a468c3c13","views":1015}]
     * hot : likes
     * status : 100
     */

    private String category;
    private String hot;
    private int status;
    private List<DataBean> data;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * _id : 5e5149256e7524f833c3f7a4
         * author : 鸢媛
         * category : Girl
         * createdAt : 2020-02-27 08:00:00
         * desc : 原是今生今世已惘然， 山河岁月空惆怅， 而我，终将是要等着你的。
         * images : ["http://gank.io/images/02eb8ca3297f4931ab64b7ebd7b5b89c"]
         * likeCounts : 3
         * publishedAt : 2020-02-27 08:00:00
         * stars : 1
         * title : 第8期
         * type : Girl
         * url : http://gank.io/images/02eb8ca3297f4931ab64b7ebd7b5b89c
         * views : 1321
         */

        private String _id;
        private String author;
        private String category;
        private String createdAt;
        private String desc;
        private int likeCounts;
        private String publishedAt;
        private int stars;
        private String title;
        private String type;
        private String url;
        private int views;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getLikeCounts() {
            return likeCounts;
        }

        public void setLikeCounts(int likeCounts) {
            this.likeCounts = likeCounts;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
