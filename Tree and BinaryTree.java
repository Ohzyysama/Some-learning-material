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
建立一棵二叉树，并输出前序、中序、后序遍历结果。
public class BinTree {
    private char element;
    BinTree left, right;
  
    public BinTree() {
    }
  
    BinTree(char element, BinTree left, BinTree right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
  
    public static BinTree CreateBT(char[] pres, char[] ins){
        BinTree root = new BinTree();
        root = CreateBT(0, ins.length-1, 0, pres.length-1, ins, pres);
        return root;
    }

    public static BinTree CreateBT(int startin, int endin, int startpre, int endpre, char [] ins, char [] pres){
        if(startin > endin || startpre > endpre){
            return null;
        }
        BinTree root = new BinTree();
        root.element = pres[startpre];

        for(int i = startin ; i <= endin ; i ++){
            if(ins[i] == pres[startpre]){
                root.left = CreateBT(startin, i-1, startpre+1, startpre+(i-startin), ins, pres);
                root.right = CreateBT(i+1, endin, startpre+(i-startin)+1, endpre, ins, pres);
            }
        }
        return root;
    }
  
    public static StringBuilder PreOrderTraversal(BinTree root) {
        StringBuilder result = new StringBuilder();
        if(root != null){
            result.append(root.element);
            if(root.left != null){
                result.append(PreOrderTraversal(root.left)) ;
            }
            if(root.right != null){
                result.append(PreOrderTraversal(root.right)) ;
            }
        }
        return result;
    }
  
    public static StringBuilder InOrderTraversal(BinTree root) {
        StringBuilder result = new StringBuilder();
        if(root != null){
            if(root.left != null){
                result.append(InOrderTraversal(root.left));
            }
            result.append(root.element);
            if(root.right != null){
                result.append(InOrderTraversal(root.right));
            }
        }
        return result;
    }
  
    public static StringBuilder PostOrderTraversal(BinTree root) {
        StringBuilder result = new StringBuilder();
        if(root != null){
            if(root.left != null){
                result.append(PostOrderTraversal(root.left));
            }
            if(root.right != null){
                result.append(PostOrderTraversal(root.right));
            }
            result.append(root.element);
        }
        return result;
    }

}
