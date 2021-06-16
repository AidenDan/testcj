package com.aiden.cj.constant;

import java.util.Arrays;
import java.util.Optional;

/**
 * 奖项枚举, 只有8条数据就没有放在数据库里了
 */
public enum Coupons {
    TWENTY_EIGHT(1,"满28可用",3),
    THIRTY_EIGHT(2, "满38可用",10),
    EIGHTY_EIGHT(3, "满88可用", 15),
    FIFTY_EIGHT(4, "满58可用", 10),
    THIRTY_EIGHT_FOUR(5, "满38可用", 4),
    ONE_TWO_EIGHT(6, "满128可用", 25),
    ONE_SIX_EIGHT(7, "满168可用", 70),
    SIXTY_EIGHT(8, "满68可用", 20);

    private final int index;

    private final String desc;

    private final int quota;

    Coupons(int index, String desc, int quota) {
        this.index = index;
        this.desc = desc;
        this.quota = quota;
    }

    public int getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }

    public int getQuota() {
        return quota;
    }

    public static String getDesc(int index) {
        Coupons[] enums = values();
        Optional<Coupons> anEnum = Arrays.stream(enums).filter(typeEnum -> typeEnum.getIndex() == index).findFirst();
        if(anEnum.isPresent()){
            return anEnum.get().getDesc();
        }
        return null;
    }

    public static int getQuota(int index) {
        Coupons[] enums = values();
        Optional<Coupons> anEnum = Arrays.stream(enums).filter(typeEnum -> typeEnum.getIndex() == index).findFirst();
        if(anEnum.isPresent()){
            return anEnum.get().getQuota();
        }
        return 0;
    }

}
