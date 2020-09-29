package leetcode;

import edu.princeton.cs.algs4.Stack;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的前序、中序、后序遍历的迭代写法
 * 利用在栈中增加空指针的方法，作为标记，遇到空指针再输出到结果中
 * 因为是使用栈来模拟，所以压入栈的顺序和想要遍历的顺序相反
 * 在压入根（中）节点的时候压入一个null，下一次遇到null就加到结果中
 * 实现过程中每个点都压入栈两次，第二次遇到标记再输出
 */
public class BinaryTreeTraversal {
    /**
     * 先序
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> res = new LinkedList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode node;
        while (!stack.isEmpty()){
            node = stack.pop();
            if(node != null){
                // 先序 中左右 --> 入栈 右左中
                if(node.right != null){
                    stack.push(node.right);
                }
                if(node.left != null){
                    stack.push(node.left);
                }
                // 压入中的同时压入null
                stack.push(node);
                stack.push(null);
            }
            else {
                node = stack.pop();
                res.add(node.val);
            }
        }
        return res;
    }

    /**
     * 中序
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new LinkedList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode node;
        while (!stack.isEmpty()){
            node = stack.pop();
            if(node != null){
                // 中序 左中右 --> 入栈 右中左
                if(node.right != null){
                    stack.push(node.right);
                }
                // 压入中的同时压入null
                stack.push(node);
                stack.push(null);
                if(node.left != null){
                    stack.push(node.left);
                }
            }
            else {
                node = stack.pop();
                res.add(node.val);
            }
        }
        return res;
    }

    /**
     * 后序
     * @param root
     * @return list 结果集
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node;
        stack.push(root);
        while (!stack.isEmpty()){
            node = stack.pop();
            // 非空节点不输出，相当于以这个节点为根进行递归
            if(node != null){
                // 按想要的顺序的逆序来入栈
                // 后序遍历 左右中 --> 入栈 中右左
                // 中节点，压入加一个null指针
                stack.push(node);
                stack.push(null);
                if(node.right != null){
                    stack.push(node.right);
                }
                if(node.left != null){
                    stack.push(node.left);
                }
            }
            // 遇到null，输出到结果集
            // 第二次遇到了，出栈并存入结果集
            else {
                node = stack.pop();
                res.add(node.val);
            }
        }
        return res;
    }
}
