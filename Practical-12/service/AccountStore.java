package service;
import model.Account;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AccountStore {
    private final Map<String, Account> map = new HashMap<>();
    private final ConcurrentHashMap<String, Account> concurrentMap = new ConcurrentHashMap<>();
    private final ArrayList<String> history = new ArrayList<>();

    public void add(Account account) {
        map.put(account.getAccountNumber(), account);
        concurrentMap.put(account.getAccountNumber(), account);
        history.add("OPEN " + account.getAccountNumber());
    }

    public Account get(String accountNumber) {
        return map.get(accountNumber);
    }

    public Collection<Account> getAll() {
        return map.values();
    }

    public ArrayList<Account> sortedByBalance() {
        ArrayList<Account> list = new ArrayList<>(map.values());
        list.sort(Comparator.comparingLong(Account::getBalance).reversed());
        return list;
    }
}
