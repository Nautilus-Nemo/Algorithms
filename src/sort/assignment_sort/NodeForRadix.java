package sorts;

public class NodeForRadix implements NodeIntf {
    int[] key;
    int next;

    public NodeForRadix(int d){
        key = new int[d];
    }
    public void setNext(int next_){
        this.next = next_;
    }
}
