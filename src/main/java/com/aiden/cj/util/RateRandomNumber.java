package com.aiden.cj.util;

import org.apache.commons.lang3.RandomUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2021-5-22 16:45:55
 */
public class RateRandomNumber {
    /**
     * 产生随机数
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机结果
     */
    public static double produceRandomNumber(double min, double max) {
        return RandomUtils.nextDouble(min, max); //[min,max]
    }

    /**
     * 按比率产生随机数
     *
     * @param min       最小值
     * @param max       最大值
     * @param separates 分割值（中间插入数）
     * @param percents  每段数值的占比（几率）
     * @return 按比率随机结果
     */
    public static double produceRateRandomNumber(double min, double max, List<Double> separates, List<Double> percents) {
        if (min > max) {
            throw new IllegalArgumentException("min值必须小于max值");
        }
        if (separates == null || percents == null || separates.size() == 0) {
            return produceRandomNumber(min, max);
        }
        if (separates.size() + 1 != percents.size()) {
            throw new IllegalArgumentException("分割数字的个数加1必须等于百分比个数");
        }
        double totalPercent = 0D;
        for (Double p : percents) {
            if (p < 0D || p > 100D) {
                throw new IllegalArgumentException("百分比必须在[0,100]之间");
            }
            totalPercent += p;
        }
        if (totalPercent != 100D) {
            throw new IllegalArgumentException("百分比之和必须为100");
        }
        for (double s : separates) {
            if (s <= min || s >= max) {
                throw new IllegalArgumentException("分割数值必须在(min,max)之间");
            }
        }
        int rangeCount = separates.size() + 1; //例如：7个插值，可以将一个数值范围分割成8段
        //构造分割的n段范围
        List<Range> ranges = new ArrayList<Range>();
        double scopeMax = 0D;
        for (int i = 0; i < rangeCount; i++) {
            Range range = new Range();
            range.min = (i == 0 ? min : separates.get(i - 1));
            range.max = (i == rangeCount - 1 ? max : separates.get(i));
            range.percent = percents.get(i);

            //片段占比，转换为[1,100]区间的数字
            range.percentScopeMin = scopeMax + 1D;
            range.percentScopeMax = range.percentScopeMin + (range.percent - 1D);
            scopeMax = range.percentScopeMax;

            ranges.add(range);
        }
        //结果赋初值
        double r = min;
        double randomInt = RandomUtils.nextDouble(0D, 100D); //[1,100]
        for (int i = 0; i < ranges.size(); i++) {
            Range range = ranges.get(i);
            //判断使用哪个range产生最终的随机数
            if (range.percentScopeMin <= randomInt && randomInt <= range.percentScopeMax) {
                r = produceRandomNumber(range.min, range.max);
                break;
            }
        }
        return r;
    }

    public static class Range {
        public double min;
        public double max;
        public double percent; //百分比

        public double percentScopeMin; //百分比转换为[1,100]的数字的最小值
        public double percentScopeMax; //百分比转换为[1,100]的数字的最大值
    }

    public static void main(String[] args) {
        List<Double> separates = new ArrayList<Double>();
        separates.add(1D);
        separates.add(2D);
        separates.add(3D);
        separates.add(4D);
        separates.add(5D);
        separates.add(6D);
        separates.add(7D);
        List<Double> percents = new ArrayList<>();
        percents.add(41D);
        percents.add(41.9D);
        percents.add(1D);
        percents.add(1D);
        percents.add(5D);
        percents.add(5D);
        percents.add(5D);
        percents.add(0.1D);
        double number = produceRateRandomNumber(0, 8, separates, percents);
        int ceil = (int) Math.ceil(number);
        if (ceil == 0) {
            ceil = 1;
        }
        System.err.println(ceil);
    }

    /**
     * 按概率返回一个随机数子
     *
     * @return 随机数字
     */
    public static int getRateNumber() {
        List<Double> separates = new ArrayList<Double>();
        separates.add(1D);
        separates.add(2D);
        separates.add(3D);
        separates.add(4D);
        separates.add(5D);
        separates.add(6D);
        separates.add(7D);
        List<Double> percents = new ArrayList<>();
        percents.add(41D);
        percents.add(41.9D);
        percents.add(1D);
        percents.add(1D);
        percents.add(5D);
        percents.add(5D);
        percents.add(5D);
        percents.add(0.1D);
        double number = produceRateRandomNumber(0, 8, separates, percents);
        int ceil = (int) Math.ceil(number);
        if (ceil == 0) {
            ceil = 1;
        }
        return ceil;
    }
}
