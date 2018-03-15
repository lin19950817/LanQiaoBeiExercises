package myTest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by LinGaunnan on 3/5/2018.
 * 用BFS求出左上角最小区域，并用DFS检测余下空间是否连续
 */

/**
 * 探索状态，用于标志探索到当前位置上的状态
 * 存放在广度优先检索队列中
 */
class SearchState{
    boolean[][] flag;
    int x, y, amt, sum;
    // 所有探索状态位， true表示已经探索过
    // 当前探索到的坐标x,y
    // amt当前探索过amt个格子
    // 当前得到的值
    public SearchState(boolean[][] flag, int x, int y, int amt, int sum){
        this.flag = flag;
        this.x = x;
        this.y = y;
        this.amt = amt;
        this.sum = sum;
    }
}
public class Main {
    private boolean stop = false;
    private int result = 0;
    public int getResult(){
        return this.result;
    }
    private void dfs_Continue(boolean[][] flag, int cur_x, int cur_y){
        int row = flag.length, col = flag[0].length;
        if(cur_x>=0 && cur_x<row && cur_y>=0 && cur_y<col&&!flag[cur_x][cur_y]) {
            flag[cur_x][cur_y] = true;
            dfs_Continue(flag, cur_x-1, cur_y);
            dfs_Continue(flag, cur_x+1, cur_y);
            dfs_Continue(flag, cur_x, cur_y-1);
            dfs_Continue(flag, cur_x, cur_y+1);
        }
    }
    private boolean allTrue(boolean[][] flag){
        for(int i=0;i<flag.length;i++){
            for(int j=0;j<flag[i].length;j++)
                if(!flag[i][j])
                    return false;
        }
        return true;
    }
    private boolean isContinuous(boolean[][] flag){
        int row = flag.length, col = flag[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(!flag[i][j]){
                    dfs_Continue(flag, i, j);
                    return allTrue(flag);
                }
            }
        }
        return false;
    }


    public void bfs_search(int[][] matrix, Queue<SearchState> queue, int target_sum) {
        int row = matrix.length, col = matrix[0].length;
        while (!stop && !queue.isEmpty()) {
            SearchState ss = queue.poll();
            int cur_x = ss.x, cur_y = ss.y, cur_sum = ss.sum;
            boolean[][] flag = ss.flag;
            cur_sum += matrix[cur_x][cur_y];
            ss.amt ++;
            flag[cur_x][cur_y] = true;
            if (cur_sum == target_sum && isContinuous(cloneFlag(flag))){
                // 找到总和满足要求且余下格子连续
                // stop不再继续探索
                // result 获取当前最小格子数
                stop = true;
                result = ss.amt;
            } else {
                // 上下左右合法的格子加入探索队列当中
                if (cur_x < row-1 && !flag[cur_x+1][cur_y])
                    queue.add(new SearchState(cloneFlag(flag), cur_x+1, cur_y, ss.amt, cur_sum));
                if (cur_x > 0 && !flag[cur_x-1][cur_y])
                    queue.add(new SearchState(cloneFlag(flag), cur_x-1, cur_y, ss.amt, cur_sum));
                if (cur_y < col-1 && !flag[cur_x][cur_y+1])
                    queue.add(new SearchState(cloneFlag(flag), cur_x, cur_y+1, ss.amt, cur_sum));
                if (cur_y > 0 && !flag[cur_x][cur_y-1])
                    queue.add(new SearchState(cloneFlag(flag), cur_x, cur_y-1, ss.amt, cur_sum));
            }
        }
    }
    // deep copy
    private boolean[][] cloneFlag(boolean[][] flag){
        int row = flag.length, col = flag[0].length;
        boolean[][] newflag = new boolean[row][col];
        for(int i=0;i<row;i++)
            newflag[i] = flag[i].clone();
        return newflag;
    }
    public static void main(String[] args){
        // Input start
        Scanner cn = new Scanner(System.in);
        String[] scale = cn.nextLine().split(" ");
        int col = Integer.parseInt(scale[0]);
        int row = Integer.parseInt(scale[1]);
        int[][] matrix = new int[row][col];
        int sum = 0;
        for(int i=0;i<row;i++){
            String[] inp_row = cn.nextLine().split(" ");
            for(int j=0;j<col;j++) {
                matrix[i][j] = Integer.parseInt(inp_row[j]);
                sum += matrix[i][j];
            }
        }
        // Input end

        // 不满足要求
        if((sum & 1) == 1 || col == 0 || row == 0)
            System.out.println(0);
        // 目标和为总和一般
        int half_sum = sum >> 1;
        // 初始化(0,0)格子为第一个探索状态
        Queue<SearchState> queue = new LinkedList<>();
        queue.add(new SearchState(new boolean[row][col],0 , 0, 0, 0));
        Main main = new Main();
        main.bfs_search(matrix, queue,  half_sum);
        System.out.println(main.getResult());

    }
}
