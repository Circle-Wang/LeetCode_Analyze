import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CUI on 2021/1/3
 */
public class Problem_0140_åè¯æåII {

    class TreeNode {
        TreeNode[] next = new TreeNode[26];
        boolean end = false;
        String path = "";
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> list = new ArrayList<>();
        LinkedList<String> cur = new LinkedList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0)
            return list;
        TreeNode root = getTire(wordDict);
        boolean[] dp = getDP(s, root);
        proocess(s, root, list, dp,0, cur);
        return list;
    }

    private void proocess(String s, TreeNode root, List<String> list, boolean[] dp, int start, LinkedList<String> cur) {
        if (start == s.length()) {
            list.add(listToString(cur));
            return;
        }
        TreeNode curRoot = root;
        for (int end = start; end < s.length(); end++) {
            curRoot = curRoot.next[s.charAt(end) - 'a'];
            if (curRoot != null) {
                if (curRoot.end && dp[end+1]) {
                    cur.addLast(curRoot.path);
                    proocess(s, root, list,dp, end + 1, cur);
                    cur.removeLast();
                }
            } else
                break;
        }
    }

    private boolean[] getDP(String s, TreeNode root) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            TreeNode cur = root;
            for (int end = i; end < s.length(); end++) {
                cur = cur.next[s.charAt(end) - 'a'];
                if (cur != null) {
                    if(cur.end && dp[end+1]){
                        dp[i] = true;
                        break;
                    }
                }
                else
                    break;
            }
        }
        return dp;

    }

    private TreeNode getTire(List<String> wordDict) {
        TreeNode root = new TreeNode();
        for (int i = 0; i < wordDict.size(); i++) {
            TreeNode cur = root;
            for (int j = 0; j < wordDict.get(i).length(); j++) {
                int pos = wordDict.get(i).charAt(j) - 'a';
                if (cur.next[pos] == null) {
                    cur.next[pos] = new TreeNode();
                }
                cur = cur.next[pos];
            }
            cur.end = true;
            cur.path = wordDict.get(i);
        }
        return root;
    }

    private String listToString(List<String> cur) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < cur.size(); i++) {
            s.append(cur.get(i));
            if (i != cur.size() - 1) {
                s.append(" ");
            }
        }
        return s.toString();
    }

    //31 / 36 ä¸ªéè¿æµè¯ç¨ä¾ åç°å³ä½¿ä½¿ç¨äºTireNodeè¿æ¯è¿ä¸æ¥ ä¸ºä»ä¹ï¼å ä¸ºæ²¡æè¿ç¨DPç¼å­
    //æä»¬å¯ä»¥é¢åè·ä¸é çæä¸ªå­ç¬¦é¿åº¦æ¯å¦æå¯¹åºçåè¯ä¸ä¹å¯¹åº è¿æ ·åå°äºåªè£
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> list = new ArrayList<>();
        LinkedList<String> cur = new LinkedList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0)
            return list;
        TreeNode root = getTire(wordDict);
        proocess2(s, root, list, 0, cur);
        return list;
    }

    private void proocess2(String s, TreeNode root, List<String> list, int start, LinkedList<String> cur) {
        if (start == s.length()) {
            list.add(listToString(cur));
            return;
        }
        TreeNode curRoot = root;
        for (int end = start; end < s.length(); end++) {
            curRoot = curRoot.next[s.charAt(end) - 'a'];
            if (curRoot != null) {
                if (curRoot.end) {
                    cur.addLast(curRoot.path);
                    proocess2(s, root, list, end + 1, cur);
                    cur.removeLast();
                }
            } else
                break;
        }

    }






    //31 / 36 ä¸ªéè¿æµè¯ç¨ä¾ æ¶é´è¶æ¶
    public List<String> wordBreak1(String s, List<String> wordDict) {
        List<String> list = new ArrayList<>();
        LinkedList<String> cur = new LinkedList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0)
            return list;
        proocess1(s, wordDict, list, 0, cur);
        return list;
    }

    private void proocess1(String s, List<String> wordDict, List<String> list, int start, LinkedList<String> cur) {
        if (start == s.length()) {
            list.add(listToString(cur));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            String temp = s.substring(start, end + 1);
            if (wordDict.contains(temp)) {
                cur.add(temp);
                proocess1(s, wordDict, list, end + 1, cur);
                cur.removeLast();
            }
        }

    }


}
