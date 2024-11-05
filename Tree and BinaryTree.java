节点类
public class BinaryNode(){
  private int element;
  private BinaryNode left;
  private BinaryNode right;
  public BinaryNode(int element, BinaryNode left, BinaryNode right){
    this.element = element;
    this.left = left;
    this.right = right;
  }
}

先序遍历
public void preorder(BinaryNode root){
  if(root != null){
  System.out.print(root.element);
  }
  if(root.left != null){
    preorder(root.left);
  }
  if(root.right != null){
    preorder(root.right);
  }
}
