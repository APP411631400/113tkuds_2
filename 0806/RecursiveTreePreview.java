import java.io.File;
import java.util.*;

public class RecursiveTreePreview {
    public static void main(String[] args) {
        File root = new File("C:/example"); 
        System.out.println("總檔案數: " + countFiles(root));

        Menu menu = new Menu("主選單", Arrays.asList(
            new Menu("檔案", Arrays.asList(
                new Menu("新建", null),
                new Menu("打開", null),
                new Menu("關閉", null)
            )),
            new Menu("編輯", Arrays.asList(
                new Menu("複製", null),
                new Menu("貼上", null),
                new Menu("刪除", null)
            )),
            new Menu("幫助", null)
        ));
        printMenu(menu, 0);

        Object[] nested = {1, new Object[]{2, 3, new Object[]{4, 5}}, 6};
        List<Object> flat = new ArrayList<>();
        flatten(nested, flat);
        System.out.println("展平成果: " + flat);

        List<Object> list = Arrays.asList(
            1,
            Arrays.asList(2, Arrays.asList(3, Arrays.asList(4))),
            5,
            Arrays.asList(6, 7)
        );
        System.out.println("最大深度: " + maxDepth(list));
    }

    public static int countFiles(File f) {
        if (!f.exists()) return 0;
        if (f.isFile()) return 1;
        int sum = 0;
        File[] files = f.listFiles();
        if (files != null) {
            for (File c : files) {
                sum += countFiles(c);
            }
        }
        return sum;
    }

    static class Menu {
        String name;
        List<Menu> children;
        Menu(String name, List<Menu> children) {
            this.name = name;
            this.children = children;
        }
    }

    public static void printMenu(Menu m, int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("- " + m.name);
        if (m.children != null) {
            for (Menu c : m.children) {
                printMenu(c, level + 1);
            }
        }
    }

    public static void flatten(Object[] arr, List<Object> res) {
        for (Object o : arr) {
            if (o instanceof Object[]) {
                flatten((Object[]) o, res);
            } else {
                res.add(o);
            }
        }
    }

    public static int maxDepth(Object o) {
        if (!(o instanceof List)) return 1;
        int max = 1;
        for (Object e : (List<?>) o) {
            max = Math.max(max, 1 + maxDepth(e));
        }
        return max;
    }
}

