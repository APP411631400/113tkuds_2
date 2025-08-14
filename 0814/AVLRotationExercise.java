public class AVLRotationExercise {
    public static void main(String[] args) {
        System.out.println("測試：右旋");
        AVLNode root1 = new AVLNode(30);
        root1.left = new AVLNode(20);
        root1.left.left = new AVLNode(10);
        root1.updateHeight();
        root1.left.updateHeight();
        root1 = AVLRotations.rightRotate(root1);
        printTree(root1);

        System.out.println("\n測試：左旋");
        AVLNode root2 = new AVLNode(10);
        root2.right = new AVLNode(20);
        root2.right.right = new AVLNode(30);
        root2.updateHeight();
        root2.right.updateHeight();
        root2 = AVLRotations.leftRotate(root2);
        printTree(root2);

        System.out.println("\n測試：左右旋");
        AVLNode root3 = new AVLNode(30);
        root3.left = new AVLNode(10);
        root3.left.right = new AVLNode(20);
        root3.left.updateHeight();
        root3.updateHeight();
        root3.left = AVLRotations.leftRotate(root3.left);
        root3 = AVLRotations.rightRotate(root3);
        printTree(root3);

        System.out.println("\n測試：右左旋");
        AVLNode root4 = new AVLNode(10);
        root4.right = new AVLNode(30);
        root4.right.left = new AVLNode(20);
        root4.right.updateHeight();
        root4.updateHeight();
        root4.right = AVLRotations.rightRotate(root4.right);
        root4 = AVLRotations.leftRotate(root4);
        printTree(root4);
    }

    public static void printTree(AVLNode node) {
        if (node != null) {
            printTree(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printTree(node.right);
        }
    }
}
