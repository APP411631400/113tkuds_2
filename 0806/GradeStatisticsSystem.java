public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        double sum = 0;
        int max = scores[0];
        int min = scores[0];
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;
            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }
        double average = sum / scores.length;
        int aboveCount = 0;
        for (int score : scores) {
            if (score > average) aboveCount++;
        }
        System.out.printf("平均分: %.2f%n", average);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("A 等級人數: " + countA);
        System.out.println("B 等級人數: " + countB);
        System.out.println("C 等級人數: " + countC);
        System.out.println("D 等級人數: " + countD);
        System.out.println("F 等級人數: " + countF);
        System.out.println("高於平均分的人數: " + aboveCount);
        System.out.println("成績報表:");
        for (int i = 0; i < scores.length; i++) {
            System.out.println("學生" + (i + 1) + ": " + scores[i] + " (" + getGrade(scores[i]) + ")");
        }
    }

    private static String getGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
}

