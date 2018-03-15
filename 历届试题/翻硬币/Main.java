package myTest;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // input start
        Scanner sc = new Scanner(System.in);
        String startStatus = sc.nextLine(), endStatus = sc.nextLine();
        // input end
        
        System.out.println(new Main().minStep(startStatus, endStatus));
        
//        //测试
//        String case1start="**********",case1end="o****o****";
//        String case2start="*o**o***o***",case2end="*o***o**o***";
//        Main m = new Main();
//        System.out.println(m.minStep(case1start, case1end));
//        System.out.println(m.minStep(case2start, case2end));
    }

    private int minStep(String startStatus,String endStatus){
        //将字符串转成0，1组成的int数组(0表示o，1表示*)
        int[] intstart = new int[startStatus.length()],intend = new int[endStatus.length()];
        for(int i = 0;i < startStatus.length();i++){
            intstart[i] = '*' == startStatus.charAt(i) ? 1 : 0;
            intend[i] = '*' == endStatus.charAt(i) ? 1 : 0;
        }
        
        int step = 0;
        int[] intpresent=intClone(intstart);
        for(int i = 0;i < intstart.length-1;i++){
            if(intpresent[i] != intend[i]){
                intpresent[i] = intend[i];
                intpresent[i+1] = 0 == intpresent[i+1] ? 1 : 0;
                step++;
            }
        }
        if(isEquals(intpresent, intend))
            return step;
        else return -1;//不能翻转到目标状态
    }

    /**
     * 检查两个参数是否相等
     * 参数：presentStaus:需检查的当前状态，endStatus:目标状态
     * 返回boolean值
     */
    private boolean isEquals(int[] presentStatus, int[] endStatus) {
        for (int i = 0; i < presentStatus.length; i++)
            if (presentStatus[i] != endStatus[i])
                return false;
        return true;
    }
    /** 
     * 克隆数组
     * 参数：status:被克隆的对象
     * 返回int[]数组
     * */
    private int[] intClone(int[] status){
        int[] intClone = new int[status.length];
        for(int i = 0;i < status.length;i++)
            intClone[i] = status[i];
        return intClone;
    }
}