package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicles {
    @JsonProperty("data")
    private List<Vehicle> vehicles;

    @JsonProperty("links")
    private List<Link> links;

    @JsonProperty("meta")
    private PagedResources.PageMetadata metadata;


    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
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

    public Vehicles withVehicles(List<Vehicle> vehicles) {
        setVehicles(vehicles);
        return this;
    }

    public Vehicles withLinks(List<Link> links) {
        setLinks(links);
        return this;
    }

    public Vehicles withMetadata(PagedResources.PageMetadata metadata) {
        setMetadata(metadata);
        return this;
    }
}
