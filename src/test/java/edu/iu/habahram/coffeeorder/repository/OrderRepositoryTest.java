package edu.iu.habahram.coffeeorder.repository;

import edu.iu.habahram.coffeeorder.model.OrderData;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    @Test
    void testAddOrder() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        orderRepository.add(new OrderData("Espresso", List.of("Milk", "Soy")));
        orderRepository.add(new OrderData("Decaf", List.of("Milk", "Whip")));
        orderRepository.add(new OrderData("Dark Roast", List.of("Milk", "Mocha")));
        orderRepository.add(new OrderData("House Blend", List.of()));
        List<String> lines = Files.readAllLines(Path.of("db.txt"), StandardCharsets.UTF_8);
        assertTrue(lines.get(0).contains("Espresso"));
        assertTrue(lines.get(0).contains("Milk"));
        assertTrue(lines.get(0).contains("Soy"));
        assertTrue(lines.get(1).contains("Decaf"));
        assertTrue(lines.get(1).contains("Milk"));
        assertTrue(lines.get(1).contains("Whip"));
        assertTrue(lines.get(2).contains("Dark Roast"));
        assertTrue(lines.get(2).contains("Milk"));
        assertTrue(lines.get(2).contains("Mocha"));
        assertTrue(lines.get(3).contains("House Blend"));
        try{
            orderRepository.add(new OrderData("Invalid", List.of()));
        } catch (Exception e) {
            assertEquals("Beverage type 'Invalid' is not valid!", e.getMessage());
        }
    }
}