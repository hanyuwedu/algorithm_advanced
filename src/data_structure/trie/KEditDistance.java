package data_structure.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KEditDistance {
    /**
     * 2/1/2019
     * DFS on trie
     *
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */
    public List<String> kDistance(String[] words, String target, int k) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }

        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }

        int[] dp = new int[target.length() + 1];
        for (int i = 0; i <= target.length(); i++) {
            dp[i] = i;
        }

        List<String> list = new ArrayList<>();
        dfs(trie.root, dp, 0, k, target, "", list);

        return list;
    }

    private void dfs(TrieNode current, int[] last, int length, int k, String target, String source, List<String> list) {
        int[] dp = new int[last.length];

        if (length == 0) {
            dp = last;
        } else {
            dp[0] = length;
            for (int t = 1; t <= target.length(); t++) {
                if (target.charAt(t - 1) == current.c) {
                    dp[t] = Math.min(last[t - 1], Math.min(last[t] + 1, dp[t - 1] + 1));
                } else {
                    dp[t] = Math.min(last[t - 1] + 1, Math.min(last[t] + 1, dp[t - 1] + 1));
                }
            }
            source += current.c;
        }

        if (current.isWord && dp[target.length()] <= k) {
            list.add(source);
        }

        for (int i = 0; i <= 25; i++) {
            if (current.next[i] != null) {
                dfs(current.next[i], dp, length + 1, k, target, source, list);
            }
        }
    }





    /**
     * 2/1/2019
     * Memorized Search in Trie
     *
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */
    public List<String> kDistance_on_multiple_tries(String[] words, String target, int k) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }

        List<String> list = new ArrayList<>();
        int n = target.length();
        Trie[] dp = new Trie[n + 1];

        for (int j = 0; j <= n; j++) {
            dp[j] = new Trie();
            dp[j].add("a");
            dp[j].getTrieNode("a").distance = j;
        }

        for (String word : words) {
            String prefixedWord = "a" + word;

            for (int i = 2; i <= prefixedWord.length(); i++) {
                dp[0].add(prefixedWord.substring(0, i));
                dp[0].getTrieNode(prefixedWord.substring(0, i)).distance = i - 1;

                for (int j = 1; j <= n; j++) {
                    if (dp[j].containsWord(prefixedWord.substring(0, i))) {
                        continue;
                    }

                    dp[j].add(prefixedWord.substring(0, i));

                    int min;

                    if (prefixedWord.charAt(i - 1) == target.charAt(j - 1)) {
                        min = Math.min(dp[j - 1].getTrieNode(prefixedWord.substring(0, i - 1)).distance,
                                Math.min(dp[j - 1].getTrieNode(prefixedWord.substring(0, i)).distance + 1,
                                dp[j].getTrieNode(prefixedWord.substring(0, i - 1)).distance + 1));
                    } else {
                        min = Math.min(dp[j - 1].getTrieNode(prefixedWord.substring(0, i - 1)).distance + 1,
                                Math.min(dp[j - 1].getTrieNode(prefixedWord.substring(0, i)).distance + 1,
                                        dp[j].getTrieNode(prefixedWord.substring(0, i - 1)).distance + 1));
                    }

                    dp[j].getTrieNode(prefixedWord.substring(0, i)).distance = min;
                }
            }

            if (dp[n].getTrieNode(prefixedWord).distance <= k) {
                list.add(word);
            }
        }

        return list;
    }

    public class Trie {
        public TrieNode root;

        public Trie() {
            this.root = new TrieNode('#');
        }

        public void add(String word) {
            TrieNode current = this.root;
            for (Character c : word.toCharArray()) {
                if (current.next[c - 'a'] == null) {
                    current.next[c - 'a'] = new TrieNode(c);
                }
                current = current.next[c - 'a'];
            }
            current.isWord = true;
        }

        public boolean containsWord(String word) {
            TrieNode current = this.root;
            for (Character c : word.toCharArray()) {
                if (current.next[c - 'a'] == null) {
                    return false;
                }
                current = current.next[c - 'a'];
            }
            return current.isWord;
        }

        public List<String> getWordsByPrefix(String prefix) {
            TrieNode current = this.root;
            Stack<Character> stack = new Stack<>();
            for (Character c : prefix.toCharArray()) {
                if (current.next[c - 'a'] == null) {
                    return new ArrayList<>();
                }
                current = current.next[c - 'a'];
                stack.push(c);
            }

            List<String> result = new ArrayList<>();
            dfs(current, stack, result);
            return result;
        }

        private void dfs(TrieNode current, Stack<Character> stack, List<String> result) {
            if (current.isWord) {
                result.add(stackToString(stack));
            }

            for (char c = 'a'; c <= 'z'; c++) {
                if (current.next[c - 'a'] != null) {
                    stack.push(c);
                    dfs(current.next[c - 'a'], stack, result);
                    stack.pop();
                }
            }
        }

        public List<String> fuzzySearchWord(String word) {
            List<String> list = new ArrayList<>();
            Stack<Character> stack = new Stack<>();

            dfs(word, 0, list, stack, this.root);
            return list;
        }

        private void dfs(String word, int i, List<String> list, Stack<Character> stack, TrieNode current) {
            if (i == word.length()) {
                if (current.isWord) {
                    list.add(stackToString(stack));
                }
                return;
            }

            if (word.charAt(i) == '.') {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (current.next[c - 'a'] != null) {
                        stack.push(c);
                        dfs(word, i + 1, list, stack, current.next[c - 'a']);
                        stack.pop();
                    }
                }
            } else {
                if (current.next[word.charAt(i) - 'a'] == null) {
                    return;
                }
                stack.push(word.charAt(i));
                dfs(word, i + 1, list, stack, current.next[word.charAt(i) - 'a']);
                stack.pop();
            }
        }

        private String stackToString(Stack<Character> stack) {
            List<Character> list = new ArrayList<>(stack);
            StringBuilder builder = new StringBuilder();
            for (Character c : list) {
                builder.append(c);
            }

            return builder.toString();
        }

        public TrieNode getTrieNode(String word) {
            TrieNode current = this.root;

            for (Character c : word.toCharArray()) {
                current = current.next[c - 'a'];
                if (current == null) {
                    return null;
                }
            }

            return current;
        }
    }

    public class TrieNode {
        public boolean isWord;
        public TrieNode[] next;
        public Character c;
        public int distance;

        public TrieNode(Character c) {
            this.c = c;
            this.isWord = false;
            this.next = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        String[] words = {"abc","abd","abcd","adc"};
//        String[] words = {"drgimmrzmyioqlbpeezsegxlohvwheslvatjmkkylsobgqskwtebbemqljosngdjgz","sulfuwxiafassxowypamhnyvgldlwlkrfwwzuvkwbkpvhfrhuuwcjuzpfddeopvunr","vkmfgpsxkzccjzyfbhzazgfekuthqhyltltzalxnmzpvypabybexkjbwfxevgchzpc","ysnxfbxwxxnzbldhlnqojynnttscsjetdtqkvpiupvtqjwxwlpkvnykhteloqqhtlz","ggpocgmwwvuvrgmddadnjxuulkmufyvlgmcjsohqjdlcjafoajeliyhtkwppukdcxf","lptugeytghtnyvwciecqzmmodibgkylyngsvkqrqakgygccojegkqubteccranppuj","ngaexcofcwhensivfaqgvwiznknezjompqqouhtwddcqxmocogkjcslscawcjmqaej","raqmwvraormcvaigmkyxcdxbhipxkwgbwyqvujviakkwkscwecvjfsmmcbknzsoytg","vnqsxqchopbqnfvjoadangeztftjdmqabityavhpkihyocjefmhssntnsoxydpyezr","fzpmobvyblckrhzybtnujilggoxgswaxvpkmshmwsoijwhkzdxwmfhunvrunpunmlz","afgmbadkjbtvawvwicdeqaxmqmidbvcbakqckvovwhryemvooatgbqsrfcwgxajymb","cwwqtuxktdyqspsnxtdqmksksskeqyfsuekoyharlbhfqasnvxfkkawcsjgkhoeeso","ssxukkvtpnvjdtjkftkbxemgquuuyoisggmsskoixltcgcnlvzptebzuvvrvjbnrjc","mcmaklldouidfdcwyiyxfvuwrfxgxhijjulviyawsizamdwmmukrsgpjmmpjvaueuh","behccambcahyntvzmnfbyrnpclpyrmftqnxtpjwtkdrxyowkbblbtbrxogwfsdylsv","ejfccriuogiktmngnihggxnedhhbuazajgdmxlilrsxkgktwtacbyrfdzmevofwylz","mimwtdoqqxdjtylvzlngqouvjjcfjoexwqtswjkognzzlmvmakuqypnhyqnjetgcmz","ukkfarszqgnnupxpgvevfelunrpvubwqngvnxqoqyedstdjbanxftkbierdyuzqiim","xfemxeqbwlgingxkorwpzaifafgrpofmwjatnqwlcctrlrbzkzqlabsagzrvpwhnhk","jwahkemfxxyyyiyqwatoazpuwhqgwaqfyspizbqwaoxazxqxxmlxndtkmksimiqmcf","xbgnyidxicuabtmnsasmmeyemkocwqjtupiuzmrhqnksgyhyyzkmfisrsgwrzfmtxx","nyjegzrstxqavrbidgqwaleyedfqwepjdyplaidthtwfmxnsgdoiquguynnuscgvbx","gdglypguutuhlcnbkdvsytflqxnysqxatfluvsoplkzymmzwrwqpswcitsioifoela","yguoxfzwdnkemcactsywcsomgyswefyensvkxwjcjtivxlyrdypisfuafoxktvqiol","tnhestzcoxnoqrnfbhkvirkfbfcuoqfjgpnaiocylqndjcikluhtmrapzdjnvrybin","dqbgqpwdshhdrtxaolupdtsoinfgrpvwfwcylabghljaggbuuvlxqdlyttgmibiqxn","olnrrveaxkiyhevsblypjtirfsspiscyfqqynuakhilqpgkqsihdbpviipyshcrosh","mhdmumxfcmlnefvmjzbehlvhedkxteegmjsgwrlydyliejunkxtrlozrtlonpuvbdp","jzhuakvnuzyqoiqhabxbsrmigdcbffqohxlhkiughtxvbnedqbgkuuscyudfzwvgvi","pfqkmzflwiaaoqeuayrubrogwpctyacnhuzvuegqngrbyxxavouyfihdylxylbltvl","oprxggfzjeyhevxzufjdijohhcivxggnvzkdgsepwcycywdudnxlynvgrfboljchio","mogtgevehvckpfzotycoevthkhqmoaccrjvxprdwoijeniuigyymuuveblsroutffp","cvihtwpccflxntvoipidtjrnafktkrkomuxirpkvuxsjtoxbffgbqxoseyoqqyeete","mmtzhnwcypqwsvdzyuynmdnatfbakweykzxrmwtklligjmhhifuwkkzgpagzymailx","bztvlhiupriwbqbxomhnsxpaynoyxbyzarwlaehpyplzhpwwbelvdbxbqlcomanmtj","zwnhllwzmgojcrpnosqlvhwxxkzkytvxrkgcyfdkltvpkkcyevjaehzbrbnpwipnsv","rsavdnoycblhzpclriutjmwwdsgshaqytsvygmyinjrobtbudwpmklkndsiktylosg","myukjjwaaxwbtzxjohuaxatfmmfzazimycyjmulmtjpnlnyzwuzvwscjghighsuhvv","sjpdvlpkaazybysatpvwadfkmodixydphvvegkqgksglqanlpkhropcadfibfmsojn","urxkzjcfwufkgxunokeqkkxvnejfssvmjuziebpawwmethtityaeiabvekazcvnnqm","xwqmyoilseulonlqrtquoxeryiolvgauesiditocxiplvbbpuujltoeryueubeqhyz","kguyirgyeebhtvbeiuuontjspmwftxgedaeorloxrpfmmiqvfljseuktgjacghgjjm","zdxnaqchcpqtmvgxpksafajhcrokcaxcdwrdpulrldkzaqwpcqrjrcrvthiyhfakdr","qsndmagwbipyztqrdhvufrbnpjurvzkluaogcwceeteenuxseumjnpycasvwtihniw","tkuyqzrufgreavamgoegseaoatwkhgiascajbufiawodtprbdvhxahlcdjmkrumkxn","vahaljzzmuoewtbfrbpeesqsehnqrmfmmopxyozmipsgluocvfibzyudgjvzvbojpd","hpriebxwkiqamofvrevnjdwedthrdzwkqpsuqrrcajcmxjjppfeyibfnuogxpcjgrd","akxtoxdrlacvrsawqkzxyvmevbjgjcmkmjfdgiurlwoeqoajbbgzxufuyqahtmthvz","kdhgxuflzvcbgddnecjlyheyrznkgiltovblpgyoeaskfxzlzlxxsbxlakyivjdjef","wunuktxcgczhoasnznqzysjwagggpmlngaitufxajzjzbcmdpccpulmxuufjgqzpsh","kmohozgyzkclntqpimcwlwqqizihobqapghefqegaisnbkcjyegkbyalzlsnmkpbqx","hxnnenvycwieigkqsnovyogsductxssfqfuuuquyocewkomfedaetgxycztzulgmsb","iorcmhgqgshuxlxxrsforhqmhmypysqihjmvrsoxkxrikrfblnqeuirdurvujlesvq","qoignudgcnxkqlxgptqgzmzuilfmdccvsmbfhemjtlvkwssmojunxujgfqsktxgljj","tqqfajqvtnqobezvtwreeyuxlpwtdfcwwsbyetvzinnjtmhmlaspznokckdfqhboae","meyjdgxtsqfzfszxiamwkpjpwzxypzcddcmillbdejelbfklgxksotikufijenmjry","tcmxfqglbjrlxxkhpyaxjuetfkirvarpedmktuvwfokclulbvlaghhzmtkfpmxeqas","awmystmcwazhduvfcembtgnyvzvbqwtsuhsmahrwhsgkodpqhetamhzhgwkyudroml","dmsulzotlcwdsdjnevufedbodwtumfgptaleczxpbtsvzdjfyfkcknsolliyqqpkrb","qvcqkgjfbkimpjuznepgsatdakvqwotojveubqzfahrqsnrgsgmkjfpjrlzpbsglqk","hrcgwdpqvidoqxxeigjzpdmruolabbnitpqrufjrpnihkflulxuaaitwwfxxgmfbdx","tadcrurzccepyrmusmdllzskzzwgazetzjytfpujtzzsslmmapalrsvsrryusenunn","ntdhewidobpdnrsnclgxdzqetkijeywrtbbxylboosrcjmrnzzkfbcjumtfrrckmfn","kdznspflrqxldxmpcnrnkfipwatjozyyezlypskiijtliiamxrzhxizvkugyvfzagk","avfkenwzzehztftsoscppokyphfqjlckhhwlvukwzuvszqmpkrfahrayyfoiqruzys","kvnxrorpgshuhsnmuvfuvebkordkrdcdzpasfthnlpithxhdunxqtycjrftkixojpq","cukjivasqjpymknlailcrdjmnrjddyufvgqdbrvsamspwhaypmcgpnsehdkkegrzmh","epabjdnbsmiumzgrfxezeldoxhwogiykzyljdamvmxqzwyscwwbaieqfnmwvwuhvss","eyvrvholikmaoizskjwaqmomhkgphbjozefwoviyhwbweaoqlmsbagoiswzzzipapu","xdrhdzdgxjinztbtvcblmtjlujtklkgipyrtzuzyfingdozysaletvpqglcrvicnit","gjqhjvrwcvlbvdehkzebpmmrfkchxmdfwtmfqecubpwwucdebjgrvukbgokfbpkxkz","ebfivgxtcsviyxrgqpbaqjpcpqszpcytdfeblbuqvsywprcfgfhypybeotdfvebbjf","cxiyneqlaheemlkwjzukdlfisbmteyygvjeknxxpebttmdpyclkhwqqptekxcldzuj","jhigynkrgyxxwbihjhzzwsfipktujnfvwodudnojmlikmqrxzswxkegbozxzpfulta","iynwjbjrmxjfvjytgkxmmlolldqholhwjvuswdlibvqweopmypzmanalqqtgeadpxz","kwdvggqwewkvjlkkxlxzypqtvwwalvavrdryljupghmrsxbrkaripjekfalsvnopqh","ndqjuysgpmdsgnszyhicupehhzuxqmedpvpjthpluvdckwbifjmzaqgjsahimlodid","oemgpibuknslwywkxybgivgpfvctylynrntivwcijvthtsssqvyaqhrxcwrchrraem","icikkuhfbczvvsqvsicmgeizjrqarxnzzxjjrtqvvqqqijmcrqoxvwygnpiemvdnvp","yoiojdgawojktcahcxgadtpmadjdqeuotefeklggcrqvuqewokwteohetrjkxgaskf","xurdctxsqrlupbfnwlberxvahugjntpmnirsbomtfxnuaukzhndykaztuhdhastqal","idzuyhunduinvpbyolyphvwkczeypglxlmrjtnwzkgofypeodefmzbwecacripqtbk","exxcwhlknjctzhxetxhrdktfdbuuwxeaubesjpfyyjrzsodnnneqzzxcctwyraanee","gpwnnwwewosbbiohapgysitoimriaxoimkvzhtdfjxgmhxtimbiejbvtoodqltyzev","ynqduzcclkzguohcusdrltfojsjjtnercwwyvzbilaofqxhlpmccgksrecbyrhpweo","ubnvkayyfoyoznacqioiasmbqeiiaowuqjsalqaqeyggoikeryotqdwhhgphvldlwv","nhnnzsohycpiiiiybybxhkewtbjxmhuzqiorceycgomqyupasszcdfywgkvurqvkam","bcqaexqqpomeqgzsiczjaitwcmoawomascawbsoricxziwtrasabavxfhnfgbrgtvi","rxahqkjnltmekmzmnbhmgpznxcgdvcnobpxrzielcqrodqbsuihcxiqulyziamzbdw","sdfzqhphvuawpuhwweeobrncaprztuvlzcmplcxhyxfntmjrtqgwhvyjkridmgrlkd","dwhafgzmvuzfluxtseosqbkygygzlrfonmquupgqjhxvbuovafoqiyroyzqjsvyhho","begiurptotnfopkeiscbicaymtvmafccjkmfcdyqwnvnchrmzwnkyqkmlgalldpund","zrhxidmfqonidogqaeilrtrrfewwamazdjynmmvfaipdyvwzbeksydmfhidhwficoi","pdukkwuzctwasxgesehglqqrznwjpntgqntclpdpizpcywgscnaqfrhegfnxsgfkvy","mjopyyqqbrohlsxmicfrhlxukrdpcyqqheiieayfropfgmrpowixjfuvxxkbxathgb"};
//        String[] words = {"e"};
        String target = "ac";
        int k = 1;

        System.out.println(new KEditDistance().kDistance(words, target, k));
    }
}
