package com.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;

import java.util.List;

public class Shops {
    @JsonProperty("data")
    private List<Shop> shops;

    @JsonProperty("links")
    private List<Link> links;

    @JsonProperty("meta")
    private
    PagedResources.PageMetadata metadata;

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
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

        Shops shops1 = (Shops) o;

        if (shops != null ? !shops.equals(shops1.shops) : shops1.shops != null) return false;
        if (links != null ? !links.equals(shops1.links) : shops1.links != null) return false;
        return metadata != null ? metadata.equals(shops1.metadata) : shops1.metadata == null;

    }

    @Override
    public int hashCode() {
        int result = shops != null ? shops.hashCode() : 0;
        result = 31 * result + (links != null ? links.hashCode() : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        return result;
    }
}
