# PriorityQueue
**优先队列**<br>
优先队列中的每个元素有优先级，优先级高（低）的元素优先出队。<br>
**优先队列的实现：堆**<br>
分类：最大堆、最小堆<br>
堆是一棵完全二叉树，每个父节点的值都大于等于或小于等于其子节点的值<br>
堆的物理层由数组实现<br>
**建堆算法**<br>
对非叶节点进行下律操作<br>
```java 
private void buildHeap(Comparable [] a, int currentSize){
    for(int i = currentSize/2; i > 0 ; i --){
        percDown(a, i, currentSize);
    }
}
private static void percDown(Comparable [] a, int i, int n){
    int child;
    Comparable tmp;

    for(tmp = a[i] ;  leftChild(i) < n ; i = child){
        child = leftChild(i);
        if(child != n-1 && a[child].compareTo(a[child+1]) < 0)
            child++;
        if(tmp.compareTo(a[child]) < 0)
            a[i] = a[child];
        else break;
    }
    a[i] = tmp;
}
private static int leftChild(int i){
    return 2*i+1;
}
```
**堆的实际应用1：堆排序**<br>
step1:建立最大堆<br>
step2:交换堆顶元素和最后一个元素<br>
step3:一直到堆中只剩一个元素为止<br>
**堆排序是不稳定的**<br>
**堆的实际应用2：找第K大元素**<br>
一般解法：先对元素进行排序，然后取出下标为k的元素<br>
复杂度:O($n^2$)<br>
用堆来实现：<br>
(1)读入前k个元素，建立最小堆<br>
(2)其余元素一一读入，与堆顶元素比较，若比堆顶元素大，则替换堆顶元素，并调整堆<br>
复杂度:O($nlogk$)<br>