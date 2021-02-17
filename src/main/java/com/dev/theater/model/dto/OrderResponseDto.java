package com.dev.theater.model.dto;

import com.dev.theater.model.Ticket;
import java.util.List;

public class OrderResponseDto {
    private Long id;
    private String orderTime;
    private List<Ticket> ticketIds;
    private String userEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public List<Ticket> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<Ticket> ticketIds) {
        this.ticketIds = ticketIds;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
