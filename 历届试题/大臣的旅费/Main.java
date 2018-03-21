package myTest;

import java.util.*;


public class Main {
	// 最后一个case超时
    public static void main(String[] args) {
        // Input Start
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] adjacencyMaxtrix = new int[n][n];
        for (int i = 1; i < n; i ++) {
            int x = sc.nextInt(), y = sc.nextInt(), value = sc.nextInt();
            // 无向岭接矩阵,矩阵从零开始并非从1，所以x,y减1
            adjacencyMaxtrix[x - 1][y - 1] = value;
            adjacencyMaxtrix[y - 1][x - 1] = value;
        }
        // Input End
        

        DistanceOfTheCity theFurthestCity = new Main().answer(n, adjacencyMaxtrix);
        System.out.println(theFurthestCity.expense);
        
        
//        // 测试
//        Main m = new Main();
//        int[][] a = new int[5][5];
//        a[0][1] = 2;
//        a[0][2] = 1;
//        a[1][3] = 5;
//        a[1][4] = 4;
//        a[1][0] = 2;
//        a[2][0] = 1;
//        a[3][1] = 5;
//        a[4][1] = 4;
//        DistanceOfTheCity theFurthestCity = m.answer(5, a);
//        System.out.println("大臣J从城市" + theFurthestCity.startCity + "到城市" + theFurthestCity.endCity + "要花费" + theFurthestCity.expense + "的路费。");
    }
    
    private DistanceOfTheCity answer(int cityCount, int[][] adjacencyMatrix) {
        DistanceOfTheCity theFurthestCity = null;
        
        for (int i = 0; i < cityCount; i++) {
            boolean[] blBook = new boolean[cityCount];
            blBook[i] = true;
            DistanceOfTheCity tempTheFurthestCity = new DistanceOfTheCity(0, 0, 0);
            DistanceOfTheCity cityInformation = new DistanceOfTheCity(i, 0, 0);
            distanceOfTheCity(cityCount, adjacencyMatrix, blBook, i, cityInformation, tempTheFurthestCity);
            if (null == theFurthestCity || tempTheFurthestCity.distance > theFurthestCity.distance) {
                theFurthestCity = tempTheFurthestCity;
            }
        }
        theFurthestCity.expense = travelExpense(theFurthestCity.distance);
        // adjacencyMatrix是从零开始的，所以开始和结束城市都要加1；
        theFurthestCity.startCity++;
        theFurthestCity.endCity++;
        return theFurthestCity;
    }
    
    /** 
     * 计算当前城市到其他任何城市的最远距离
     * 参数：adjacencyMatrix：存放城市间距离的岭接矩阵，blBook：记录城市是否已去过，cityInformation：记录遍历过程中城市信息，theFurthestCity：最远城市
     * */
    private void distanceOfTheCity(int cityCount, int[][] adjacencyMatrix, boolean[] blBook, int currentCity, DistanceOfTheCity cityInformation, DistanceOfTheCity theFurthestCity) {
        if (cityInformation.distance > theFurthestCity.distance) {
            theFurthestCity.startCity = cityInformation.startCity;
            theFurthestCity.endCity = currentCity;
            theFurthestCity.distance = cityInformation.distance;
        }
        
        for (int i = 0; i < cityCount; i++) {
            if (adjacencyMatrix[currentCity][i] != 0 && !blBook[i]) {
                blBook[i] = true;
                cityInformation.distance += adjacencyMatrix[currentCity][i];
                distanceOfTheCity(cityCount, adjacencyMatrix, blBook, i, cityInformation, theFurthestCity);
                // 回溯
                cityInformation.distance -= adjacencyMatrix[currentCity][i];
                blBook[i] = false;
            }
        }
    }
    
    /** 
     * 计算路费
     * 参数：distance：距离
     * 返回：路费：int
     * */
    private int travelExpense(int distance) {
        int travelExpense = 0, halfOfNumber = distance / 2;
        // 累加
        int median = 0 == distance % 2 ? 0 : halfOfNumber + 1;
        travelExpense = (distance + 1) * halfOfNumber + median;
        
        travelExpense += 10 * distance;
        return travelExpense;
    }
}
class DistanceOfTheCity {
    public int startCity;
    public int endCity;
    public int distance;
    public int expense;
    public DistanceOfTheCity(int startCity, int endCity, int distance) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.distance = distance;
    }
}