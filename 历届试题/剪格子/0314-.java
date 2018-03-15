package myTest;

import java.util.*;

//有错误

public class Main {
    public static void main(String[] args) {

         //录入数据

         Scanner sc=new Scanner(System.in);

         int n=sc.nextInt(),m=sc.nextInt();

         int[][] a=new int[n][m];

         for (int i = 0; i < a.length; i++)

         for (int j = 0; j < a[0].length; j++)

         a[i][j]=sc.nextInt();

//
//int[][] a1 = {{ 2,2,1,1,0,0 }, {2,0,0,2,0,0 }, {2,1,0,1,0,2 },{1,1,0,3,0,0},{1,1,0,3,0,0},{1,1,0,0,0,0},{2,0,0,0,0,0}  }; // 测试
//int[][] a2 = {{ 2,2,1,1,0,0 }, {2,0,3,2,0,0 }, {2,1,3,1,0,2 },{1,1,0,3,0,0},{1,1,0,3,0,0},{1,1,0,0,0,0},{2,0,0,0,0,0}  }; // 测试
//int[][] a3 = {{ 2,2,1,1,0,0 }, {2,0,0,1,0,0 }, {2,1,0,1,0,2 },{1,1,0,1,0,0},{1,1,0,1,0,0},{1,1,0,3,0,0},{1,1,0,2,0,0}  }; // 测试

        Main ms = new Main();
//        System.out.println(ms.minStep(a1));
//        System.out.println(ms.minStep(a2));
//        System.out.println(ms.minStep(a3));
        
        System.out.println(ms.minStep(a));
    }

    /** 
     * 克隆标记book
     * 参数：book标记
     * 返回：克隆的boolean[][]
     * */
    private boolean[][] bookClone(boolean[][] book){
        int x=book.length,y=book[0].length;
        boolean[][] barr=new boolean[x][y];
        for(int i=0;i<x;i++)
            for(int j=0;j<y;j++)
                barr[i][j]=book[i][j];
        return barr;
    }

    /** 
     * 深度搜索，往list填充所有满足要求的book
     * 参数：a:搜索的数组，book：标记，x、y：执行坐标，sum:累积已标记点的值的总和，target：要求sum等于target，list：用来填充所有满足要求的book
     * */
    private void dfsListFill(int[][]a,boolean[][] book,int x,int y,int sum,int target,List<boolean[][]> list){
        //包含左上角
        if(false==book[0][0]){
            book[0][0]=true;
            sum=a[0][0];
        }
        // 深度搜索的方向
        int[][] path = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        if (sum < target) {
            for (int i = 0; i < 4; i++) {
                int nextX = x + path[i][0], nextY = y + path[i][1];
                if (nextX < a.length && nextY < a[0].length && nextX >= 0 && nextY >= 0) {
                    if (false == book[nextX][nextY]) {
                        book[nextX][nextY] = true;
                        sum += a[nextX][nextY];
                        dfsListFill(a, book,  nextX, nextY,sum, target, list);
                        book[nextX][nextY] = false;
                        sum -= a[nextX][nextY];
                    }
                }
            }
        } else {
            //符合要求则填充list
            if (sum == target) {
                list.add(bookClone(book));
            }
        }
    }
    /** 
     * 广度搜索进行染色
     * */
    private boolean[][] stain(boolean[][] barr,int x,int y){
        int[][] path = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int ver=barr.length,col=barr[0].length;
        List<point> list=new ArrayList<point>();
        //从x,y坐标开始染色
        list.add(new point(x,y));
        barr[x][y]=true;
        // 染色
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 4; j++) {
                int _x = list.get(i).x + path[j][0], _y = list.get(i).y + path[j][1];
                if (_x < ver && _y < col && _x >= 0 && _y >= 0) {
                    if (false == barr[_x][_y]) {
                        barr[_x][_y] = true;
                        list.add(new point(_x, _y));
                    }
                }
            }
        }
        return barr;
    }
    /** 
     * 染色是否完全
     * */
    private boolean isAllStain(boolean[][] barr){
        int x=barr.length,y=barr[0].length;
        for(int i=0;i<x;i++)
            for(int j=0;j<y;j++)
                if(false==barr[i][j])
                    return false;
        return true;
    }
    /** 
     * 是否形成连续区域
     * */
    private boolean isContinue(boolean[][] barr){
        //找出为标记的点开始染色后判断是否形成连续区域
        int x=barr.length,y=barr[0].length;
        for(int i=0;i<x;i++)
            for(int j=0;j<y;j++)
                if(false==barr[i][j]){
                    return isAllStain(stain(bookClone(barr),i,j));
                }
        //没有找到为标记的点
        return false;
    }
    /** 
     * 计算最小格子数
     * */
    private int minStep(int[][] a){
        int x=a.length,y=a[0].length,target=0,minstep=999;
        List<boolean[][]> list=new ArrayList<boolean[][]>();
        //计算target
        for(int i=0;i<x;i++)
            for(int j=0;j<y;j++)
                target+=a[i][j];
        //总和不能整除2则返回零
        if(0!=target%2) return 0;
        
        ///用广度搜索解决
        Queue<bfs_model> queue =new LinkedList<>();
        boolean[][] bl=new boolean[x][y];
        bl[0][0]=true;
        queue.add(new bfs_model(bl,0,0,1,a[0][0]));
        return bfs_reseach(a,queue,target>>1);
        
        /**     深度搜索解决
        //往list填充所有符合条件的book
        dfsListFill(a,new boolean[x][y],0,0,0,target>>1,list);
        //找出最小格子数
        for(boolean[][] barr:list){
            if(isContinue(barr)){
                int size=0;
                for(int i=0;i<x;i++)
                    for(int j=0;j<y;j++)
                        if(true==barr[i][j])
                            size++;
                if(size<minstep)
                    minstep=size;
            }
        }
        if(minstep!=999)
            return minstep;
        //list元素都不能形成连续区域
        return 0;
        */
    }
    /** 
     * 用广度搜索找出符合要求的bfs_model并返回step
     * */
    private int bfs_reseach(int[][] a,Queue<bfs_model> queue,int target){
        int[][] path = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int x=a.length,y=a[0].length;
        while(!queue.isEmpty()){
            bfs_model model=queue.poll();
            if(model.sum==target&&isContinue(model.book)){
                return model.step;
            }
            for(int i=0;i<4;i++){
                int _x=model.x+path[i][0],_y=model.y+path[i][1];
                if(_x>=0&&_x<x&&_y>=0&&_y<y){
                    if(false==model.book[_x][_y]){
                        int _step=model.step+1;
                        int _sum=model.sum+a[_x][_y];
                        boolean[][] _b=bookClone(model.book);
                        _b[_x][_y]=true;
                        queue.add(new bfs_model(_b,_x,_y,_step,_sum));
                    }
                }
            }
            
        }
        //没有找到符合条件 的路径
        return 0;
    }
}
class bfs_model{
    public int x;
    public int y;
    public boolean[][] book;
    public int step;
    public int sum;
    public bfs_model(boolean[][] _book,int _x,int _y, int _step,int _sum){
        book=_book;
        x=_x;
        y=_y;
        step=_step;
        sum=_sum;
    }
    
}
class point {

    public int x;

    public int y;

    public point(int a, int b) {

        x = a;

        y = b;

    }

}