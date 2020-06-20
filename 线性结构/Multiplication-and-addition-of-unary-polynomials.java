import java.util.Scanner;

public class Poly_Add_Mutiply
{
    // 多项式加法与乘法 -- https://blog.csdn.net/liyuanyue2017/article/details/83244253
    // 用链表实现
    // 项term 类，节点
    static class poly_term
    {
        int coff; // 系数
        int mu; // 指数
        poly_term next;
        poly_term()
        {
            coff = 0;
            mu = 0;
            next = null;
        }
        poly_term(int coff, int mu)
        {
            this.coff = coff;
            this.mu = mu;
            next = null;
        }
    }
    // 整个多项式，链表
    static class poly_list
    {
        poly_term head;
        int num;
        poly_list()
        {
            head = new poly_term();
            num = 0;
        }
        void add_point (poly_term term)
        {
            head.next = term;
        }
        void print_list()
        {
            poly_term temp = head;
            temp = temp.next;
            while(temp.next != null)
            {
                System.out.print(temp.coff + "x^(" + temp.mu + ") + ");
                temp = temp.next;
            }
            System.out.print(temp.coff + "x^(" + temp.mu + ")");
            System.out.println();
        }
    }
    static poly_list Read_Poly_List()
    {
        poly_list list = new poly_list();
        poly_term temp = list.head;
        Scanner in = new Scanner(System.in);
        int num_of_poly = in.nextInt();
        list.num = num_of_poly;
        for(int i = 0; i < num_of_poly; i++)
        {
            int coefficient = in.nextInt();
            int miu = in.nextInt();
            poly_term term = new poly_term(coefficient, miu);
            temp.next = term;
            temp = temp.next;
        }
        return list;
    }
    static poly_list Addition(poly_list l1, poly_list l2)
    { //加法
        poly_list res = new poly_list();
        poly_term res_node = res.head;
        poly_term l1_node = l1.head.next;
        poly_term l2_node = l2.head.next;
        while(l1_node != null && l2_node != null)
        {
            poly_term temp = new poly_term();
            // 取大的指数
            if(l1_node.mu > l2_node.mu)
            {
                temp.mu = l1_node.mu;
                temp.coff = l1_node.coff;
                l1_node = l1_node.next;
            }
            else if(l1_node.mu < l2_node.mu)
            {
                temp.mu = l2_node.mu;
                temp.coff = l2_node.coff;
                l2_node = l2_node.next;
            }
            else if(l1_node.mu == l2_node.mu)
            {
                temp.mu = l1_node.mu;
                temp.coff = l1_node.coff + l2_node.coff;
                l1_node = l1_node.next;
                l2_node = l2_node.next;
            }
            res_node.next = temp;
            res_node = res_node.next;
        }
        if(l1_node != null)
        {
            res_node.next = l1_node;
        }
        else if(l2_node != null)
        {
            res_node.next = l2_node;
        }
        return res;
    }
    static poly_list Mutiply(poly_list l1, poly_list l2)
    { //乘法
        poly_list res = new poly_list();
        poly_term l1_node = l1.head.next;
        poly_term l2_node = l2.head.next;
        for (; l1_node != null; l1_node = l1_node.next)
        {
            for (l2_node = l2.head.next; l2_node != null; l2_node = l2_node.next)
            {
                poly_term temp = new poly_term();
                temp.coff = l1_node.coff * l2_node.coff;
                temp.mu = l1_node.mu +  l2_node.mu;
                poly_list temp_list = new poly_list();
                temp_list.add_point(temp);
                res = Addition(res, temp_list);
            }
        }
        return res;
    }
    public static void main(String[] args)
    {
        poly_list list_1 = new poly_list();
        poly_list list_2 = new poly_list();
        poly_list list_add = new poly_list();
        poly_list list_mul = new poly_list();
        list_1 = Read_Poly_List();
        list_1.print_list();
        list_2 = Read_Poly_List();
        list_2.print_list();
        System.out.println("Addition is:");
        list_add = Addition(list_1, list_2);
        list_add.print_list();
        System.out.println("Mutiply is:");
        list_mul = Mutiply(list_1, list_2);
        list_mul.print_list();
    }
}
