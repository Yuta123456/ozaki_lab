import java.util.Arrays;
import java.util.Random;
public class p3_1 {
    public static void main(final String[] args) {
        final int[] true_count = new int[10000];
        for (int i = 0; i < true_count.length; i++) {
            final Random r = new Random(); // 乱数生成器を準備
            final boolean[] result = new boolean[100]; // 生成した乱数を代入する配列
            for (int j = 0; j < result.length; j++) {
                result[j] = r.nextInt(3) >= 2;
            }
            for (int j = 0; j < result.length; j++) {
                if (result[j]) {
                    true_count[i] += 1;
                }
            }
        }
        java.util.Arrays.sort(true_count);
        final double mean = (Arrays.stream(true_count).sum() * 1.0) / true_count.length;
        float variable = 0;
        final int[] frequency = new int[101];
        for (int i = 0; i < true_count.length; i++) {
            variable += (true_count[i] - mean) * (true_count[i] - mean);
            frequency[true_count[i]] += 1;
        }
        variable /= true_count.length;
        final double center = (true_count[true_count.length / 2] + true_count[true_count.length / 2 + 1]) / 2.0;
        final int frequency_max = Arrays.stream(frequency).max().getAsInt();
        int freq = 0;
        for(int i = 0;i < true_count.length;i++){
            if (frequency_max == frequency[i]){
                freq = i;
            }
        }
        System.out.println("mean : "  + mean);
        System.out.println("variable : "  + variable);
        System.out.println("center : "  + center);
        System.out.println("frequency : "  + freq);
    }
}