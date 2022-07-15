package src.main.java.structure.tree;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树
 * */
public class BinaryTree {


    private static class TreeNode{

        private char data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(char data, TreeNode left, TreeNode right) {
            super();
            this.setData(data);
            this.setLeft(left);
            this.setRight(right);
        }
        public char getData() {
            return data;
        }
        public void setData(char data) {
            this.data = data;
        }
        public TreeNode getLeft() {
            return left;
        }
        public void setLeft(TreeNode left) {
            this.left = left;
        }
        public TreeNode getRight() {
            return right;
        }
        public void setRight(TreeNode right) {
            this.right = right;
        }
        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("TreeNode{");
            sb.append("data=").append(data);
            sb.append('}');
            return sb.toString();
        }
    }

	public void print(TreeNode node){
		System.out.print(node.getData());
	}

    //前序遍历(根，放到最前);  根(输出)  左  右
	public void pre(TreeNode root){ //时间复杂度？O(n)、N^2; 每个点访问两次，O(2*n)=>O(n);
		print(root);

		if(root.getLeft() != null){
			pre(root.getLeft());	//认为是子树。可以看成分解子问题;
		}

		if(root.getRight() != null){
			pre(root.getRight());
		}
	}

    //中序遍历(根，放到中间);  左  根(输出)  右
	public void in(TreeNode root){
		if(root.getLeft() != null){
			in(root.getLeft());	//认为是子树,分解子问题；
		}

		print(root);

		if(root.getRight() != null){
			in(root.getRight());
		}
	}

    //后序遍历(根，放到最后);   左  右  根(输出)
	public void post(TreeNode root){
		if(root.getLeft() != null){
			post(root.getLeft());	//认为是子树,分解子问题；
		}
		if(root.getRight() != null){
			post(root.getRight());
		}
		print(root);
	}

    /**
     * 层次遍历
     *
     * @param root
     * @return
     */
    public List<TreeNode> levelOrder(TreeNode root) {
        /**
         * 层次遍历使用到了广度优先搜索，技巧：深度优先用递归，广度优先用队列。
         */
        Queue<TreeNode> queue = new LinkedList<>();

        List<TreeNode> list = new LinkedList<>();
        queue.add(root);
        while (queue.size()>0){
            //出一个，进n个

            //出一个
            TreeNode node = queue.poll();

            if(node != null){
                print(node);
                list.add(node);

                //进两个
                TreeNode left = node.left;
                TreeNode right = node.right;
                //可加上left、right 的判空
                queue.add(left);
                queue.add(right);
            }

        }
        return list;
    }

    public static void main(String[] args) {
        //init data。从叶子结点开始逐层往上建
        TreeNode D = new TreeNode('D', null, null);
        TreeNode H = new TreeNode('H', null, null);
        TreeNode K = new TreeNode('K', null, null);
        TreeNode C = new TreeNode('C', D, null);
        TreeNode G = new TreeNode('G', H, K);
        TreeNode B = new TreeNode('B', null, C);
        TreeNode F = new TreeNode('F', G, null);
        TreeNode E = new TreeNode('E', null, F);
        TreeNode A = new TreeNode('A', B, E); //root

        BinaryTree binaryTree = new BinaryTree();

        System.out.println("前序");
        binaryTree.pre(A);
        System.out.println();

        System.out.println("中序");
        binaryTree.in(A);
        System.out.println();

        System.out.println("后序");
        binaryTree.post(A);
        System.out.println();

        System.out.println("层次排序");
        binaryTree.levelOrder(A);
    }
}
