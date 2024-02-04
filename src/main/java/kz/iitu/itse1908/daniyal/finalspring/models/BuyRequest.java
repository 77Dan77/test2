package kz.iitu.itse1908.daniyal.finalspring.models;

import java.util.List;

public class BuyRequest{
    private String clientFullname;
    private String deadline;
    private List<String> bookNames;

    public BuyRequest(String clientFullname, String deadline, List<String> bookNames) {
        this.clientFullname = clientFullname;
        this.deadline = deadline;
        this.bookNames = bookNames;
    }

    public BuyRequest(){

    }

    public String getClientFullname() {
        return clientFullname;
    }

    public void setClientFullname(String clientFullname) {
        this.clientFullname = clientFullname;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public List<String> getBookNames() {
        return bookNames;
    }

    public void setBookNames(List<String> bookNames) {
        this.bookNames = bookNames;
    }

    @Override
    public String toString() {
        return "BuyRequest{" +
                "clientFullname='" + clientFullname + '\'' +
                ", deadline='" + deadline + '\'' +
                ", bookNames=" + bookNames +
                '}';
    }
}
