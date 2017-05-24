package net.cpsec.zfwx.lawyer_recruitment.utils;

import android.text.TextUtils;

public class VerifyPhoneNumber {
    /**
     * 判断一个字符串的位数
     *
     * @param str
     * @param length
     * @return
     */
    public static int isMatchLength(String str, int length) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        String telRegex = "[1]\\d{1}\\d{9}";// "[1]"代表第1位为数字1，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(str)) {
            return Constant.PHONE_NULL;
        } else if (str.length() != length) {
            return Constant.PHONE_LENGTH_ERROR;
        } else if (!str.matches(telRegex)) {
            return Constant.PHONE_FORMAT_ERROR;
        } else {
            return Constant.PHONE_SUCCEED;
        }
    }

    /**
     * 验证手机格式
     */
//    public static int isMobileNO(String mobileNums) {
////        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
//        String telRegex = "[1]\\d{1}\\d{9}";// "[1]"代表第1位为数字1，"\\d{9}"代表后面是可以是0～9的数字，有9位。
//        if (TextUtils.isEmpty(mobileNums))
//            return 0;
//        else if (mobileNums.matches(telRegex)) {
//            return 1;
//        } else {
//            return 2;
//        }
//    }
    public static int isSame(String password, String againPassword) {
        if (!password.isEmpty() && !againPassword.isEmpty() && password.equals(againPassword)) {
            return Constant.PASSWORD_VERIFY_SUCCEED;
        } else if (!password.equals(againPassword)) {
            return Constant.PASSWORD_VERIFY_NOT_SAME;
        } else if (password.length() < 6) {
            return Constant.PASSWORD_VERIFY_SHORT;
        } else {
            return Constant.PASSWORD_VERIFY_NULL;
        }
    }

}
