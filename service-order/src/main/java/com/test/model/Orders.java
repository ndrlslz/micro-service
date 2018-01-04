package com.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;

import java.util.List;

public class Orders {
    @JsonProperty("data")
    private List<Order> orders;

    @JsonProperty("links")
    private List<Link> links;

    @JsonProperty("meta")
    private PagedResources.PageMetadata metadata;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public PagedResources.PageMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PagedResources.PageMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders1 = (Orders) o;

        if (orders != null ? !orders.equals(orders1.orders) : orders1.orders != null) return false;
        if (links != null ? !links.equals(orders1.links) : orders1.links != null) return false;
        return metadata != null ? metadata.equals(orders1.metadata) : orders1.metadata == null;

    }

    @Override
    public int hashCode() {
        int result = orders != null ? orders.hashCode() : 0;
        result = 31 * result + (links != null ? links.hashCode() : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        return result;
    }
}
