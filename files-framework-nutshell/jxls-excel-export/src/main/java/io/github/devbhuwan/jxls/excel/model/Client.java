package io.github.devbhuwan.jxls.excel.model;

import java.util.List;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/23/2017
 */
public class Client {

    private String clientName;

    private List<ClientAccount> clientAccounts;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<ClientAccount> getClientAccounts() {
        return clientAccounts;
    }

    public void setClientAccounts(List<ClientAccount> clientAccounts) {
        this.clientAccounts = clientAccounts;
    }

}
