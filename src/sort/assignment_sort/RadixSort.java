package sorts;

import java.util.Random;

public class RadixSort {
    private int first;

    public RadixSort(NodeForRadix r[], int n, int m,int d) {
        QueueNode[] q = new QueueNode[m];
        //从下标0开始存放待排序记录
        for (int i = 0; i < n; i++) {
            r[i].setNext(i + 1);
        }
        //!
        //设置尾指针和头指针
        //first = 0
        r[n - 1].setNext(-1);
        //初始化m个静态队列的队头，队尾指针
        for (int i = 0; i < m; i++) {
            q[i] = new QueueNode();
            q[i].front = -1;
            q[i].rear = -1;
        }
        //进行分配
        for (int j = 0; j < d; j++) {
            //进行第j躺分配
            distribute(r, q, j);
            //进行收集
            //进行第j躺收集,first为头指针
            this.first = Bucket_Sort.collect(r, m, q);
            //重新初始化m个静态队列的队头，队尾指针
            for (int i = 0; i < m; i++) {
                q[i].front = -1;
                q[i].rear = -1;
            }
        }
    }
    private void distribute(NodeForRadix[] r, QueueNode[] q,int j) {
        int i = first;
        //r[7].next怎么办
        while (i != -1) {
            int k = r[i].key[j];
            if (q[k].front == -1)
                q[k].front = i;
            else
                r[q[k].rear].next = i;
            q[k].rear = i;

            i = r[i].next;
        }
    }

    public static void main(String[] args) {
        int testD = 2;
        int testN = 7;
        //[0-9]
        int testM = 10;
        //声明了一个java数组
        NodeForRadix[] testR = new NodeForRadix[testN];
        //new
        for (int i = 0; i < testN; i++) {
            testR[i] = new NodeForRadix(testD);
        }
        Random random = new Random();
        for (int i = 0; i < testR.length; i++) {
            int t = random.nextInt(100);
            testR[i].key[0] = t%10;
            testR[i].key[1] = t/10;
        }
        RadixSort radixSort = new RadixSort(testR,testN,testM,testD);
        int k = radixSort.first;
        if (testR[k].key[1] != 0){
            System.out.print(testR[k].key[1]);
        }
        System.out.println(testR[k].key[0]);
        while (testR[k].next != -1){
            if (testR[testR[k].next].key[1] != 0) {
                System.out.print(testR[testR[k].next].key[1]);
            }
            System.out.println(testR[testR[k].next].key[0]);
            k = testR[k].next;
        }
    }
}
