package com.grgbanking.counter.common.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/***
 * 时间窗通用工具类。
 * 时间窗定义: Pair<LocalDateTime,LocalDateTime>
 *     其中左边元素代表开始时间，如果是null，表示无限久之前。
 *     右边元素代表结束时间，如果是null,代表无限未来。
 *
 *  规则的时间窗列表定义: 列表中的时间窗元素以开始时间由小到大排序，并且相邻两个时间窗之间没有交叉。
 *  非规则的时间窗列表定义: 规则的时间窗列表以外的普通列表称之为非规则时间窗列表。
 *
 *
 */
@Slf4j
public class TimeWindowUtils {
    private TimeWindowUtils() {
    }

    /***
     * 比较两个时间窗先后顺序，根据开始时间作比较。
     * @param pair1
     * @param pair2
     * @return
     */
    public static int compareTimeWindow(Pair<LocalDateTime,LocalDateTime> pair1,Pair<LocalDateTime,LocalDateTime> pair2){
        LocalDateTime start1=pair1.getLeft();
        LocalDateTime start2=pair2.getLeft();
        if(start1!=null&&start2!=null){
            return start1.compareTo(start2);
        }else if(start1==null){
            return -1;
        }else {
            return 1;
        }

    }

    /**
     * 判断给定时刻是否在时间窗内
     * @param localDateTime
     * @param pair
     * @return
     */
    public static boolean isInTimeWindow(LocalDateTime localDateTime,Pair<LocalDateTime,LocalDateTime> pair){
        assert localDateTime!=null;
        LocalDateTime start=pair.getLeft();
        LocalDateTime end=pair.getRight();
        if(start!=null){
            if(end!=null){
                if(localDateTime.compareTo(start)>=0&&localDateTime.compareTo(end)<=0){
                    return true;
                }else {
                    return false;
                }
            }else {
                if(localDateTime.compareTo(start)>=0){
                    return true;
                }else {
                    return false;
                }
            }
        }else {
            if(end!=null){
                if(localDateTime.compareTo(end)<=0){
                    return true;
                }else {
                    return false;
                }
            }else {
                return true;
            }
        }
    }

    /***
     * 求两个时间窗的交集
     * @param pair1
     * @param pair2
     * @return 时间窗。如果返回null表示没有交集。
     */
    public  static Pair<LocalDateTime,LocalDateTime> intersectTimeWindow(Pair<LocalDateTime,LocalDateTime> pair1, Pair<LocalDateTime,LocalDateTime> pair2){
        if(compareTimeWindow(pair1,pair2)>0){
            Pair<LocalDateTime,LocalDateTime> pair=pair1;
            pair1=pair2;
            pair2=pair;
        }
        LocalDateTime start1=pair1.getLeft();
        LocalDateTime end1=pair1.getRight();
        LocalDateTime start2=pair2.getLeft();
        LocalDateTime end2=pair2.getRight();
        Pair<LocalDateTime,LocalDateTime> ret;
        if(end1!=null){
            if(start2.compareTo(end1)>=0){
                ret=null;
            }else {
                if(end2.compareTo(end1)<0){
                    ret=new ImmutablePair<>(start2,end2);
                }else {
                    ret=new ImmutablePair<>(start2,end1);
                }
            }
        }else{
            ret=new ImmutablePair<>(start2,end2);
        }
        return ret;
    }

    /***
     * 求两个时间窗的并集
     * @param pair1
     * @param pair2
     * @return 规则时间窗列表。改返回值的列表长度不会超过2。因为两个时间窗如果有交叉，返回的列表长度为1，否则为2。
     */
    public static List<Pair<LocalDateTime,LocalDateTime>> unionTimeWindow(Pair<LocalDateTime,LocalDateTime> pair1,Pair<LocalDateTime,LocalDateTime> pair2){
        if(compareTimeWindow(pair1,pair2)>0){
            Pair<LocalDateTime,LocalDateTime> pair=pair1;
            pair1=pair2;
            pair2=pair;
        }
        LocalDateTime start1=pair1.getLeft();
        LocalDateTime end1=pair1.getRight();
        LocalDateTime start2=pair2.getLeft();
        LocalDateTime end2=pair2.getRight();
        List<Pair<LocalDateTime,LocalDateTime>>  ret=new ArrayList<>();
        if(end1!=null){
            if (start2!=null){
                if(end1.compareTo(start2)>=0){
                    ret.add(new ImmutablePair<>(start1,end2));
                }else {
                    ret.add(pair1);
                    ret.add(pair2);
                }
            }else {
                ret.add(new ImmutablePair<>(null,end2));
            }
        }else{
            ret.add(new ImmutablePair<>(start1,null));
        }
        return ret;

    }

