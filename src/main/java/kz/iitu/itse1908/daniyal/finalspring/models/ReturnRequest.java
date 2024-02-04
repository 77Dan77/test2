package kz.iitu.itse1908.daniyal.finalspring.models;

import java.util.List;

public class ReturnRequest {
    private String clientFullname;
    private Long ticketId;

    public ReturnRequest(String clientFullname, Long ticketId) {
        this.clientFullname = clientFullname;
        this.ticketId = ticketId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getClientFullname() {
        return clientFullname;
    }

    public void setClientFullname(String clientFullname) {
        this.clientFullname = clientFullname;
    }

    @Override
    public String toString() {
        return "ReturnRequest{" +
                "clientFullname='" + clientFullname + '\'' +
                ", ticketId=" + ticketId +
                '}';
    }
}
