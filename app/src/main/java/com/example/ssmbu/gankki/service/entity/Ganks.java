package com.example.ssmbu.gankki.service.entity;

import java.util.ArrayList;
import java.util.List;

public class Ganks implements GankItemsInterface{
    @Override
    public List<GankItem> getGankItems() {
        List<GankItem> gankItems=new ArrayList<>();
        if(!isError()){
            for(ResultsBean resultsBean:results){
                    GankItem gankItem=new GankItem(resultsBean._id,resultsBean.desc,resultsBean.publishedAt,resultsBean.type,resultsBean.url,resultsBean.who);
                    gankItems.add(gankItem);

            }
        }
        return gankItems;
    }

    /**
     * error : false
     * results : [{"_id":"5b21f019421aa92a57e29e96","createdAt":"2018-06-14T12:33:29.592Z","desc":"受Android FAB的启发的动画隐藏/显示视图。","images":["http://img.gank.io/228dcb82-b6cb-40bc-ac14-c2d909105176","http://img.gank.io/97a2cf03-6aee-41c2-9179-72534d53de33","http://img.gank.io/c6fc42e8-d13c-4b42-9771-74b7d46d69d9"],"publishedAt":"2018-06-15T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/3llomi/Hidely","used":true,"who":"lijinshanmx"},{"_id":"5b21f08f421aa92a5f2a35f7","createdAt":"2018-06-14T12:35:27.742Z","desc":"Flutter实现的CardSwipe效果。","images":["http://img.gank.io/746d8620-cf0d-455a-b1a0-0370d62bb74b"],"publishedAt":"2018-06-15T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/geekruchika/FlutterCardSwipe","used":true,"who":"lijinshanmx"},{"_id":"5b2257e7421aa92a5abcd368","createdAt":"2018-06-14T19:56:23.920Z","desc":"万能Adapter for RecyclerView，使用与单ItemView和多种ItemView","images":["http://img.gank.io/aeca4ba2-ed84-4a5f-85f9-fa03f6eba057"],"publishedAt":"2018-06-15T00:00:00.0Z","source":"web","type":"Android","url":"https://github.com/jdqm/RvAdapter","used":true,"who":"jdqm"},{"_id":"5b2269a6421aa92a5f2a35f9","createdAt":"2018-06-14T21:12:06.463Z","desc":"2018-06-15","publishedAt":"2018-06-15T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1fsb0lh7vl0j30go0ligni.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b228556421aa92a5c429ac9","createdAt":"2018-06-14T23:10:14.694Z","desc":"Apple 团队开源的基于 Google Protocol Buffer (\"protobuf\") 序列化技术的运行时库","publishedAt":"2018-06-15T00:00:00.0Z","source":"web","type":"iOS","url":"https://github.com/apple/swift-protobuf","used":true,"who":"皮特尔"},{"_id":"5b228a3f421aa92a5f2a35fb","createdAt":"2018-06-14T23:31:11.566Z","desc":"一个可拖动、好看的歌词View","publishedAt":"2018-06-15T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/wangchenyan/LrcView","used":true,"who":"艾米"},{"_id":"5b231142421aa91798db0b02","createdAt":"2018-06-15T09:07:14.576Z","desc":"恭喜马化腾喜提 fuckqq.com","publishedAt":"2018-06-15T00:00:00.0Z","source":"chrome","type":"瞎推荐","url":"https://zhuanlan.zhihu.com/p/36263164","used":true,"who":"ChengZhen"},{"_id":"5b2311a8421aa92a5abcd36c","createdAt":"2018-06-15T11:48:18.914Z","desc":"世界杯直播。","images":["http://img.gank.io/955e7f7b-b39e-4f75-baa3-1c7354149f48"],"publishedAt":"2018-06-15T00:00:00.0Z","source":"chrome","type":"前端","url":"https://github.com/closetao/TVLive","used":true,"who":"ChengZhen"},{"_id":"5b231742421aa92a5abcd36d","createdAt":"2018-06-15T09:32:50.567Z","desc":"防止密码被硬编码到代码中","publishedAt":"2018-06-15T00:00:00.0Z","source":"chrome","type":"拓展资源","url":"https://github.com/Yelp/detect-secrets","used":true,"who":"ChengZhen"},{"_id":"5b233551421aa92a5abcd36e","createdAt":"2018-06-15T11:41:05.571Z","desc":"Android手机端崩溃日志记录。","images":["http://img.gank.io/0add1cda-d44b-4430-aa74-a8074bd23613"],"publishedAt":"2018-06-15T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/simplepeng/SpiderMan","used":true,"who":"lijinshanmx"},{"_id":"5b233608421aa92a5f2a3600","createdAt":"2018-06-15T11:44:08.358Z","desc":"我们一起来还原微信，探索微信的设计，以及使用到的技术手段等。","images":["http://img.gank.io/47405a3a-6351-4807-8ede-b630c8a5e609","http://img.gank.io/1f379738-1793-4a8a-ac0f-d2b00d4448fe","http://img.gank.io/80b62acd-accd-402b-917f-776b658a32c7"],"publishedAt":"2018-06-15T00:00:00.0Z","source":"chrome","type":"拓展资源","url":"https://github.com/lefex/iWeChat","used":true,"who":"lijinshanmx"},{"_id":"5b233674421aa92a5abcd370","createdAt":"2018-06-15T11:45:56.948Z","desc":"Android App 冷启动优化方案。","images":["http://img.gank.io/fc6f2afd-3aa8-4517-9574-bbe4bd8aa87b","http://img.gank.io/be784163-bb24-46fc-8438-081c138bbe28","http://img.gank.io/8b59a310-2e00-4193-8d96-839384f9b2fa"],"publishedAt":"2018-06-15T00:00:00.0Z","source":"chrome","type":"拓展资源","url":"https://github.com/DanluTeam/ColdStart","used":true,"who":"lijinshanmx"},{"_id":"5b2337ce421aa92a5f2a3601","createdAt":"2018-06-15T11:51:42.57Z","desc":"家里有个贤惠的媳妇，都是手机培养出来的，哎😔。。。。","publishedAt":"2018-06-15T00:00:00.0Z","source":"web","type":"休息视频","url":"https://www.iesdouyin.com/share/video/6566526360171318542/?region=CN&mid=6502768799983504141&titleType=title&utm_source=copy_link&utm_campaign=client_share&utm_medium=android&app=aweme&iid=33383525082&timestamp=1529034645","used":true,"who":"lijinshanmx"},{"_id":"5b23387f421aa92a5c429ace","createdAt":"2018-06-15T11:54:39.736Z","desc":"React应用程序管理器：创建并运行React（和其他）应用程序 - 不需要命令行或生成设置。","images":["http://img.gank.io/5983eae4-e9b1-409a-8bcb-7d9976d09371"],"publishedAt":"2018-06-15T00:00:00.0Z","source":"chrome","type":"前端","url":"https://github.com/jxnblk/ram","used":true,"who":"lijinshanmx"},{"_id":"5b1fec10421aa9793930bf99","createdAt":"2018-06-12T23:51:44.815Z","desc":"2018-06-13","publishedAt":"2018-06-14T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1fs8tym1e8ej30j60ouwhz.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b2087e7421aa97934d42ff7","createdAt":"2018-06-14T12:20:33.394Z","desc":"使用Bootstrap 4构建的免费和开放源代码管理仪表板模板。","images":["http://img.gank.io/18767dac-d782-4fbb-8fa0-c6562d5c5e2c","http://img.gank.io/6d5590b8-bf85-43db-b456-be2d3856103c"],"publishedAt":"2018-06-14T00:00:00.0Z","source":"chrome","type":"前端","url":"https://github.com/flatlogic/sing-app","used":true,"who":"lijinshanmx"},{"_id":"5b208876421aa979371cf5fa","createdAt":"2018-06-13T10:59:02.236Z","desc":"一个React-Native入门套件，包含React-Navigation + Code Push + Onesignal + Sentry + Google Signin。","publishedAt":"2018-06-14T00:00:00.0Z","source":"chrome","type":"瞎推荐","url":"https://github.com/teallabs/react-native-init","used":true,"who":"lijinshanmx"},{"_id":"5b20a86b421aa97934d42ffd","createdAt":"2018-06-14T12:22:32.573Z","desc":"android图片涂鸦，具有设置画笔，撤销，缩放移动等功能。","images":["http://img.gank.io/e666e3c0-3606-4107-a515-4e3a96a6cfdd","http://img.gank.io/648431b0-10f3-40da-bfc8-dacf2eb34ace","http://img.gank.io/2086cfd0-39f9-4ae3-b32e-5c90e3fefa2a"],"publishedAt":"2018-06-14T00:00:00.0Z","source":"web","type":"Android","url":"https://github.com/1993hzw/Graffiti","used":true,"who":"joker"},{"_id":"5b20a893421aa97934d42ffe","createdAt":"2018-06-14T12:23:54.255Z","desc":"Android多媒体选择器支持图片和视频。","images":["http://img.gank.io/c781326b-b66a-4a56-90b3-1280ae460e66","http://img.gank.io/7631e3f0-529e-4f52-b56c-63f3f187fa15","http://img.gank.io/14c71d71-146e-41bf-a7e1-f2c3bb1da55f","http://img.gank.io/d6ffc1e5-9d9d-48ef-897c-7ac1c9d07500"],"publishedAt":"2018-06-14T00:00:00.0Z","source":"web","type":"Android","url":"https://github.com/JakePrim/PrimFilePicker","used":true,"who":"joker"},{"_id":"5b20c01e421aa979314bc73e","createdAt":"2018-06-13T14:56:30.940Z","desc":"使用 Flutter 开发的P站浏览应用","images":["http://img.gank.io/2c1aef80-4478-4f20-9e70-afd1427c42d6","http://img.gank.io/5a811aa2-320c-408e-87aa-a1b25e439918","http://img.gank.io/7ab14330-c7a5-46c1-91f6-9d2c3186fe28"],"publishedAt":"2018-06-14T00:00:00.0Z","source":"web","type":"App","url":"https://github.com/SamuelGjk/pxnyan","used":true,"who":"SamuelGjk"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean{

        /**
         * _id : 5b21f019421aa92a57e29e96
         * createdAt : 2018-06-14T12:33:29.592Z
         * desc : 受Android FAB的启发的动画隐藏/显示视图。
         * images : ["http://img.gank.io/228dcb82-b6cb-40bc-ac14-c2d909105176","http://img.gank.io/97a2cf03-6aee-41c2-9179-72534d53de33","http://img.gank.io/c6fc42e8-d13c-4b42-9771-74b7d46d69d9"]
         * publishedAt : 2018-06-15T00:00:00.0Z
         * source : chrome
         * type : Android
         * url : https://github.com/3llomi/Hidely
         * used : true
         * who : lijinshanmx
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
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

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
