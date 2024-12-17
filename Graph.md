# Graph
**Graph=(V,E)**<br>
点集+边集<br>
分类：有向图和无向图<br>
### 一些概念(以无向图为例)
**完全图(complete graph)**:每两个顶点之间均有一条边<br>
**顶点的度数(degree)**:顶点相连的边数<br>
入度(in-degree):从某一顶点出发的边数<br>
出度(out-degree):到达某一顶点的边数<br>
度数=入度+出度<br>
若一个图有n个顶点和e条边，那么**e=所有顶点的度数/2**<br>
**子图**:点集和边集均为父图的子集<br>
**路径**:从一个顶点到另一个顶点经过的边的集合<br>
**连通图**:任意两个顶点之间都有至少一条路径<br>
若一个有向图中任意两个顶点之间都有双向的路径，那么称改图是**强连通的**<br>
一个非强连通图的最大强连通子图称为改图的**强连通分量**<br>
**网络**:边上加有权重的图<br>
**生成树**:连通图的极小连通子图。一棵有n个顶点的生成树有n-1条边<br>
## 图的表示方法
### 邻接矩阵
A(i,j)=1 ((i,j)存在) / 0 (i,j)不存在<br>
网络的邻接矩阵：若边存在该位置为边的权重，不存在则为无穷<br>
```C++
//找顶点V的第一个邻接顶点的位置
template <class NameType, class DistType>
    int Graph<NameType, DistType>::GetFirstNeighbor(int v){
        if(v != -1){
            //找第v行第一个非0列
            for(int col = 0 ; col < CurrentVertices ; col ++){
                if(Edge[v][col] > 0 && Edge[v][col] < max) return col;
            }
        }
        return -1;
    }
```
### 邻接表
相比邻接矩阵可以节省空间<br>
数组下标代表顶点，与其相连的链表存储的是边的另一个顶点<br>
如果边有权重，那就在每个节点后面加一个权重<br>
```C++
template<class NameType,class DistType>
int Graph<NameType,DistType>::GetFirstNeighbor(int v)
{
    if(v != -1)
    {
        Edge<DistType> *p=NodeTable[v].adj;
        if(p!=NULL) return p->dest;
    }
    return -1;
}

template<class NameType,class DistType>
int Graph<NameType,DistType>::GetNextNeighbor(int v1, int v2)
{
    if(v1 != -1)
    {
        Edge<DistType> *p=NodeTable[v1].adj;
        while(p!=NULL)
        {
            if(p->dest==v2 && p->link!=NULL)
                return p->link->dest;
            else p=p->link;
        }
    }
    return -1;
}
```
## 图的遍历
1.深度优先搜索<br>
从图中某个顶点V0出发,访问它,
然后选择一个V0邻接到的未被访问的一个邻接点V1出发深度优先遍历图,
当遇到一个所有邻接于它的结点都被访问过了的
结点U时,回退到前一次刚被访问过的拥有未被访问的
邻接点W,再从W出发深度遍历,……直到连通图中的所
有顶点都被访问过为止<br>
**以邻接表为例，以某一顶点出发寻找FirstNeighbor，然后以该邻接点为起点继续寻找它的FirstNeighbor，直到没有更多的邻接点**<br>
```C++
//主过程
template<NameType,DistType>
void Graph<NameType,DistType>::DFS()
{
    int *visited = new int[NumVertices];
    for(int i = 0 ; i < NumVertices ; i++) visited[i] = 0;
    DFS(0, visited);
    delete[] visited;
}
//子过程
template<NameType,DistType>
void Graph<NameType,DistType>::DFS(int v, visited[])
{
    cout<<GetValue(v)<<"";
    visited[v] = 1;
    int w = GetFirstNeighbor(v);
    while(w!=-1)
    {
        if(!visited[w]) DFS(w, visited);
        w = GetNextNeighbor(v, w);
    }
}
```
2.广度优先搜索<br>
从图中某顶点V0出发，在访问了V0之后依次访
问v0的各个未曾访问过的邻接点，然后分别从这些邻接
点出发广度优先遍历图，直至图中所有顶点都被访问到为止<br>
**以邻接表为例，以某一顶点出发寻找它的所有邻接点，然后以这些邻接点为起点继续寻找它们的邻接点，直到没有更多的邻接点**<br>
```C++
template<NameType,DistType>
void Graph<NameType,DistType>::BFS(int v){
    int* visited = new int[NumVertices];
    for(int i = 0 ; i < NumVertices ; i++) visited[i] = 0;
    cout<<GetValue(v)<<"";
    visited[v] = 1;
    queue<int> q;
    q.EnQueue(v); //队列用来记录正在访问的该层和上一层顶点
    while(!q.IsEmpty()){
        v=q.DeQueue();
        int w = GetFirstNeighbor(v);
        while(w!=-1){
            if(!visited[w]){
                cout<<GetValue(w)<<'';
                visited[w] = 1;
                q.EnQueue(w);
            }
            w = GetNextNeighbor(v, w);
        }
    }
    delete[] visited;
}
```
### 算法分析
用邻接表表示：O(n+e)<br>
用邻接矩阵表示：O($n^2$)<br>
## 连通分量
以上讨论的是对一个无向的连通图或一个强连通图的
有向图进行遍历，得到一棵深度优先或广度优先生成树.
但当无向图(以无向图为例)为非连通图时，从图的某一
顶点出发进行遍历(深度，广度)只能访问到该顶点所在
的最大连通子图(即连通分量)的所有顶点<br>
```C++
Template<NameType,DistType>
void Graph<NameType,DistType>::DFS(int v, int visited[]){
    void Graph<NameType,DistType>::components(){
        int* visited = new int[NumVertices];
        for(int i = 0; i < NumVertices; i ++){
            if(!visited[i]){
                DFS(i, visited);
                outputNewComponent();
            }
        }
        delete[] visited;
    }
```
## 最小生成树
生成树的代价：边的权重之和<br>
最小代价生成树：如何找到一个网络的最小生成树，即各边权的总和为最小的生成树<br>
两个算法：Kruskal算法和Prim算法<br>
都采用了逐步求解(Grandy)的策略<br>
### Kruskal算法
1.把无向图中的所有边排序<br>
2.一开始的最小生成树为T <- (V,TE), TE为空<br>
3.在E中选一条代价最小的边(u,v)加入T，满足(u,v)不和TE中已有的边构成回路<br>
4.一直到TE中加满n-1条边为止<br>
图用邻接矩阵表示<br>
图的顶点信息在顶点表Verticelist中<br>
边的条数为CurrentEdges<br>
取最小的边以及判别是否构成回路<br>
取最小的边利用最小堆<br>
```java
public void kruskal(){
    int edgesAccepted; DisjSet s; priorityQueue h;
    Vertex u,v; SetType uset,vset; Edge e;

    h = readGraphIntoHeapArray();
    h.buildHeap();
    s = new DisjSet(NUM_VERTICES);
    
    edgesAccepted = 0;
    while(edgesAccepted < NUM_VERTICES-1){
        e = h.deleteMin();
        uset = s.find(u);
        vset = s.find(v);
        if(uset != vset){
            edgesAccepted++;
            s.union(uset,vset);
        }
    }
}
```
#### 算法分析
O(e $log_2 e$ +e $log_2 n$ +$n^2$+n);<br>
### Prim算法
设：<br>
原图的顶点集合V(有n个)<br>
生成树的顶点集合U(最后也有n个)，一开始为空<br>
TE集合为{}<br>
1.U={1}(任意起始顶点)，TE={}<br>
2.每次生成(选择)一条边。这条边是所有边(u,v)中代价最小的边<br>
3.当U!=V
#### 算法分析
O($n^3$)<br>
具体实现：<br>
1.图采用邻接矩阵<br>
2.改进了实现效率(O($n^2$))<br>
```C++
void graph<string,float>::Prim(MinSpanTree&T){
    int NumVertices = VerticesList.last;
    float*lowcost = new float[NumVertices];
    int*nearvex = new int[NumVertices];

    for(int i = 1 ; i < NumVertices ; i ++){
        lowcost[i] = Edge[0][i];  nearvex[i] = 0;
    }
    nearvex[0] = -1;

    MSTEdgeNode e;

    for(int i = 1 ; i < NumVertices ; i ++){
        float min = MAXINT; int v = 0; //v是未加入树的顶点
        for(int j = 1 ; j < NumVertices ; j ++){
            if(nearvex[j] != -1 && lowcost[j] < min){
                v = j; min = lowcost[j];
            }
        }
        if(v){
            e.tail = nearvex[v];
            e.head = v;
            e.cost = lowcost[v];

            T.insert(e);
            nearvex[v] = -1;

            //如果新加入的顶点创造了比现有边代价更小的边，则更新
            for(int j = 1 ; j < NumVertices ; j ++){
                if(nearvex[j] != -1 && Edge[v][j] < lowcost[j]){
                    lowcost[j] = Edge[v][j]; nearvex[j] = v;
                }
            }
        }    
    }
}
```
时间复杂度:O($n^2$)<br>
## 最短路径
三种算法：<br>
1.Dijkstra算法:边上权值为非负情况的从一个节点到其他各节点的最短路径(单源最短路径)<br>
2.Bellman-Ford算法:边上权值为任意值的单源最短路径<br>
3.Floyd-Warshall算法:边上权值为任意值的所有顶点之间的最短路径<br>
### Dijkstra算法
**贪心思想**<br>
**不可能从一条长的路径绕一圈而绕出比段路径更短的路线**<br>
过程：按最短路径长度递增的次序产生最短路径<br>
一开始，在源点到直接有连线的诸顶点的path中找最小的，去掉该点，然后找从源点到余下点中最短的path
(这里可以不是直接连线，可以是经过前面已找到的最短path的顶点)<br>
```C++
//dist[]:距离值数组，path[]:路径数组
void Graph::shortestpath(int n, int v){
    for(int i = 0 ; i < n ; i ++){
        if(i != v && dist[i] < MAXNUM) path[i] = v;
        else path[i] = -1;
    }

    s[v] = 1 ; dist[v] = 0;
    for(int i = 0 ; i < n-1 ; i ++){
        float min = MAXNUM; int u = 0;
        for(int j = 0 ; j < n ; j ++){
            if(!s[j] && dist[j] < min) {u=j ; min = dist[j];}
        }

        s[u] = 1;
        //更新s值不为1的各顶点的最短路径
        for(int w = 0; w < n ; w ++){
            if(!s[w] && Edge[u][w] < MAXNUM && dist[u]+Edge[u][w] < dist[w]){
                dist[w] = dist[u] + Edge[u][w];
                path[w] = u;
            }
        }
    } 
}
```
时间复杂度:O($n^2$)<br>
### Bellman-Ford算法
**动态规划**<br>
递推公式：<br>
dist_1[u] = Edge[v][u]<br>
dist_k[u] = min{dist_k-1[u], dist_k-1[j]+Edge[j][u]}<br>
```C++
void Graph::BellaFord(int n, int v){
    for(int i = 0 ; i < n ; i ++){
        dist[i] = Edge[v][i];
        if(i != v && dist[i] < MAXNUM) path[i] = v;
        else path[i] = -1;
    }

    for(int k = 1 ; k < n ; k ++){
        for(int u = 0 ; u < n ; u ++){
            if(u != v){
                for(i = 0 ; i < n ; i ++){
                    if(i != u && Edge[u][i] < MAXNUM && dist[u] > Edge[i][u] + dist[i]){
                        dist[u] = dist[i] + Edge[i][u]; path[u] = i;
                    }
                }
            }
        }
    }
}
```
时间复杂度:O($n^3$)<br>
### Floyd-Warshall算法
方法：把有向图的每一个顶点作为源点，重复执行Dijkstra算法n次，执行时间为O($n^3$)<br>
在矩阵上作n-1次迭代<br>
```C++
void Graph::Alllength(int n)
    { 1. for(int i=0; i<n; i++)
        for(int j=0; j<n; j++)
        {  a[i][j]=Edge[i][j]; 
        if(i==j) a[i][j]=0;
        if(i< >j&&a[i][j]<MAXNUM) path[i][j]=i;
        else path[i][j]=0;
    }
    2. for(int k=0; k<n; k++)
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if( a[i][k]+a[k][j]<a[i][j] )
                {  a[i][j]=a[i][k]+a[k][j];
                path[i][j]=path[k][j];
                }
        }  
 }
```
三重循环O($n^3$)<br>
## 活动网络
AOV网络(Activity On Vertex)、AOE网络(Activity On Edge)<br>
### AOV网络
用顶点表示活动，用弧表示活动间的优先关系<br>
前驱、后继：从顶点i到顶点j有一条有向路径，则称i是j的前驱，j是i的后继<br>
直接前驱、直接后继：(i,j)为直连边<br>
**AOV网络中不应该出现有向环**<br>
·拓扑排序：有向图中满足任意顶点都在其后继顶点之前的顶点序列<br>
拓扑序列不是唯一的<br>
#### 拓扑排序算法
1.从图中选择一个入度为0的节点输出<br>
2.从图中删除此节点及其所有的出边<br>
3.反复执行以上步骤直到没有入度为0的节点为止<br>
```C++
void Grapg::Topologicalsort(){
    int top = -1;
    for(int i = 0 ; i < n ; i ++){
        if(count[i] == 0) {count[i] = top; top = i;} //可能只适合一开始只有两个入度为0的情况
    }
    for(int i = 0 ; i < n ; i ++){
        //top=-1说明没有入度为0的顶点了,top==count[0]
        if(top == -1) {cout<< "Network has a cycle"<<endl; return;}
        else{
            int j = top; top = count[top];
            cout<<j<<endl;
            Edge<float> *l = NodeTable[j].adj;
            while(l){
                int k = l.dest;
                if(--count[k] == 0){count[k] = top; top=k;}
                l = l->link;
            }
        }
    }
}
```
#### 算法分析
O(n+n+e)<br>
### AOE网络
**用边表示活动的网络**
顶点：表示事件，它的入边代表的活动已完成，它的出边代表的活动可以开始<br>
有向边：表示活动，边上的权表示完成一项活动需要的时间<br>
**与AOE网不同：有唯一的入度(出度)为0的开始(完成)节点**
#### 关键路径
具有从开始顶点到完成顶点的最长的路径<br>
#### 关键路径算法
**对事件而言：**<br>
Ve[i]——表示事件Vi可能的最早发生时间，定义为从源点V0->Vi的最长路径长度<br>
Vl[i]——表示时间Vi允许的最晚发生时间，最晚时间=Ve[n-1]-Vi->Vn-1的最长路径长度<br>
**对活动而言：**<br>
e[k]表示活动ak=<Vi,Vj>的可能的最早开始时间，e[k]=Ve[i]<br>
l[k]表示活动ak=<Vi,Vj>的允许的最迟开始时间，l[k]=Vl[j]-dur(<i,j>)<br>
l[k]-e[k]称为松弛时间<br>
l[k] == e[k]表示活动ak是没有时间余量的关键活动<br>
**步骤**
1.求各事件的可能最早发生时间，从Ve[0]=0开始向前推进求其他事件的Ve<br>
2.求各事件的允许最晚发生时间，从Vl[n-1]=Ve[n-1]开始，反向递推<br>
```C++
void Graph::CriticalPath(){
    int i, j; int p, k; float e, l;
    float *Ve = new float[n]; float *Vl = new float[n];
    for(i = 0 ; i < n ; i ++) Ve[i] = 0;
    for(i = 0 ; i < n ; i ++){
        Edge<float> *p = NodeTable[i].adj;
        while(p != NULL){
            k = p.dest;
            if(Ve[i] + p.cost > Ve[k]) Ve[k] = Ve[i] + p.cost;
            p = p.link;
        }
    }
    for(i = 0 ; i < n ; i ++) Vl[i] = Ve[n-1];
    for(i = n-2 ; i >= 0 ; i --){
        p = NodeTable[i].adj;
        while(p != NULL){
            k = p.dest;
            if(Vl[k] - p.cost < Vl[i]) Vl[i] = Vl[k] - p.cost;
            p = p.link;
        }
    }
    for(i = 0 ; i < n ; i ++){
        p = NodeTable[i].adj;
        while(p != NULL){
            k = p.dest;
            e = Ve[i]; l = Vl[k] - p.cost;
            if(l == e){
                cout<<“<“<<i<<“”,”<<k<<“>”<<“is critical Activity”<<endl
            }
            p = p.link;
        }
    }
}
```
#### 算法分析
O(n+e)<br>