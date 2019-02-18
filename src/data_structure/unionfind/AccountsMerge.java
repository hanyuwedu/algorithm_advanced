package data_structure.unionfind;

import java.util.*;

public class AccountsMerge {
    /**
     * 2/4/2019
     * Union Find Set and inverted index
     *
     * @param accounts: List[List[str]]
     * @return: return a List[List[str]]
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.size() == 0) {
            return new ArrayList<>();
        }

        Map<String, String> emailUser = invertedIndex(accounts);
        UnionFindSet<String> ufs = new UnionFindSet<>();

        for (List<String> account : accounts) {
            String base = account.get(1);
            ufs.add(base);

            for (int i = 2; i <= account.size() - 1; i++) {
                ufs.add(account.get(i));
                ufs.union(base, account.get(i));
            }
        }

        Set<Set<String>> unionedAccounts = ufs.getElementsAlongComponent();
        List<List<String>> mergedAccounts = new ArrayList<>();

        for (Set<String> emails : unionedAccounts) {
            List<String> account = new ArrayList<>(emails);
            Collections.sort(account);
            account.add(0, emailUser.get(account.get(0)));
            mergedAccounts.add(account);
        }

        return mergedAccounts;
    }

    private Map<String, String> invertedIndex(List<List<String>> accounts) {
        Map<String, String> emailUser = new HashMap<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i <= account.size() - 1; i++) {
                emailUser.put(account.get(i), name);
            }
        }

        return emailUser;
    }
}
