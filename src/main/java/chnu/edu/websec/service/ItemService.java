package chnu.edu.websec.service;


import chnu.edu.websec.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ItemService {
    private List<Car> items = new ArrayList<>();
    {
        items.add(new Car("1", "BMW M5", "2024", "High-performance sedan", "Black"));
        items.add(new Car("2", "Audi A6", "2023", "Comfortable sedan", "White"));
        items.add(new Car("3", "Toyota Camry", "2024", "Reliablesedan", "Silver"));
        items.add(new Car("4", "Mercedes-Benz GLE", "2022", "Premium SUV", "Blue"));
        items.add(new Car("5", "Tesla Model 3", "2025", "Electric sedan", "Red"));

    }

    public List<Car> getALLItems() {
        return items;
    }
    public Car createItem(Car item) {
        items.add(item);
        return item;
    }
    public Car getItem(String id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst().orElse(null);

    }

    public Car updateItem(Car item) {
        Car oldItem = getItem(item.getId());
        items.remove(oldItem);
        items.add(item);
        return oldItem;
    }



    public void deleteItem(String id) {
        Car item = getItem(id);
        items.remove(item);
    }
}



