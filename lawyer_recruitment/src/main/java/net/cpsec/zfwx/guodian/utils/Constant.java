package net.cpsec.zfwx.guodian.utils;

public class Constant {
    // 全局的调试标识
    public static final String DEBUGGING_FLAG = "DEBUGGING_FLAG";
    // 老名字
    public static final String OLD_NAME = "OLD_NAME";
    // 新名字
    public static final String NEW_NAME = "NEW_NAME";
    // 老学校
    public static final String OLD_SCHOOL = "OLD_SCHOOL";
    // 新学校
    public static final String NEW_SCHOOL = "NEW_SCHOOL";
    // 老专业
    public static final String OLD_SPECIALTY = "OLD_SPECIALTY";
    // 新专业
    public static final String NEW_SPECIALTY = "NEW_SPECIALTY";

    // Boss端老名字
    public static final String BOSS_OLD_NAME = "BOSS_OLD_NAME";
    // Boss端新名字
    public static final String BOSS_NEW_NAME = "BOSS_NEW_NAME";
    //Boss端当前公司职位
    public static final String BOSS_CURRENT_POSITION = "BOSS_CURRENT_POSITION";
    //Boss端邮箱
    public static final String BOSS_EMAIL = "BOSS_EMAIL";
    //Boss端公司
    public static final String BOSS_COMPANY = "BOSS_COMPANY";
    //发布职位信息
    //发布职位的公司简称
    public static final String RELEASE_COMPANY_NAME="RELEASE_COMPANY_NAME";
    //发布职位的名称
    public static final String RELEASE_POSITION_NAME = "RELEASE_POSITION";
    //职位描述
    public static final String RELEASE_POSITION_DESCRIBE = "RELEASE_POSITION_DESCRIBE";

    // 手机号验证状态标识 成功
    public static final int PHONE_SUCCEED = 0;
    // 手机号验证状态标识 手机号长度不对
    public static final int PHONE_LENGTH_ERROR = 1;
    // 手机号验证状态标识 手机号格式不对
    public static final int PHONE_FORMAT_ERROR = 2;
    // 手机号验证状态标识 手机号为空
    public static final int PHONE_NULL = 3;
    // 密码验证状态标识 成功
    public static final int PASSWORD_VERIFY_SUCCEED = 1;
    // 密码验证状态标识 两次密码不一样
    public static final int PASSWORD_VERIFY_NOT_SAME = 2;
    // 密码验证状态标识 密码长度不够
    public static final int PASSWORD_VERIFY_SHORT = 3;
    // 密码验证状态标识 密码为空
    public static final int PASSWORD_VERIFY_NULL = 4;
    // 姓名标识
    public static final int NAME_REQUEST_CODE = 1;
    // 学历标识
    public static final int EDUCATION_BACKGROUND = 0;
    // 毕业时间标识
    public static final int GRADUATE_TIME = 1;
    // 学校标识
    public static final int FOR_SCHOOL = 0;
    // 专业标识
    public static final int FOR_SPECIALTY = 1;
    //当前职位标识
    public static final int CURRENT_POSITION_CODE = 0;
    //邮箱标识
    public static final int EMAIL_CODE = 2;
    //公司标识
    public static final int COMPANY_CODE = 3;
    //发布职位信息
    public static final int RELEASE_COMPANY_CODE = 0;

    //发布职位类型
    public static final int POSITION_TYPE_CODE = 1;
    //发布职位名称
    public static final int POSITION_NAME_CODE = 2;
    //职位描述
    public static final int POSITION_DESCRIBE_CODE=3;

    // 登录阿里百川成功后发出的广播：
    public static final String ON_LOGIN_ALI_SUCCESS = "com.feirui.feiyunbangong.login.ali.success";

}
