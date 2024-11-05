# Tree
A tree T is a collections of nodes(element).<br>
## Some notions:<br>
Degree of an elements(nodes): the number of children it has<br>
Degree of a tree: the maximum of its element degrees<br>
Leaf: element whose degree is 0<br>
Branch: element whose degree is not 0<br>
# Binary Tree
特例：二叉树(Binary Trees)<br>
当一棵二叉树非空时，每个节点最多有两个子节点，称为左子节点和右子节点。<br>
节点类：(见代码)<br>
## 二叉树的遍历
二叉树的遍历按根节点遍历顺序分为三种：先序(preorder), 中序(inorder), 后序(postorder)。<br>
具体遍历见代码文件(使用递归遍历)。<br>
思考：如何根据先序和中序遍历结果构建一棵二叉树？并对该树进行遍历。(见代码)<br>
# Binary tree <——> Forest
森林：左子女右兄弟<br>
!<https://github.com/Ohzyysama/Tree/blob/main/p1.png>
