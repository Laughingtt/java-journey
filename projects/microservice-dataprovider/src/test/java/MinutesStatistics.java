


import java.util.*;

public class MinutesStatistics {
    public static void main(String[] args) {
        // 假设您已经有一个包含上万条数据的字典，这里我们简单地使用几个示例数据进行演示
        List<Map.Entry<Long, Integer>> heartRateData = Arrays.asList(
                entry(1626700000L, 80), // 时间戳：1626700000，心率：80
                entry(1626700010L, 85), // 时间戳：1626700010，心率：85
                entry(1626700020L, 90), // 时间戳：1626700020，心率：90
                entry(1626700020L, 70) // 时间戳：1626700020，心率：70
                // ... 更多数据
        );

        // 将数据按照时间戳排序
        heartRateData.sort(Map.Entry.comparingByKey());

        // 获取当前时间戳
        long currentTime = System.currentTimeMillis() / 1000;

        // 筛选近五分钟内的数据
        long fiveMinutesAgo = currentTime - 5 * 60; // 五分钟前的时间戳
        List<Integer> recentHeartRates = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : heartRateData) {
            if (entry.getKey() >= fiveMinutesAgo) {
                recentHeartRates.add(entry.getValue());
            } else {
                break; // 数据已按时间戳排序，因此这里可以直接跳出循环
            }
        }

        // 计算平均心率和最大心率
        double sum = 0;
        int maxHeartRate = Integer.MIN_VALUE;
        for (int heartRate : recentHeartRates) {
            sum += heartRate;
            maxHeartRate = Math.max(maxHeartRate, heartRate);
        }
        double averageHeartRate = sum / recentHeartRates.size();

        // 输出结果
        System.out.println("近五分钟内的平均心率：" + averageHeartRate);
        System.out.println("近五分钟内的最大心率：" + maxHeartRate);
    }

    private static <K, V> Map.Entry<K, V> entry(K key, V value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }
}