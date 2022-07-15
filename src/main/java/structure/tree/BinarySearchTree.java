package src.main.java.structure.tree;

import java.util.Scanner;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {

    static class BinaryNode {
        int data;
        BinaryNode left;
        BinaryNode right;
        BinaryNode parent;  //相对lite版，新增字段

        public BinaryNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

	// 查找
	public BinaryNode find(BinaryNode root, int key) {
		BinaryNode current = root;
		while (current != null) { //不为空的话，就一直往下查找。
			if (key < current.data) {
				current = current.left;
			} else if (key > current.data) {
				current = current.right;
			} else {
				return current;
			}
		}
		return null;
	}

    // 插入
	public void insert(BinaryNode root, int data) {
		if (root.data < data) {  //比根节点大 我们要放到右子树
			if (root.right != null) {
				insert(root.right, data);
			} else {
				BinaryNode newNode = new BinaryNode(data);
				newNode.parent = root; //多了这一步，类似双向指针
				root.right = newNode;
			}
		} else { //比根节点小 我们要放到左子树
			if (root.left != null) {
				insert(root.left, data);
			} else {
				BinaryNode newNode = new BinaryNode(data);
				newNode.parent = root;  //多了这一步，类似双向指针
				root.left = newNode;
			}
		}
	}

    // 删除
	public BinaryNode remove(BinaryNode root, int data) {

        // 0.拿到待删除结点，并校验是否存在
		BinaryNode delNode = find(root, data);
		if (delNode == null) {
			System.out.println("要删除的值不在树中");
			return root;
		}

		// 1.删除的点没有左右子树
		if (delNode.left == null && delNode.right == null) {
		    //1.1 删除的点是独根
			if (delNode == root) {
				root = null;
            //1.2 删除的点是 右子节点
			} else if (delNode.parent.data < delNode.data) {
				delNode.parent.right = null;
                delNode.parent = null; //GC
            //1.3 删除的点是 左子节点
			} else {
				delNode.parent.left = null;
                delNode.parent = null;//GC
			}
        // 2.删除的节点有两颗子树
		} else if (delNode.left != null && delNode.right != null) {
			BinaryNode successor = finSuccessor(delNode); // 先找到后继节点。一定不会是其本身，因为进来前已判断，它一定同时拥有左右子节点。
			//2.1 ‘后继节点’ 和 ‘删除节点’ 进行交换。
            //2.1.1 先看后继节点的左边。
            //      首先，后继节点的左节点是肯定为空的。关联 后继结点、待删点左子树
            //a)结点关联操作
			successor.left = delNode.left; // 后继的左指针 指向 待删结点左子树
			successor.left.parent = successor; // 删除点的左子节点的parent指针 指向 后继节点
			//2.1.2 再来看后继节点的右边。
            // 如果 ‘后继节点的parent’ 不等于 ‘待删点’
            // 那么，就需要  关联 后继结点、待删点右子树
            if (successor.parent != delNode) {
                //b)结点关联操作
                if(successor.right != null){ // 后继节点有右边,这其实就是下面情况3的第一种
                    successor.right.parent = successor.parent;
                    successor.parent.left = successor.right;
                }else{ // 如果后继节点没有右边,那其实就是情况1，它没有左右子树
                    successor.parent.left = null;//注意原来的后继节点上的引用要删掉,否则会死循环
                }
                //c)结点关联操作
                successor.right = delNode.right;
                successor.right.parent = successor;
            }

			// 2.2 后继节点的替换做完后，接下来就要删除节点了
			if (delNode == root) { //待删点为根结点
				successor.parent = null;
				root = successor; //d)后继结点作为新的根结点
				return root;
			}

            //d)结点关联操作
			successor.parent = delNode.parent;
			if (delNode.data > delNode.parent.data) { //待删点是右子节点，则关联为右子树
				delNode.parent.right = successor;
			} else { //待删点是左子节点，则关联为左子树
				delNode.parent.left = successor;
			}
        // 3.待删节点只有一颗子树
		} else {
            //3.1 只有右子树
			if (delNode.right != null) {
			    //3.1.1 待删除节点为根结点
				if (delNode == root) {
					root = delNode.right;
                    delNode.right = null; //GC
                    delNode.parent = null;//GC
					return root;
				}
                // 把待删点的右子节点的parent指针 指向 待删结点的父结点
				delNode.right.parent = delNode.parent;

				//3.1.2 关联父节点的左右子树
                // 待删结点 关联为左子树
				if (delNode.data < delNode.parent.data) {
					delNode.parent.left = delNode.right;
                // 待删结点 关联为右子树
				} else {
					delNode.parent.right = delNode.right;
				}
                delNode.right = null; //GC
                delNode.parent = null;//GC
            //3.2 只有左子树
			} else {
				if (delNode == root) {
					root = delNode.left;
                    delNode.left = null;  //GC
                    delNode.parent = null;//GC
					return root;
				}
				delNode.left.parent = delNode.parent;
				if (delNode.data < delNode.parent.data) {
					delNode.parent.left = delNode.left;
				} else {
					delNode.parent.right = delNode.left;
				}
                delNode.left = null;  //GC
                delNode.parent = null;//GC
			}
		}
		return root;
	}

	// 查找 node的后继节点
    public BinaryNode finSuccessor(BinaryNode node) {
        if (node.right == null) { // 表示没有右边 那就没有后继
            return node;
        }

        BinaryNode cur = node.right;
        BinaryNode pre = node.right; // 开一个额外的指针 用来指向待返回的后继节点。因为当我们找到cur为空的时候，那么其实需要返回的是上一个节点
        while (cur != null) {
            pre = cur;
            cur = cur.left; // 注意后继节点是要往左边找，因为右边的肯定比左边的大，我们要找的是第一个比根节点小的，所以只能往左边
        }
        return pre; // 因为cur会变成null，实际我们是要cur的上一个点，所以就是pre来代替
    }

    //中序 排序
	public void inOrder(BinaryNode root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data);
			inOrder(root.right);
		}
	}

	/**
	 * 
	 * 测试用例
     * 插入：15 10 19  8 13 16 28  5  9 12 14 20 30   -1
	 * 删除：15  8  5 10 12 19 16 14 30  9 13 20 28
	 * @param args
	 */
	public static void main(String[] args) {

		BinarySearchTree binarySearchTree = new BinarySearchTree();

        //插入

		Scanner cin = new Scanner(System.in);
		System.out.println("二叉搜索树,假定不存重复的子节点,重复可用链表处理，请注意~~");
		System.out.println("请输入根节点:");
		int rootData = cin.nextInt();

		BinaryNode root = new BinaryNode(rootData);

		int t = 1;
		System.out.println("请输入第" + t + "个节点:输入-1表示结束");
		while (true) {
			int data = cin.nextInt();
			if (data == -1) {
				break;
			}
			binarySearchTree.insert(root, data);
			t++;
			System.out.println("请输入第" + t + "个节点:输入-1表示结束");
		}

		binarySearchTree.inOrder(root);


		//展示
		ShowTree.show(root);  //找的别人写的打印二叉树形结构，感觉还不错，可以更加清晰



        //删除
		System.out.println("删除测试:");
		while(true) {
			System.out.println("请输入要删除的节点：-1表示结束");
			int key = cin.nextInt();
			root = binarySearchTree.remove(root, key);

			//展示
			ShowTree.show(root);

			if(root == null) {
				System.out.println("树已经没有数据了~~");
				break;
			}
		}


	}



	public static class ShowTree {
		//展示
		public static void show(BinaryNode root) {
			if (root == null) {
				System.out.println("EMPTY!");
				return ;
			}

			//step1：初始化结果数组
			// 得到树的深度
			int treeDepth = getTreeDepth(root);

			// n乘3，再减1。作为整个二维数组的高度
			int arrayHeight = treeDepth * 2 - 1;
			// 最后一行的宽度为: 2的（n - 1）次方乘3，再加1。
			// 最后一行的宽度作为整个二维数组的宽度
			int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;

			// 用一个字符串数组来存储每个位置应显示的元素
			String[][] res = new String[arrayHeight][arrayWidth];
			// 对数组进行初始化，默认为一个空格
			for (int i = 0; i < arrayHeight; i++) {
				for (int j = 0; j < arrayWidth; j++) {
					res[i][j] = " ";
				}
			}

			//step2：将数据存储到结果数组中。
			// 从根节点开始，递归处理整个树
			writeArray(root, 0, arrayWidth / 2, res, treeDepth);

			//step3：打印数据
			// 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
			for (String[] line : res) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < line.length; i++) {
					sb.append(line[i]);
					if (line[i].length() > 1 && i <= line.length - 1) {
						i += line[i].length() > 4 ? 2 : line[i].length() - 1;
					}
				}
				System.out.println(sb.toString());
			}
		}
		// 获得树的层数
		public static int getTreeDepth(BinaryNode root) {
			return root == null ?
					0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
		}
		// 将数据存储到 用于展示结果的二维数组 中
		private static void writeArray(BinaryNode currNode,
								int rowIndex, int columnIndex,
								String[][] res, int treeDepth) {
			// 保证输入的树不为空
			if (currNode == null)
				return;
			// 先将当前节点保存到二维数组中
			res[rowIndex][columnIndex] = String.valueOf(currNode.data);

			// 计算当前位于树的第几层
			int currLevel = ((rowIndex + 1) / 2);
			// 若到了最后一层，则返回
			if (currLevel == treeDepth) {
				return;
			}
			// 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
			int gap = treeDepth - currLevel - 1;

			// 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
			if (currNode.left != null) {
				res[rowIndex + 1][columnIndex - gap] = "/";
				writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
			}

			// 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
			if (currNode.right != null) {
				res[rowIndex + 1][columnIndex + gap] = "\\";
				writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
			}
		}
	}
}

/*
root 15  第二层 10 19   第三层 8 13 16 28   第四层 5 9 12 14 20 30
	        15
         /     \
      10          19
    /   \       /   \
  8       13  16      28
 / \     / \         / \
5   9   12  14      20  30



root 5   left 3 2 4 1    right 7 6 8 9
            5
         /     \
      3           7
    /   \       /   \
  2       4   6       8
 /                     \
1                       9


root 5   left 4 3 2 1    right 6 7 8 9
                    5
                /       \
            4               6
         /                     \
      3                           7
    /                               \
  2                                   8
 /                                     \
1                                       9

**/