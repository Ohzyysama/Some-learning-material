# Hashing
**address = hash(key)**<br>
理想的时间复杂度：O(C)<br>
常见的Hash函数：<br>
·取余法<br>
·平方取中法<br>
·乘法杂凑函数<br>
## 如何解决Hash冲突
1.linear probing:当一个位置被占时向后探测直到找到一个空位置<br>
2.quadratic probing:和linear probing类似，但是探测距离变为原来的平方(d+1,d+4,……)(这里的距离是相对于算出hash值的距离)<br>
3.separate chaining:相同hash值的元素用链表存储<br>