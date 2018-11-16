package sorts;

public class NodeForBucket implements NodeIntf {
    int key;
    int next;

    public void setNext(int next_) {
        this.next = next_;
    }
}
