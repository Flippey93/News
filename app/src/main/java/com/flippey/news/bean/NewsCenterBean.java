package com.flippey.news.bean;

import java.util.List;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/19 20:34
 */
public class NewsCenterBean {

    /**
     * retcode : 200
     * data : [{"id":10000,"title":"鏂伴椈","type":1,"children":[{"id":10007,"title":"鍖椾含","type":1,
     * "url":"/10007/list_1.json"},{"id":10006,"title":"涓浗","type":1,"url":"/10006/list_1
     * .json"},{"id":10008,"title":"鍥介檯","type":1,"url":"/10008/list_1.json"},{"id":10010,
     * "title":"浣撹偛","type":1,"url":"/10010/list_1.json"},{"id":10091,"title":"鐢熸椿","type":1,
     * "url":"/10091/list_1.json"},{"id":10012,"title":"鏃呮父","type":1,"url":"/10012/list_1
     * .json"},{"id":10095,"title":"绉戞妧","type":1,"url":"/10095/list_1.json"},{"id":10009,
     * "title":"鍐涗簨","type":1,"url":"/10009/list_1.json"},{"id":10093,"title":"鏃跺皻","type":1,
     * "url":"/10093/list_1.json"},{"id":10011,"title":"璐㈢粡","type":1,"url":"/10011/list_1
     * .json"},{"id":10094,"title":"鑲插効","type":1,"url":"/10094/list_1.json"},{"id":10105,
     * "title":"姹借溅","type":1,"url":"/10105/list_1.json"}]},{"id":10002,"title":"涓撻","type":10,
     * "url":"/10006/list_1.json","url1":"/10007/list1_1.json"},{"id":10003,"title":"缁勫浘",
     * "type":2,"url":"/10008/list_1.json"},{"id":10004,"title":"浜掑姩","type":3,"excurl":"",
     * "dayurl":"","weekurl":""}]
     * extend : [10007,10006,10008,10014,10012,10091,10009,10010,10095]
     */

    private int retcode;
    /**
     * id : 10000
     * title : 鏂伴椈
     * type : 1
     * children : [{"id":10007,"title":"鍖椾含","type":1,"url":"/10007/list_1.json"},{"id":10006,
     * "title":"涓浗","type":1,"url":"/10006/list_1.json"},{"id":10008,"title":"鍥介檯","type":1,
     * "url":"/10008/list_1.json"},{"id":10010,"title":"浣撹偛","type":1,"url":"/10010/list_1
     * .json"},{"id":10091,"title":"鐢熸椿","type":1,"url":"/10091/list_1.json"},{"id":10012,
     * "title":"鏃呮父","type":1,"url":"/10012/list_1.json"},{"id":10095,"title":"绉戞妧","type":1,
     * "url":"/10095/list_1.json"},{"id":10009,"title":"鍐涗簨","type":1,"url":"/10009/list_1
     * .json"},{"id":10093,"title":"鏃跺皻","type":1,"url":"/10093/list_1.json"},{"id":10011,
     * "title":"璐㈢粡","type":1,"url":"/10011/list_1.json"},{"id":10094,"title":"鑲插効","type":1,
     * "url":"/10094/list_1.json"},{"id":10105,"title":"姹借溅","type":1,"url":"/10105/list_1.json"}]
     */

    private List<DataBean> data;
    private List<Integer> extend;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<Integer> getExtend() {
        return extend;
    }

    public void setExtend(List<Integer> extend) {
        this.extend = extend;
    }

    public static class DataBean {
        private int id;
        private String title;
        private int type;
        /**
         * id : 10007
         * title : 鍖椾含
         * type : 1
         * url : /10007/list_1.json
         */

        private List<ChildrenBean> children;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            private int id;
            private String title;
            private int type;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
