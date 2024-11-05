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
  preorder(root.left);
  preorder(root.right);
  }
}
中序遍历
public void inorder(BinaryNode root){
  if(root != null){
  inorder(root.left);
  System.out.print(root.element);
  inorder(root.right);
  }
}
后序遍历
public void postorder(BinaryNode root){
  if(root != null){
  postorder(root.left);
  postorder(root.right);
  System.out.print(root.element);
  }
}
