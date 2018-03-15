package myTest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by LinGaunnan on 3/5/2018.
 * ��BFS������Ͻ���С���򣬲���DFS������¿ռ��Ƿ�����
 */

/**
 * ̽��״̬�����ڱ�־̽������ǰλ���ϵ�״̬
 * ����ڹ�����ȼ���������
 */
class SearchState{
    boolean[][] flag;
    int x, y, amt, sum;
    // ����̽��״̬λ�� true��ʾ�Ѿ�̽����
    // ��ǰ̽����������x,y
    // amt��ǰ̽����amt������
    // ��ǰ�õ���ֵ
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
                // �ҵ��ܺ�����Ҫ�������¸�������
                // stop���ټ���̽��
                // result ��ȡ��ǰ��С������
                stop = true;
                result = ss.amt;
            } else {
                // �������ҺϷ��ĸ��Ӽ���̽�����е���
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

        // ������Ҫ��
        if((sum & 1) == 1 || col == 0 || row == 0)
            System.out.println(0);
        // Ŀ���Ϊ�ܺ�һ��
        int half_sum = sum >> 1;
        // ��ʼ��(0,0)����Ϊ��һ��̽��״̬
        Queue<SearchState> queue = new LinkedList<>();
        queue.add(new SearchState(new boolean[row][col],0 , 0, 0, 0));
        Main main = new Main();
        main.bfs_search(matrix, queue,  half_sum);
        System.out.println(main.getResult());

    }
}