    /***
     * 求第一个时间窗相对于第二个时间窗的差集，
     * @param pair1
     * @param pair2
     * @return 时间窗。如果为空，则表示第二个时间窗范围完全覆盖第一个时间窗。
     */
    public static List<Pair<LocalDateTime,LocalDateTime>> exceptTimeWindow(Pair<LocalDateTime,LocalDateTime> pair1, Pair<LocalDateTime,LocalDateTime> pair2){
        LocalDateTime start1=pair1.getLeft();
        LocalDateTime end1=pair1.getRight();
        LocalDateTime start2=pair2.getLeft();
        LocalDateTime end2=pair2.getRight();
        List<Pair<LocalDateTime,LocalDateTime>> ret=new ArrayList<>();
        //去掉左边
        if(start2.compareTo(start1)<=0&&end2!=null&&end2.compareTo(start1)>0&&(end1==null||end2.compareTo(end1)<0)){
            ret.add(new ImmutablePair<>(end2, end1));
            //去掉中间
        }else if(start2.compareTo(start1)>0&&end2!=null&&(end1==null||end2.compareTo(end1)<0)){
            ret.add(new ImmutablePair<>(start1, start2));
            ret.add(new ImmutablePair<>(end2, end1));
            //去掉右边
        }else if(start2.compareTo(start1)>0&&(end1==null||start2.compareTo(end1)<0)&&(end2==null||end1!=null&&end1.compareTo(end2)<=0)){
            ret.add(new ImmutablePair<>(start1, start2));
            //不去掉
        }else if(end2!=null&&end2.compareTo(start1)<=0||(end1!=null&&start2.compareTo(end1)>=0)){
            ret.add(new ImmutablePair<>(start1, end1));
            //去掉全部
        }else{
        }

        return ret;
    }


    /***
     *
     * 对给定时间窗列表进行合并(去重)。得到规则的时间窗列表。
     *
     * @param list
     */
    public static void combineTimeWindowList(List<Pair<LocalDateTime,LocalDateTime>> list){
        list.sort(TimeWindowUtils::compareTimeWindow);
        int pos=0;
        Pair<LocalDateTime,LocalDateTime> pair1=list.get(pos);
        while (pos<list.size()-1){
            Pair<LocalDateTime,LocalDateTime> pair2=list.get(pos+1);
            List<Pair<LocalDateTime, LocalDateTime>> pairs = unionTimeWindow(pair1, pair2);
            if(pairs.size()==1){
                list.remove(pos);
                list.set(pos,pairs.get(0));
            }else {
                pos++;
            }
            pair1=list.get(pos);
        }
    }

    /***
     *
     * @param pair
     * @param pairList
     * @return
     * @throws Exception
     */
    public static   List<Pair<LocalDateTime,LocalDateTime>> exceptTimeWindowList(Pair<LocalDateTime,LocalDateTime> pair, List<Pair<LocalDateTime,LocalDateTime>> pairList) throws Exception {
        checkRegularTimeWindowList(pairList);
        List<Pair<LocalDateTime,LocalDateTime>> result= new ArrayList<>();
        Pair<LocalDateTime, LocalDateTime> pairToCut=pair;
        for (int i = 0; i < pairList.size(); i++) {

            Pair<LocalDateTime, LocalDateTime> pairTemp=pairList.get(i);
            if(pairToCut.getRight()!=null&&pairToCut.getRight().compareTo(pairTemp.getLeft())<=0){
                result.add(pairToCut);
                break;
            }
            List<Pair<LocalDateTime, LocalDateTime>> list = exceptTimeWindow(pairToCut, pairTemp);
            if(list.size()==2){
                result.add(list.get(0));
                pairToCut=list.get(1);
            }else if(list.size()==1){
                pairToCut=list.get(0);
            }

            if(list.size()>0&&(i==pairList.size()-1)){
                result.add(pairToCut);
            }
        }

        return result;


    }

    /***
     *
     * @param pairList1
     * @param pairList2
     * @return
     * @throws Exception
     */
    public static   List<Pair<LocalDateTime,LocalDateTime>> exceptTimeWindowList(List<Pair<LocalDateTime,LocalDateTime>> pairList1, List<Pair<LocalDateTime,LocalDateTime>> pairList2) throws Exception {
        checkRegularTimeWindowList(pairList1);
        checkRegularTimeWindowList(pairList2);

        List<Pair<LocalDateTime,LocalDateTime>> result=new ArrayList<>();
        for (Pair<LocalDateTime, LocalDateTime> pair : pairList1) {
            final List<Pair<LocalDateTime, LocalDateTime>> list = exceptTimeWindowList(pair, pairList2);
            result.addAll(list);
        }
        return result;

    }

