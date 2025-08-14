public class AVLDeleteExercise {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("初始樹：");
        tree.printTree();

        // 刪除葉子節點（20）
        tree.delete(20);
        System.out.println("\n刪除葉子節點 20：");
        tree.printTree();

        // 刪除只有一個子節點的節點（30）
        tree.delete(30);
        System.out.println("\n刪除只有一個子節點的節點 30：");
        tree.printTree();

        // 刪除有兩個子節點的節點（70）
        tree.delete(70);
        System.out.println("\n刪除有兩個子節點的節點 70：");
        tree.printTree();

        System.out.println("\n是否為有效 AVL 樹: " + tree.isValidAVL());
    }
}
