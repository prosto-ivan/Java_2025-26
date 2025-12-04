import java.util.Arrays;

public class TestRailCarSet {
    
    public static void main(String[] args) {
        // Три конструктори
        System.out.println("1. Конструктори:");
        RailCarSet<RailCar> set1 = new RailCarSet<>();
        RailCarSet<RailCar> set2 = new RailCarSet<>(new LuxuryCar(20, 40, 1));
        RailCarSet<RailCar> trainSet = new RailCarSet<>(Arrays.asList(
            new EconomyCar(60, 80, 3),
            new CoupeCar(40, 60, 2),
            new LuxuryCar(20, 40, 1)
        ));
        System.out.println("   Порожня: " + set1.size() + ", З 1 елементом: " + set2.size() + ", Зі списку: " + trainSet.size());
        
        // Додавання
        System.out.println("\n2. Додавання елементів:");
        trainSet.add(new EconomyCar(50, 70, 3));
        trainSet.add(new CoupeCar(36, 55, 2));
        System.out.println("   Розмір після додавання: " + trainSet.size());
        
        // Властивість Set
        System.out.println("\n3. Властивість Set (без дублікатів):");
        boolean added = trainSet.add(new EconomyCar(60, 80, 3));
        System.out.println("   Дублікат відхилено: " + !added);
        
        // Пошук
        System.out.println("\n4. Пошук:");
        System.out.println("   Містить Купе(40,60,2): " + trainSet.contains(new CoupeCar(40, 60, 2)));
        
        // Перебір
        System.out.println("\n5. Вміст колекції:");
        int i = 1;
        for (RailCar car : trainSet) {
            System.out.println("   " + i++ + ". " + car);
        }
        
        // Видалення
        System.out.println("\n6. Видалення:");
        trainSet.remove(new LuxuryCar(20, 40, 1));
        System.out.println("   Розмір після видалення: " + trainSet.size());
        
        // Статистика
        System.out.println("\n7. Статистика потягу:");
        int totalPass = 0, totalBag = 0;
        for (RailCar car : trainSet) {
            totalPass += car.getPassengerCount();
            totalBag += car.getBaggageCount();
        }
        System.out.println("   Пасажирів: " + totalPass + ", Багажу: " + totalBag);
        
        // Очищення
        System.out.println("\n8. Очищення:");
        trainSet.clear();
        System.out.println("   Розмір: " + trainSet.size() + ", Порожня: " + trainSet.isEmpty());
        
        System.out.println("\n=== ТЕСТ ЗАВЕРШЕНО ===");
    }
}