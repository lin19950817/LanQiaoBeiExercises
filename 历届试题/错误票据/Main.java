import java.util.*;

public class Main {

    public static void main(String[] args) {
		//录入数据
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine().trim());
        String[] str = new String[n];
        for (int i = 0; i < n; i++)
            str[i] = sc.nextLine();

		//处理数据转成数组
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            String[] sa = str[i].split("[ ]+");
            for (int j = 0; j < sa.length; j++)
                list.add(Integer.valueOf(sa[j].trim()));
        }
        int[] a = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            a[i] = list.get(i);
		
		//排序查找输出
        new Main().quicksort(a, 0, a.length - 1);
        int flag = a[0],duan=-1,chong=-1;
        for (int i = 1; i < a.length; i++, flag++) {
            if(-1==duan&&a[i-1]+2==a[i])
                duan=a[i-1]+1;
            if(-1==chong&&a[i-1]==a[i])
                chong=a[i];
            if(duan!=-1&&chong!=-1)
                break;
        }
        System.out.println(duan+" "+chong);
    }

    private void quicksort(int[] a, int left, int right) {
        if (left < right) {
            int _left = left, _right = right, target = a[left];
            while (_right != _left) {
                while (a[_right] >= target && _right > _left)
                    _right--;
                while (a[_left] <= target && _right > _left)
                    _left++;
                if (_right > _left) {
                    int temp = a[_right];
                    a[_right] = a[_left];
                    a[_left] = temp;
                }
            }
            a[left] = a[_left];
            a[_left] = target;

            quicksort(a, _right + 1, right);
            quicksort(a, left, _right - 1);

        }
    }
}
