package service;
import exception.*;
import model.Account;
import model.annotation.Audit;
import util.*;
import java.lang.reflect.Method;
import java.nio.file.*;
import java.io.*;
import java.util.List;

public class BankService {
    // The repository stores accounts using their account numbers.
    private final Repository<Account> repo = new Repository<>();
    private void logAudit(String name) {
        for (Method m : getClass().getDeclaredMethods()) {
            if (m.getName().equals(name) && m.isAnnotationPresent(Audit.class))
                System.out.println("[Audit] " + m.getAnnotation(Audit.class).action());
        }
    }
    @Audit(action = "OPEN_ACCOUNT")
    public synchronized Account openAccount(String id, String owner,
            String mobile, long balance) throws BankException {
        if (id == null || !id.matches("AC[0-9]{4,}"))
            throw new BankException("Use account ID AC followed by at least four digits.");
        if (repo.findById(id) != null) throw new BankException("Account already exists.");
        if (owner == null || owner.isBlank()) throw new BankException("Owner is required.");
        if (!Validator.isValidMobile(mobile)) throw new BankException("Invalid mobile number.");
        if (balance < 0) throw new InvalidAmountException(balance);
        Account a = new Account(id, owner.trim(), balance);
        repo.save(a); logAudit("openAccount"); return a;
    }
    @Audit(action = "DEPOSIT")
    public synchronized void deposit(String id, long amount) throws BankException {
        Account a = getRequired(id);
        if (amount <= 0 || a.getBalance() > Long.MAX_VALUE - amount)
            throw new InvalidAmountException(amount);
        a.deposit(amount); logAudit("deposit");
    }
    @Audit(action = "WITHDRAW")
    public synchronized void withdraw(String id, long amount) throws BankException {
        getRequired(id).withdraw(amount); logAudit("withdraw");
    }
    // synchronized lets only one service operation run at a time.
    // Check both accounts before changing either balance.
    @Audit(action = "TRANSFER")
    public synchronized void transfer(String fromId, String toId, long amount)
            throws BankException {
        if (fromId.equals(toId)) throw new BankException("Choose two different accounts.");
        Account from = getRequired(fromId), to = getRequired(toId);
        if (amount <= 0 || to.getBalance() > Long.MAX_VALUE - amount)
            throw new InvalidAmountException(amount);
        from.withdraw(amount); to.deposit(amount); logAudit("transfer");
    }
    public synchronized Account getRequired(String id) throws AccountNotFoundException {
        Account a = repo.findById(id);
        if (a == null) throw new AccountNotFoundException(id);
        return a;
    }
    public synchronized String statement(String id) throws BankException {
        return StatementFormatter.buildStatement(getRequired(id));
    }
    public synchronized List<Account> listAccounts() {
        List<Account> list = repo.findAll(); list.sort(null); return list;
    }
    // Save to a temporary file first so a failed write keeps the old file.
    public synchronized void save(Path file) throws IOException {
        Path target = file.toAbsolutePath();
        Files.createDirectories(target.getParent());
        Path temp = Files.createTempFile(target.getParent(), "accounts-", ".tmp");
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(temp))) {
                out.writeObject(listAccounts().toArray(new Account[0]));
            }
            Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING);
        } finally { Files.deleteIfExists(temp); }
    }
    public synchronized void load(Path file) throws IOException, ClassNotFoundException {
        if (!Files.exists(file)) return;
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(file))) {
            Account[] accounts = (Account[]) in.readObject();
            for (Account a : accounts) repo.save(a);
        }
    }
}
