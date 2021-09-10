package Algorithm.algorithm.Y6주차;

import java.util.Objects;
import java.util.PriorityQueue;

public class 캐시 {

    static int cacheSize = 100;
    static String[] cities = {"1", "1", "2", "2"};

    public static void main(String[] args) {
        System.out.println(solution(cacheSize, cities));
    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if(cacheSize == 0) {
            answer = cities.length * 5;
            return answer;
        }
        PriorityQueue<CacheInfo> cacheData = new PriorityQueue<>();

        for (int c = 0; c < cities.length; c++) {
            String city = cities[c].toLowerCase();
            boolean exist = false;

            int curCacheSize = cacheData.size();

            PriorityQueue<CacheInfo> temp = new PriorityQueue<>();
            for (int i = 0; i < curCacheSize; i++) {
                CacheInfo cacheInfo = cacheData.poll();
                if (cacheInfo.city.equals(city)) {
                    exist = true;
                } else {
                    temp.add(new CacheInfo(cacheInfo.time + 1, cacheInfo.city));
                }
            }

            cacheData.addAll(temp);

            if (exist) {
                answer++;
                cacheData.add(new CacheInfo(0, city));
            } else {
                if (curCacheSize < cacheSize) {
                    cacheData.add(new CacheInfo(0, city));
                } else {
                    cacheData.poll();
                    cacheData.add(new CacheInfo(0, city));
                }
                answer += 5;
            }
        }

        return answer;
    }

    static class CacheInfo implements Comparable<CacheInfo> {

        int time;
        String city;

        public CacheInfo(int time, String city) {
            this.time = time;
            this.city = city;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CacheInfo cacheInfo = (CacheInfo) o;
            return Objects.equals(city, cacheInfo.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(city);
        }

        @Override
        public int compareTo(CacheInfo o) {
            return o.time - this.time;
        }
    }
}
