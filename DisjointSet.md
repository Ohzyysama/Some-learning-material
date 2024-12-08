# Disjoint Set
## 等价类
xRy,R满足自反性、对称性、传递性<br>
则R是一个等价关系，任意两个元素满足R的集合称为等价类<br>
### 等价类操作
Combine(a,b)：将a和b所在的等价类合并<br>
Find(e):返回e所在的等价类<br>
Combine(a,b)等价于以下代码：<br>
```
i = Find(a);
j = Find(b);
if(i != j){
    Union(i,j); //合并等价类
}
```
## 并查集
并查集是一种树型的数据结构，用于处理一些不相交集合的合并及查询问题<br>
### 简单的并查集操作
```
void Initialize(int n){
    parent = new int[n+1]; //把0号留空
    for(int e = 1 ; e <= n ; e ++)
        parent[e] = 0;
}

int Find(int e){
    while(parent[e])//根的父节点为0
        e = parent[e];
    return e;
}

void Union(int i, int j){
    parent[j] = i; //把一棵树的根挂在另一棵树的根上
}
```
并查集的初始化、合并、寻根操作
```java
public class DisjSets{
    public DisjSets(int numElements)
    public void union(int root1, int root2)
    public int find(int x)
    private int [] s; //记录每个元素的父节点,根节点的s值为负数且代表高度
}

public DisjSets(int numElements){
    s = new int [numElements];
    for(int i = 0 ; i < s.length ; i ++)
        s[i] = -1; //把每个元素都设为根节点
}

public void union(int root1, int root2){
    s[root2] = root1;
}

public int find(int x){
    if(s[x] < 0){
        return x;
    }else{
        return find(s[x]);
    }
}
```
### Performance Evaluation
Find--O(h) (最坏情况)<br>
Union--O(1)<br>
#### 对Union进行优化
方式1：weight rule:把元素更少的树挂在更多的树上<br>
方式2：height rule:把高度更低的树挂在更高的树上<br>
方式1实现：
```
void Initialize(int n){
    root = new bool[n+1];
    parent = new int[n+1];
    for(int e = 1 ; e <= n ; e ++){
        parent[e] = 1;//父节点的parent值为树中元素的个数，非父节点的parent值为其父节点的编号
        root[e] = true;//如为根节点则为true,否则为false
    }
}

int Find(int e){
    while(!root[e])
        e = parent[e];
    return e;
}

void Union(int i, int j){
    if(parent[i] < parent[j]){
        parent[j] = parent[i] + parent[j];
        root[i] = false;
        parent[i] = j;
    }else{
        parent[i] = parent[j] + parent[i];
        root[j] = false;
        parent[j] = i;
    }
}
```
方式2实现：
```java
//只展示union
public void union(int root1, int root2){
    if(s[root2] < s[root1])//2更高
        s[root1] = root2;
    else{
        if(s[root1] == s[root2])
            s[root1] --;
        s[root2] = root1;
    }
}
```
#### 对Find进行优化
一般来说，处理两个等价类，要使用Find()两次，Union()一次<br>
优化方法：在Find()时将经过的每一个节点连到根节点上<br>
示例：
C++实现
```C++
int Find(int e){
    int j = e;
    while(!root[j]) j = parent[j];
    //此时j为根节点
    int f = e;
    while(f != j){
        int pf = parent[f];
        parent[f] = j;
        f = pf;
    }
}
```
java实现：
```java
public int find(int x){
    if(s[x] < 0){
        return x;
    }else{
        return s[x] = find(s[x]);
    }
}
```
