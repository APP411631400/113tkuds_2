public class AVLBasicExercise {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);
        tree.insert(35);
        tree.insert(50);

        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 60: " + tree.search(60));
        System.out.println("樹的高度: " + tree.getHeight());
        System.out.println("是否為有效的 AVL 樹: " + tree.isValidAVL());
        tree.printTree();
    }
}
