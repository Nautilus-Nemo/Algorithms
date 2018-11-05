package sort.Utils;

public class UtilArrayItemSwap<T> {
    //泛型数组问题
    public  void swap(T[] arr, int i, int j){
        T tem;
        tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    public static void main(String[] args) {
        Integer[] a = {1,2,3,4,5};
        UtilArrayItemSwap<Integer> uais = new UtilArrayItemSwap();
        uais.swap(a,2,4);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
