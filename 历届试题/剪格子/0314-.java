package myTest;

import java.util.*;

//�д���

public class Main {
    public static void main(String[] args) {

         //¼������

         Scanner sc=new Scanner(System.in);

         int n=sc.nextInt(),m=sc.nextInt();

         int[][] a=new int[n][m];

         for (int i = 0; i < a.length; i++)

         for (int j = 0; j < a[0].length; j++)

         a[i][j]=sc.nextInt();

//
//int[][] a1 = {{ 2,2,1,1,0,0 }, {2,0,0,2,0,0 }, {2,1,0,1,0,2 },{1,1,0,3,0,0},{1,1,0,3,0,0},{1,1,0,0,0,0},{2,0,0,0,0,0}  }; // ����
//int[][] a2 = {{ 2,2,1,1,0,0 }, {2,0,3,2,0,0 }, {2,1,3,1,0,2 },{1,1,0,3,0,0},{1,1,0,3,0,0},{1,1,0,0,0,0},{2,0,0,0,0,0}  }; // ����
//int[][] a3 = {{ 2,2,1,1,0,0 }, {2,0,0,1,0,0 }, {2,1,0,1,0,2 },{1,1,0,1,0,0},{1,1,0,1,0,0},{1,1,0,3,0,0},{1,1,0,2,0,0}  }; // ����

        Main ms = new Main();
//        System.out.println(ms.minStep(a1));
//        System.out.println(ms.minStep(a2));
//        System.out.println(ms.minStep(a3));
        
        System.out.println(ms.minStep(a));
    }

    /** 
     * ��¡���book
     * ������book���
     * ���أ���¡��boolean[][]
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
     * �����������list�����������Ҫ���book
     * ������a:���������飬book����ǣ�x��y��ִ�����꣬sum:�ۻ��ѱ�ǵ��ֵ���ܺͣ�target��Ҫ��sum����target��list�����������������Ҫ���book
     * */
    private void dfsListFill(int[][]a,boolean[][] book,int x,int y,int sum,int target,List<boolean[][]> list){
        //�������Ͻ�
        if(false==book[0][0]){
            book[0][0]=true;
            sum=a[0][0];
        }
        // ��������ķ���
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
            //����Ҫ�������list
            if (sum == target) {
                list.add(bookClone(book));
            }
        }
    }
    /** 
     * �����������Ⱦɫ
     * */
    private boolean[][] stain(boolean[][] barr,int x,int y){
        int[][] path = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int ver=barr.length,col=barr[0].length;
        List<point> list=new ArrayList<point>();
        //��x,y���꿪ʼȾɫ
        list.add(new point(x,y));
        barr[x][y]=true;
        // Ⱦɫ
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
     * Ⱦɫ�Ƿ���ȫ
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
     * �Ƿ��γ���������
     * */
    private boolean isContinue(boolean[][] barr){
        //�ҳ�Ϊ��ǵĵ㿪ʼȾɫ���ж��Ƿ��γ���������
        int x=barr.length,y=barr[0].length;
        for(int i=0;i<x;i++)
            for(int j=0;j<y;j++)
                if(false==barr[i][j]){
                    return isAllStain(stain(bookClone(barr),i,j));
                }
        //û���ҵ�Ϊ��ǵĵ�
        return false;
    }
    /** 
     * ������С������
     * */
    private int minStep(int[][] a){
        int x=a.length,y=a[0].length,target=0,minstep=999;
        List<boolean[][]> list=new ArrayList<boolean[][]>();
        //����target
        for(int i=0;i<x;i++)
            for(int j=0;j<y;j++)
                target+=a[i][j];
        //�ܺͲ�������2�򷵻���
        if(0!=target%2) return 0;
        
        ///�ù���������
        Queue<bfs_model> queue =new LinkedList<>();
        boolean[][] bl=new boolean[x][y];
        bl[0][0]=true;
        queue.add(new bfs_model(bl,0,0,1,a[0][0]));
        return bfs_reseach(a,queue,target>>1);
        
        /**     ����������
        //��list������з���������book
        dfsListFill(a,new boolean[x][y],0,0,0,target>>1,list);
        //�ҳ���С������
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
        //listԪ�ض������γ���������
        return 0;
        */
    }
    /** 
     * �ù�������ҳ�����Ҫ���bfs_model������step
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
        //û���ҵ��������� ��·��
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