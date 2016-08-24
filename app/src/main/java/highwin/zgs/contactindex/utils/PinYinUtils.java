package highwin.zgs.contactindex.utils;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * User: zgsHighwin
 * Email: 799174081@qq.com Or 799174081@gmail.com
 * Description:
 * Create-Time: 2016/8/24 14:12
 */
public class PinYinUtils {

    public static String getPinYin(String text) {
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(text)) {
            throw new IllegalArgumentException("输入不能为空");
        }
        char[] chars;
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); //设置显示为没有音标
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);   //设置显示为大写显示
        try {
            chars = text.toCharArray();
            for (int i = 0; i < chars.length; i++) {

                //去掉空格
                if (Character.isWhitespace(chars[i])) {
                    continue;
                }

                //在这个ascii码表范围内不是中文
                if (chars[i] > -128 && chars[i] < 127) {
                    sb.append(chars[i]);
                    continue;
                }

                String pinyin = PinyinHelper.toHanyuPinyinStringArray(chars[i], hanyuPinyinOutputFormat)[0];//由于汉字有多音字，这里不考滤多音字的情况，只取第一个音
                sb.append(pinyin);

            }
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
            sb.append(text);
        }
        return sb.toString();
    }
}
