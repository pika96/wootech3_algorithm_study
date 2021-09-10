package Algorithm.algorithm.Y5주차;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 매칭점수 {

//    public static String word = "blind";
//    public static String[] pages = {
//            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
//            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
//            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};

    public static String word = "Muzi";

    public static String[] pages = {
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};

    public static String mainUrlReg = "<meta property=\"og:url\" content=\"https://(.+?)\"/>";
    public static String linkUrlReg = "<a href=\"https://(.+?)\"\\>";

    public static Pattern mainPattern = Pattern.compile(mainUrlReg);
    public static Pattern linkPattern = Pattern.compile(linkUrlReg);

    public static Matcher mainUrlMatcher;
    public static Matcher linkUrlMatcher;

    static ArrayList<PageInfo> list;

    public static void main(String[] args) {
        System.out.println(solution(word, pages));
    }

    public static int solution(String word, String[] pages) {
        int answer = 0;

        list = new ArrayList<>();

        // 기본 점수 계산
        for (int i = 0; i < pages.length; i++) {
            mainUrlMatcher = mainPattern.matcher(pages[i]);
            linkUrlMatcher = linkPattern.matcher(pages[i]);
            String mainUrl = url();
            String[] linkUrl = linkUrl();
            double baseScore = calculateBaseScore(word, pages[i]);

            PageInfo pageInfo = new PageInfo(i, mainUrl, linkUrl, baseScore, 0, 0);
            list.add(pageInfo);
        }

        // 링크 점수 계산
        for (int i = 0; i < pages.length; i++) {
            PageInfo pageInfo = list.get(i);

            String[] linkUrls = pageInfo.getLinkUrl();

            double curPageLinkScore =
                    pageInfo.getBaseScore() / (double) pageInfo.getLinkUrl().length;
            calculateLinkScore(linkUrls, curPageLinkScore);
        }

        for (int i = 0; i < list.size(); i++) {
            double baseScore = list.get(i).baseScore;
            double linkScore = list.get(i).linkScore;

            list.get(i).setAllScore(baseScore + linkScore);
        }

        // 마지막 sort 할 때 new Comparable 은 안되고 Comparator 됐음 왜일까?
        list.sort(new Comp());
//        Collections.sort(list);

        answer = list.get(0).idx;

        return answer;
    }

    static class Comp implements Comparator<PageInfo> {

        @Override
        public int compare(PageInfo a, PageInfo b) {
            if(a.allScore == b.allScore) {
                return a.idx - b.idx;
            } else if(a.allScore < b.allScore) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private static void calculateLinkScore(String[] linkUrls, double score) {

        for (int i = 0; i < linkUrls.length; i++) {
            String linkUrl = linkUrls[i];

            for (int j = 0; j < list.size(); j++) {
                if (linkUrl.equals(list.get(j).getMainUrl())) {
                    PageInfo pageInfo = list.get(j);

                    pageInfo.setLinkScore(pageInfo.linkScore + score);
                    break;
                }
            }
        }
    }

    static class PageInfo implements Comparable<PageInfo> {

        int idx;
        String mainUrl;
        String[] linkUrl;
        double baseScore;
        double linkScore;
        double allScore;

        PageInfo(int idx, String mainUrl, String[] linkUrl, double baseScore, double linkScore,
                double allScore) {
            this.idx = idx;
            this.mainUrl = mainUrl;
            this.linkUrl = linkUrl;
            this.baseScore = baseScore;
            this.linkScore = linkScore;
            this.allScore = allScore;
        }

        public String getMainUrl() {
            return mainUrl;
        }

        public String[] getLinkUrl() {
            return linkUrl;
        }

        public double getBaseScore() {
            return baseScore;
        }

        public void setLinkScore(double linkScore) {
            this.linkScore = linkScore;
        }

        public void setAllScore(double allScore) {
            this.allScore = allScore;
        }

        @Override
        public int compareTo(PageInfo p) {
            if (this.allScore == p.allScore) {
                return this.idx - p.idx;
            } else {
                return (int) (p.allScore - this.allScore);
            }
        }
    }

    private static double calculateBaseScore(String word, String page) {
        String lowerWord = word.toLowerCase(Locale.ROOT);
        String lowerPage = page.toLowerCase(Locale.ROOT);

        String[] splitPage = lowerPage.split("[^a-z]+");

        double count = 0;

        for (String s : splitPage) {
            if (lowerWord.equals(s)) {
                count++;
            }
        }

        return count;
    }

    public static String url() {

        String result = "";
        while (mainUrlMatcher.find()) {
            result = mainUrlMatcher.group(1);

            if (mainUrlMatcher.group(1) == null) {
                break;
            }
        }

        return result;
    }

    public static String[] linkUrl() {
        ArrayList<String> result = new ArrayList<>();

        while (linkUrlMatcher.find()) {
            result.add(linkUrlMatcher.group(1));

            if (linkUrlMatcher.group(1) == null) {
                break;
            }
        }

        String[] arrayString = new String[result.size()];

        for (int i = 0; i < result.size(); i++) {
            arrayString[i] = result.get(i);
        }

        return arrayString;
    }
}
