package com.dq.demo.greed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * <p>
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * <p>
 * 返回一个表示每个字符串片段的长度的列表。
 * <p>
 * <p>
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 * a ---> as
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "eccbbbbdec"
 * 输出：[10]
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 仅由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PartitionLabel {

    public static void main(String[] args) {
        List<Integer> abcde = partitionLabels("ababcbacadefegdehijhklij");
        System.out.println(abcde);
    }

    public static List<Integer> partitionLabels(String s) {
        Map<Character, Integer> chartMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chartMap.put(chars[i], i);
        }

        int start = 0, end = 0;

        for (int i = 0; i < chars.length; i++) {
            end = Math.max(end, chartMap.get(chars[i]));
            if (end == i) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        return result;
    }

    public static List<Integer> partitionLabels2(String s) {
        char[] chars = s.toCharArray();
        List<Integer> result = new ArrayList<>();
        Map<Character, List<Integer>> chartMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (chartMap.containsKey(chars[i])) {
                chartMap.get(chars[i]).add(i);
            } else {
                ArrayList<Integer> value = new ArrayList<>();
                value.add(i);
                chartMap.put(chars[i], value);
            }
            chartMap.computeIfAbsent(chars[i], key -> new ArrayList<>()).add(i);
        }
        int currentIndex = 0;
        int nextIndex = 0;
        for (int i = 0; i < chars.length; ) {
            List<Integer> indexes = chartMap.get(chars[i]);
            currentIndex = indexes.get(0) > i ? indexes.get(0) : i;
            nextIndex = indexes.get(indexes.size() - 1);
            if (currentIndex == nextIndex) {
                result.add(1);
                i++;
            } else if (currentIndex + 1 == nextIndex) {
                result.add(2);
                i += 2;
            } else {
                Set<Character> subChar = new HashSet<>();
                for (int j = currentIndex; j < nextIndex; j++) {
                    subChar.add(chars[j]);
                }
                for (Character character : subChar) {
                    List<Integer> integers = chartMap.get(character);
                    Integer lastIndex = integers.get(integers.size() - 1);
                    nextIndex = lastIndex > nextIndex ? lastIndex : nextIndex;
                }
                result.add(nextIndex - currentIndex + 1);
                i = nextIndex + 1;
            }
        }
        return result;
    }
}
