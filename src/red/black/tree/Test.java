package red.black.tree;
public class Test {
    public static void main(String[] args) {
        RedBlack rb= new RedBlack();
        rb.inset(11);
        rb.inset(2);
        rb.inset(14);
        rb.inset(1);
        rb.inset(7);
        rb.inset(15);
        rb.inset(5);
        rb.inset(8);
        rb.inset(4);
        rb.remove(1);
        rb.display();
        
    }
}
