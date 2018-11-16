package sorts;

public class Bucket_Sort {
    private int first;

    public Bucket_Sort(NodeForBucket r[], int n, int m) {
        QueueNode[] q = new QueueNode[m];
        //从下标0开始存放待排序记录
        for (int i = 0; i < n; i++) {
            r[i].next = i + 1;
        }
        //!
        //设置尾指针和头指针
        //first = 0
        r[n - 1].next = -1;
        //初始化m个静态队列的队头，队尾指针
        for (int i = 0; i < m; i++) {
            q[i] = new QueueNode();
            q[i].front = -1;
            q[i].rear = -1;
        }
        //进行分配
        distribute(r,q);
        //进行收集
        this.first = collect(r,m,q);
    }

    static int collect(NodeIntf[] r, int m, QueueNode[] q) {
        int k = 0;
        int first = 0;
        //!
        while (q[k].front == -1){
            ++k;
        }
        int last = q[k].rear;
        first = q[k].front;
        ++k;
        while (k < m) {
            if (q[k].front != -1) {
                r[last].setNext(q[k].front);
                last = q[k].rear;
            }
            ++k;
        }
        r[last].setNext(-1);
        return first;
    }

    private void distribute(NodeForBucket[] r, QueueNode[] q) {
        int i = first;
        //r[7].next怎么办
        while (i != -1) {
            int k = r[i].key;
            if (q[k].front == -1)
                q[k].front = i;
            else
                r[q[k].rear].next = i;
            q[k].rear = i;

            i = r[i].next;
        }
    }

    public static void main(String[] args) {
        int testN = 7;
        //[0-9]
        int testM = 10;
        //声明了一个java数组
        NodeForBucket[] testR = new NodeForBucket[testN];
        //new
        for (int i = 0; i < testN; i++) {
            testR[i] = new NodeForBucket();
        }
        testR[0].key = 4;
        testR[1].key = 2;
        testR[2].key = 6;
        testR[3].key = 9;
        testR[4].key = 4;
        testR[5].key = 3;
        testR[6].key = 6;
        Bucket_Sort bs = new Bucket_Sort(testR, testN, testM);
        int k = bs.first;
        System.out.println(testR[k].key);
        while (testR[k].next != -1){
            System.out.println(testR[testR[k].next].key);
            k = testR[k].next;
        }
    }
}
