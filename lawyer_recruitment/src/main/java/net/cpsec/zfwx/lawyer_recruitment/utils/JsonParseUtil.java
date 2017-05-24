package net.cpsec.zfwx.lawyer_recruitment.utils;

import com.alibaba.fastjson.JSON;

import net.cpsec.zfwx.lawyer_recruitment.entity.TechnologyBranch;

/**
 * Created by cat9tac on 2016/4/7.
 */
public class JsonParseUtil {
    // put a jsondata string ,return a remomentBean list

   /* public static ArtAngelHomeBean jsonParseToArtAngelHomeBean(byte[] jsonData) {
        ArtAngelHomeBean ArtAngelHomeBean = new ArtAngelHomeBean();
        ArtAngelHomeBean = JSON.parseObject(jsonData, ArtAngelHomeBean.class);
        return ArtAngelHomeBean;
    }*/


    /*public static HeadDownloadBean jsonParseToHeanDownloadBean(byte[] jsonData) {
        HeadDownloadBean HeadDownloadBean = new HeadDownloadBean();
        HeadDownloadBean = JSON.parseObject(jsonData, HeadDownloadBean.class);
        return HeadDownloadBean;
    }*/


   /* public static MyRankingBean jsonParseToMyRankingBean(byte[] jsonData) {
        MyRankingBean MyRankingBean = new MyRankingBean();
        MyRankingBean = JSON.parseObject(jsonData, MyRankingBean.class);
        return MyRankingBean;
    }*/


    /*public static UserInfoBean jsonParseToUserInfoBean(byte[] jsonData) {
        UserInfoBean UserInfoBean = new UserInfoBean();
        UserInfoBean = JSON.parseObject(jsonData, UserInfoBean.class);
        return UserInfoBean;
    }*/

    public static TechnologyBranch jsonParseToTechnologyBranch(byte[] jsonData) {
        TechnologyBranch technologyBranch = new TechnologyBranch();
        technologyBranch = JSON.parseObject(jsonData, TechnologyBranch.class);
        return technologyBranch;
    }

   /* public static SearchTypeListBean jsonParseToSearchTypeListBean(byte[] jsonData) {
        SearchTypeListBean searchTypeListBean = new SearchTypeListBean();
        searchTypeListBean = JSON.parseObject(jsonData, SearchTypeListBean.class);
        return searchTypeListBean;
    }

    public static RecommentListBean jsonParseToRecommentListBean(byte[] jsonData) {
        RecommentListBean recommentListBean = new RecommentListBean();
        recommentListBean = JSON.parseObject(jsonData, RecommentListBean.class);
        return recommentListBean;
    }

    public static BannerListBean jsonParseToBannerListBean(byte[] jsonData) {
        BannerListBean bannerListBean = new BannerListBean();
        bannerListBean = JSON.parseObject(jsonData, BannerListBean.class);
        return bannerListBean;
    }

    public static ProductDetailBean jsonParseToProductDetailBean(byte[] jsonData) {
        ProductDetailBean productDetailBean = JSON.parseObject(jsonData, ProductDetailBean.class);
        return productDetailBean;
    }

    public static ProductCategoryListBean jsonParseToProductCategoryListBean(byte[] jsonData) {
        ProductCategoryListBean productCategoryListBean = new ProductCategoryListBean();
        productCategoryListBean = JSON.parseObject(jsonData, ProductCategoryListBean.class);
        return productCategoryListBean;
    }

    public static CrowFoundListBean jsonParseToCrowFoundListBean(byte[] jsonData) {
        CrowFoundListBean crowFoundListBean = new CrowFoundListBean();
        crowFoundListBean = JSON.parseObject(jsonData, CrowFoundListBean.class);
        return crowFoundListBean;
    }

    public static LoginStateBean jsonParseToLoginStateBean(byte[] jsonData) {
        LoginStateBean loginStateBean = new LoginStateBean();
        loginStateBean = JSON.parseObject(jsonData, LoginStateBean.class);
        return loginStateBean;
    }

    public static CategorySelectBean jsonParseToSelectPriceBean(byte[] jsonData) {
        CategorySelectBean categorySelectBean = new CategorySelectBean();
        categorySelectBean = JSON.parseObject(jsonData, CategorySelectBean.class);
        return categorySelectBean;
    }

    public static InfoListBean jsonParseToInfoBean(byte[] jsonData) {
        InfoListBean infoListBean = new InfoListBean();
        infoListBean = JSON.parseObject(jsonData, InfoListBean.class);
        return infoListBean;
    }

    public static TotalIncomeBean jsonParseToTotalIncomeBean(byte[] jsonData) {
        TotalIncomeBean totalIncomeBean = new TotalIncomeBean();
        totalIncomeBean = JSON.parseObject(jsonData, TotalIncomeBean.class);
        return totalIncomeBean;
    }

    public static IncomeListBean jsonParseToIncomeListBean(byte[] jsonData) {
        IncomeListBean incomeListBean = new IncomeListBean();
        incomeListBean = JSON.parseObject(jsonData, IncomeListBean.class);
        return incomeListBean;
    }

    public static MoneyListBean jsonParseToMoneyListBean(byte[] jsonData) {
        MoneyListBean moneyListBean = new MoneyListBean();
        moneyListBean = JSON.parseObject(jsonData, MoneyListBean.class);
        return moneyListBean;
    }

    public static MoneyTotalBean jsonParseToMoneyTotalBean(byte[] jsonData) {
        MoneyTotalBean moneyTotalBean = new MoneyTotalBean();
        moneyTotalBean = JSON.parseObject(jsonData, MoneyTotalBean.class);
        return moneyTotalBean;
    }

    public static CashAttentionBean jsonParseToCashAttentionBean(byte[] jsonData) {
        CashAttentionBean cashAttentionBean = new CashAttentionBean();
        cashAttentionBean = JSON.parseObject(jsonData, CashAttentionBean.class);
        return cashAttentionBean;
    }

    // 测试
    public static ContantsEntity jsonParseToWeixin(byte[] jsonData) {
        ContantsEntity constants = new ContantsEntity();
        constants = JSON.parseObject(jsonData, ContantsEntity.class);
        return constants;
    }

    public static RechargeEntity jsonParseToRecharge(byte[] jsonData) {
        RechargeEntity constants = new RechargeEntity();
        constants = JSON.parseObject(jsonData, RechargeEntity.class);
        return constants;
    }

    public static int jsonParseToUserId(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String s = jsonObject.getString("flag");
            if (jsonObject.optString("flag").equals("success")) {
                JSONObject loginUser = jsonObject.getJSONObject("loginUser");
                int userId;
                userId = loginUser.optInt("userId");
                return userId;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }*/

}
