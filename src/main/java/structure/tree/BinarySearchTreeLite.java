package src.main.java.structure.tree;

/**
 * 二叉搜索树
 */
public class BinarySearchTreeLite {

    private class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;

        //TreeNode parent;            //红黑树新增属性  父节点
        //boolean red;		          //红黑树新增属性   false表示黑，true表示红

        //parent.parent;       //爷爷
        //parent.parent.left   //左边的叔叔
        //parent.left          //兄弟姐妹

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;

//            this.parent = null;    //红黑树新增属性
//            this.red = true;        //红黑树新增属性

        }
    }

	//插入的时候每次都是和根结点比较。一直要找到它应该插入的位置。
	//肯定会插在叶子结点。那么其实大家可以看到 插入其实就是查找。 默认root不会为空
	public void insert(TreeNode root, int data) {
		// 可作一些判空校验。这里就从简不写了。
		// if(root == null) {}

		//比根节点大 我们要放到右子树
		if(root.data < data) {
			if(root.right == null) {
				root.right = new TreeNode(data);//右子树，为空。插到空位
			} else {
				insert(root.right, data); //右子树，不为空。递归 传入右子树的根结点;
			}
		//比根节点小 我们要放到左子树
		}else {
			if(root.left == null) {
				root.left = new TreeNode(data);//左子树，为空。插到空位
			}else {
				insert(root.left, data);  //左子树，不为空。递归 传入左子树的根结点;
			}
		}
	}
	
	public void find(TreeNode root, int data) {
		if(root != null) {
			if(root.data < data) {
				find(root.right, data);
			}else if(root.data > data) {
				find(root.left, data);
			}else {
				System.out.println("找到了:"+root.data);
				return;
			}
		}
	}

	public void pre() {	
		
	}

	public void post() {	
		
	}

	public void in(TreeNode root) {	 //中序遍历
		if(root != null) {
			in(root.left);
			System.out.print(root.data + " ");
			in(root.right);
		}
	}


	public static void main(String[] args) {
		//快速排序，归并排序，二叉树排序
		int [] data= {0,5,9,1,2,3,10};

        BinarySearchTreeLite bst= new BinarySearchTreeLite();
        bst.create(data,bst);

	}

    void create(int [] data, BinarySearchTreeLite bst){
        //第一个点作为根结点
        TreeNode root = new TreeNode(data[0]);

        //1.插入数据
        for(int i = 1 ; i < data.length ; i ++) {
            bst.insert(root, data[i]);
        }

        //2.中序遍历
        bst.in(root);
        System.out.println();

        //3.查找
        bst.find(root,1);
    }

}
