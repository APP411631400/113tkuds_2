
public class AVLRotations {
    
    // 右旋操作
    // 時間複雜度: O(1), 空間複雜度: O(1)
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        // 執行旋轉
        x.right = y;
        y.left = T2;
        
        // 更新高度
        y.updateHeight();
        x.updateHeight();
        
        return x; // 新的根節點
    }
    
    // 左旋操作
    // 時間複雜度: O(1), 空間複雜度: O(1)
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        
        // 執行旋轉
        y.left = x;
        x.right = T2;
        
        // 更新高度
        x.updateHeight();
        y.updateHeight();
        
        return y; // 新的根節點
    }

    public static void main(String[] args) {
        System.out.println("測試 AVL 左右旋轉");

        // 建立測試用的 AVLNode 結構
        AVLNode root = new AVLNode(30);
        root.right = new AVLNode(40);

        // 使用 AVLRotations 執行左旋
        root = AVLRotations.leftRotate(root);

        // 印出結果
        System.out.println("旋轉後的新根節點: " + root.data);
        System.out.println("節點高度: " + root.height);
        System.out.println("平衡因子: " + root.getBalance());
    }
}