    /***
     * 计算规则时间窗列表各个时段的和。单位s。
     * @param list
     * @return
     */
    public static long sumDuration(List<Pair<LocalDateTime,LocalDateTime>> list) throws Exception {
        if(list.size()==0){
            return 0;
        }
        checkRegularTimeWindowList(list);
        LongSummaryStatistics collect = list.stream().collect(Collectors.summarizingLong(
                pair -> {
                    LocalDateTime left = pair.getLeft();
                    LocalDateTime right = pair.getRight();
                    long seconds = Duration.between(left, right).getSeconds();
                    return seconds;
                }));
        return collect.getSum();
    }

    /***
     * 判断是否是规则的时间窗列表
     * @param list
     * @return
     */
    public static boolean isRegularTimeWindowList(List<Pair<LocalDateTime,LocalDateTime>> list){
       Pair<LocalDateTime, LocalDateTime> pair1 = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Pair<LocalDateTime, LocalDateTime> pair2 = list.get(i);
            if(pair1.getRight()!=null&&pair2.getLeft()!=null){
                if(pair1.getRight().compareTo(pair2.getLeft())<0){
                    pair1=pair2;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }

    /***
     * 检查是否是规则的时间窗列表
     * @param list
     * @return
     */
    public static void checkRegularTimeWindowList(List<Pair<LocalDateTime,LocalDateTime>> list) throws Exception {
        if(!isRegularTimeWindowList(list)){
            list.forEach(pair -> {
                log.error("TimeWindow:{},{}",pair.getLeft().toString(),pair.getRight().toString());
            });
            throw new TimeWindowListException("Provided Time window List is not Regular");
        }
    }

    public static void main(String[] args) {
        try {
            testExpect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Pair<LocalDateTime,LocalDateTime>> inclusiveWorkDateTimeList= new LinkedList<>();
        inclusiveWorkDateTimeList.add( new MutablePair<>(LocalDateTime.parse("2020-10-10 00:00:00", dtf),LocalDateTime.parse("2020-10-10 24:00:00",dtf)));
        inclusiveWorkDateTimeList.add( new MutablePair<>(LocalDateTime.parse("2020-10-11 00:00:00", dtf),LocalDateTime.parse("2020-10-11 24:00:00",dtf)));

        System.out.println(isRegularTimeWindowList(inclusiveWorkDateTimeList));
        try {
            checkRegularTimeWindowList(inclusiveWorkDateTimeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void  testExpect() throws Exception {
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        Pair<LocalDateTime,LocalDateTime> pair1= new MutablePair<>(LocalDateTime.parse("2020-10-10 00:00:00", dtf),LocalDateTime.parse("2020-10-10 24:00:00",dtf));
//        Pair<LocalDateTime,LocalDateTime> pair2= new MutablePair<>(LocalDateTime.parse("2020-10-10 12:00:00", dtf),LocalDateTime.parse("2020-10-10 14:00:00", dtf));
//        final List<Pair<LocalDateTime, LocalDateTime>> list = exceptTimeWindow(pair1, pair2);
//        System.out.println(list);
//
        Pair<LocalDateTime,LocalDateTime> pair2= new MutablePair<>(LocalDateTime.parse("2020-10-09 00:00:00", dtf),LocalDateTime.parse("2020-10-09 20:00:00",dtf));
        Pair<LocalDateTime,LocalDateTime> pair3= new MutablePair<>(LocalDateTime.parse("2020-10-10 00:00:00", dtf),LocalDateTime.parse("2020-10-10 24:00:00",dtf));
        Pair<LocalDateTime,LocalDateTime> pair4= new MutablePair<>(LocalDateTime.parse("2020-10-09 12:00:00", dtf),LocalDateTime.parse("2020-10-10 14:00:00", dtf));
        Pair<LocalDateTime,LocalDateTime> pair5= new MutablePair<>(LocalDateTime.parse("2020-10-10 16:00:00", dtf),LocalDateTime.parse("2020-10-10 18:00:00", dtf));
        final List<Pair<LocalDateTime, LocalDateTime>> list2 = exceptTimeWindowList(Arrays.asList(pair2,pair3), Arrays.asList(pair4,pair5));
        System.out.println(list2);


        List<Pair<LocalDateTime,LocalDateTime>> inclusiveWorkDateTimeList= new LinkedList<>();
        inclusiveWorkDateTimeList.add( new MutablePair<>(LocalDateTime.parse("2020-10-10 00:00:00", dtf),LocalDateTime.parse("2020-10-10 24:00:00",dtf)));
        inclusiveWorkDateTimeList.add( new MutablePair<>(LocalDateTime.parse("2020-10-11 00:00:00", dtf),LocalDateTime.parse("2020-10-11 24:00:00",dtf)));
    }

    public static class TimeWindowListException extends Exception {
        public TimeWindowListException(String info){
            super(info);
        }
    }

}
