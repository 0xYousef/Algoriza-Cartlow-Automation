package org.algoriza.repository;

import org.algoriza.dto.AccountDTO;
import org.algoriza.utils.JsonParser;
import org.algoriza.utils.RandomUtil;
import org.algoriza.warpper.Accounts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccountsRepository {
    private static final String FILE_PATH = "data/accounts.json";
    private static final Logger log = LoggerFactory.getLogger(AccountsRepository.class);
    private List<AccountDTO> accounts;

    public AccountsRepository() {
        loadAccounts();
    }

    private void loadAccounts() {
        try {
            Accounts wrapper = JsonParser.readFromResources(FILE_PATH, Accounts.class);
            accounts = wrapper.getAccounts();
        } catch (Exception e) {
            log.error("Error loading accounts from file: {}", FILE_PATH);
        }
    }

    public List<AccountDTO> getAll() {
        log.info("Read All Accounts");
        return accounts;
    }

    public AccountDTO getRandom() {
        log.info(("Fetching Random Account"));
        return RandomUtil.getRandomElement(accounts);
    }

}
