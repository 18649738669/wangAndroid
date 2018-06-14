package com.example.lilong.Content.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by long on 2018/06/13.
 * 知识体系数据的model
 */

public class SystemDataModel implements Serializable{

    /**
     * errorCode : 0
     * errorMsg : 系统操作成功
     * data : [{"children":[{"children":[],"courseId":13,"id":60,"name":"Android Studio相关","order":1000,"parentChapterId":150,"visible":1},{"children":[],"courseId":13,"id":169,"name":"gradle","order":1001,"parentChapterId":150,"visible":1},{"children":[],"courseId":13,"id":269,"name":"官方发布","order":1002,"parentChapterId":150,"visible":1}],"courseId":13,"id":150,"name":"开发环境","order":1,"parentChapterId":0,"visible":1}]
     */

    private int errorCode;
    private String errorMsg;
    private List<ObjBean> data;

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

    public List<ObjBean> getData() {
        return data;
    }

    public void setData(List<ObjBean> data) {
        this.data = data;
    }

    public SystemDataModel(int errorCode, String errorMsg, List<ObjBean> data) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public static class ObjBean implements Serializable{


        /** "children":[{"children":[{"children":[],"courseId":13,"id":60,"name":"Android Studio相关","order":1000,"parentChapterId":150,"visible":1},{"children":[],"courseId":13,"id":169,"name":"gradle","order":1001,"parentChapterId":150,"visible":1},{"children":[],"courseId":13,"id":269,"name":"官方发布","order":1002,"parentChapterId":150,"visible":1}],
         *  "courseId":13,
         *  "id":150,
         *  "name":"Android Studio相关",
         *  "order":1000,
         *  "parentChapterId":150,
         *  "visible":1
         */

        private List<Children> children;
        private int courseId;
        private int id;
        private String name;
        private int order;
        private int parentChapterId;
        private int visible;

        public List<Children> getChildren() {
            return children;
        }

        public void setChildren(List<Children> children) {
            this.children = children;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

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

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public static class Children implements Serializable{

            /** "children":[],
             *  "courseId":13,
             *  "id":60,
             *  "name":"Android Studio相关",
             *  "order":1000,
             *  "parentChapterId":150,
             *  "visible":1
             */

            private List<children> childrens;
            private int courseId;
            private int id;
            private String name;
            private int order;
            private int parentChapterId;
            private int visible;

            public List<children> getChildrens() {
                return childrens;
            }

            public void setChildrens(List<children> childrens) {
                this.childrens = childrens;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

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

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getParentChapterId() {
                return parentChapterId;
            }

            public void setParentChapterId(int parentChapterId) {
                this.parentChapterId = parentChapterId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public static class children implements Serializable{

            }

        }

    }

}
