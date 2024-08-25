package practice;

class TrieNode {
    boolean isWord;
    TrieNode letters[];

    public TrieNode(int n) {
        isWord = false;
        letters = new TrieNode[];
    }

    public boolean add(String s) {
        TrieNode at = this;
        System.out.println(at.letters +" "+at.isWord);
        for(char ch: s.toCharArray()) {
            if(at.letters[ch-'a']==null) at.letters[ch-'a'] = new TrieNode(26);
            at = at.letters[ch-'a'];
        }
        at.isWord = true;

        return at.isWord;
    }
}

class OOps {
    public static void main(String[] args) {
        TrieNode root = new TrieNode(26);
//        root.add("hello");
        System.out.println(root.add("hello"));
    }
}
