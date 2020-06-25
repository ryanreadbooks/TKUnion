package com.gang.tkunion.models.pojos;


import androidx.annotation.NonNull;

import java.util.List;

/**
 * 商品分类信息
 */
public class Categories {

    /**
     * success : true
     * code : 10000
     * message : 获取分类成功.
     * data : [{"id":9660,"title":"推荐"},{"id":9649,"title":"食品"}]
     */

    private boolean success;
    private int code;
    private String message;
    private List<Category> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public static class Category {
        /**
         * id : 9660
         * title : 推荐
         */

        private int id;
        private String title;

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

        @NonNull
        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "Categories{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
