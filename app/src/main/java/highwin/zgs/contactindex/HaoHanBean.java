package highwin.zgs.contactindex;

import highwin.zgs.contactindex.utils.PinYinUtils;

/**
 * User: zgsHighwin
 * Email: 799174081@qq.com Or 799174081@gmail.com
 * Description:
 * Create-Time: 2016/8/24 15:05
 */
public class HaoHanBean implements Comparable<HaoHanBean> {

    public String name;
    public String pinyin;

    public HaoHanBean(String name) {
        this.name = name;
        this.pinyin = PinYinUtils.getPinYin(name).charAt(0) + "";
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(HaoHanBean another) {
        return this.pinyin.compareTo(another.getPinyin());
    }
}
