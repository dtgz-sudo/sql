package cn.sdut;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class EditDistance {
    /**
     * 编辑距离算法
     * @param sourceStr 原字符串
     * @param targetStr 目标字符串
     * @return 返回最小距离: 原字符串需要变更多少次才能与目标字符串一致（变更动作：增加/删除/替换,每次都是以字节为单位）
     */
    public static int minDistance(String sourceStr, String targetStr){
        int sourceLen = sourceStr.length();
        int targetLen = targetStr.length();

        if(sourceLen == 0){
            return targetLen;
        }
        if(targetLen == 0){
            return sourceLen;
        }

        //定义矩阵(二维数组)
        int[][]  arr = new int[sourceLen+1][targetLen+1];

        for(int i=0; i < sourceLen+1; i++){
            arr[i][0] = i;
        }
        for(int j=0; j < targetLen+1; j++){
            arr[0][j] = j;
        }

        Character sourceChar = null;
        Character targetChar = null;

        for(int i=1; i < sourceLen+1 ; i++){
            sourceChar = sourceStr.charAt(i-1);

            for(int j=1; j < targetLen+1 ; j++){
                targetChar = targetStr.charAt(j-1);

                if(sourceChar.equals(targetChar)){
                    /*
                     *  如果source[i] 等于target[j]，则：d[i, j] = d[i-1, j-1] + 0          （递推式 1）
                     */
                    arr[i][j] = arr[i-1][j-1];
                }else{
                    /*  如果source[i] 不等于target[j]，则根据插入、删除和替换三个策略，分别计算出使用三种策略得到的编辑距离，然后取最小的一个：
                        d[i, j] = min(d[i, j - 1] + 1, d[i - 1, j] + 1, d[i - 1, j - 1] + 1 )    （递推式 2）
                        >> d[i, j - 1] + 1 表示对source[i]执行插入操作后计算最小编辑距离
                        >> d[i - 1, j] + 1 表示对source[i]执行删除操作后计算最小编辑距离
                        >> d[i - 1, j - 1] + 1表示对source[i]替换成target[i]操作后计算最小编辑距离
                    */
                    arr[i][j] = (Math.min(Math.min(arr[i-1][j], arr[i][j-1]), arr[i-1][j-1])) + 1;
                }
            }
        }

//        System.out.println("----------矩阵打印---------------");
//        //矩阵打印
//        for(int i=0;i<sourceLen+1;i++){
//
//            for(int j=0;j<targetLen+1;j++){
//                System.out.print(arr[i][j]+"\t");
//            }
//            System.out.println();
//        }
//        System.out.println("----------矩阵打印---------------");

        return arr[sourceLen][targetLen];
    }

    /**
     * 计算字符串相似度
     * similarity = (maxlen - distance) / maxlen
     * ps: 数据定义为double类型,如果为int类型 相除后结果为0(只保留整数位)
     * @param str1
     * @param str2
     * @return
     */
    public static double getsimilarity(String str1,String str2){
        double distance = minDistance(str1,str2);
        double maxlen = Math.max(str1.length(),str2.length());
        double res = (maxlen - distance)/maxlen;

        //System.out.println("distance="+distance);
        //System.out.println("maxlen:"+maxlen);
        //System.out.println("(maxlen - distance):"+(maxlen - distance));
        DecimalFormat df=new DecimalFormat("0.00");
        String s = df.format(res);
        Double value = Double.valueOf(s);
        return value;
    }

}
