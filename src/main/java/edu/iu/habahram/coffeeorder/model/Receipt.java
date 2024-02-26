package edu.iu.habahram.coffeeorder.model;

public record Receipt(int id, String description, float cost) {

    public String toString() {
        return id + ", "+ description + ", " + cost + System.lineSeparator();
    }
}
