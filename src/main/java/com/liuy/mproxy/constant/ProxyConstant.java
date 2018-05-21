package com.liuy.mproxy.constant;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/25 11:38
 */
public class ProxyConstant {
    /**
     * 推荐redis key
     */
    public enum RecommendKey{
        RECOMMEND_ARTICLE("everyDayArticleTheEnglishApp","每日推荐文章"),
        RECOMMEND_WORD("everyDayWordTheEnglishApp","每日推荐单词"),
        RESULT_WORD("userWordCeShiResult","单词测试结果"),
        DYNAMIC_REMEN("dynamicRemenPaihang","动态点赞排行榜");
        private String key;
        private String value;
        public String getKey() {
            return key;
        }
        public String getValue() {
            return value;
        }
        RecommendKey(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 动态分组枚举
     */
    public enum DynamicGrade{
        DYNAMIC_GRADE_ALL(0,"全部"),
        DYNAMIC_GRADE_GUANZHU(1,"关注"),
        DYNAMIC_GRADE_REMEN(2,"热门");

        private Integer key;
        private String value;
        public Integer getKey() {
            return key;
        }
        public String getValue() {
            return value;
        }
        DynamicGrade(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
