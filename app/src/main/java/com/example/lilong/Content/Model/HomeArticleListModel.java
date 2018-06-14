package com.example.lilong.Content.Model;


import java.io.Serializable;
import java.util.List;

/**
 * Created by long on 2018/06/08.
 * 首页文章列表 Model
 */

public class HomeArticleListModel implements Serializable {

    /**
     * errorCode : 0
     * errorMsg : 系统操作成功
     * data : {"curPage":2,"datas":[{"apkLink":"","author":"小编","chapterId":275,"chapterName":"常用工具","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2979,"link":"https://www.pdfpai.com/","niceDate":"2018-06-03","origin":"","projectLink":"","publishTime":1527991551000,"superChapterId":272,"superChapterName":"导航主Tab","tags":[{"name":"导航","url":"/navi#275"}],"title":"pdf派文档互转","type":0,"userId":-1,"visible":1,"zan":0}],"offset":20,"over":false,"pageCount":68,"size":20,"total":1347}
     */

    private int errorCode;
    private String errorMsg;
    private DataModel data;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataModel getData() {
        return data;
    }

    public void setData(DataModel data) {
        this.data = data;
    }

    public static class DataModel implements Serializable {

        /**
         * "curPage":2,
         * "datas":[{"apkLink":"","author":"小编","chapterId":275,"chapterName":"常用工具","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2979,"link":"https://www.pdfpai.com/","niceDate":"2018-06-03","origin":"","projectLink":"","publishTime":1527991551000,"superChapterId":272,"superChapterName":"导航主Tab","tags":[{"name":"导航","url":"/navi#275"}]
         * "offset":20,
         * "over":false,
         * "pageCount":68,
         * "size":20,
         * "total":1348
         */

        private int curPage;
        private List<ObjBean> datas;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public List<ObjBean> getDatas() {
            return datas;
        }

        public void setDatas(List<ObjBean> datas) {
            this.datas = datas;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public static class ObjBean implements Serializable{

            /**
             * "apkLink":"",
             * "author":"小编",
             * "chapterId":275,
             * "chapterName":"常用工具",
             * "collect":false,
             * "courseId":13,
             * "desc":"",
             * "envelopePic":"",
             * "fresh":false,
             * "id":2979,
             * "link":"https://www.pdfpai.com/",
             * "niceDate":"2018-06-03",
             * "origin":"",
             * "projectLink":"",
             * "publishTime":1527991551000,
             * "superChapterId":272,
             * "superChapterName":"导航主Tab",
             * "tags":[{"name":"导航","url":"/navi#275"}],
             * "title":"pdf派文档互转",
             * "type":0,
             * "userId":-1,
             * "visible":1,
             * "zan":0
             */

            private String apkLink;
            private String author;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private String projectLink;
            private double publishTime;
            private int superChapterId;
            private String superChapterName;
            private List<Tags> tags;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public double getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(double publishTime) {
                this.publishTime = publishTime;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public List<Tags> getTags() {
                return tags;
            }

            public void setTags(List<Tags> tags) {
                this.tags = tags;
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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public static class Tags implements Serializable{

                /**
                 * "name":"导航",
                 * "url":"/navi#275"
                 */
                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
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

}
