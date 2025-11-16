import java.util.Arrays;
import java.util.Comparator;

public class TrainApp {

    public static void main(String[] args) {
        // Створюємо вагони
        RailCar[] cars = {
                new EconomyCar(60, 80, 3),
                new EconomyCar(50, 70, 3),
                new CoupeCar(40, 60, 2),
                new LuxuryCar(20, 40, 1),
                new CoupeCar(36, 55, 2)
        };

        PassengerTrain train = new PassengerTrain(cars);

        System.out.println("Початковий склад потягу:");
        train.printCars();

        int totalPassengers = train.calculateTotalPassengers();
        int totalBaggage = train.calculateTotalBaggage();

        System.out.println("\nЗагальна кількість пасажирів: " + totalPassengers);
        System.out.println("Загальна кількість багажу (місць): " + totalBaggage);

        train.sortByComfortLevel();
        System.out.println("\nПісля сортування за рівнем комфортності:");
        train.printCars();

        // Пошук вагонів за діапазоном пасажирів
        int minPassengers = 35;
        int maxPassengers = 55;

        System.out.println("\nВагони з кількістю пасажирів від "
                + minPassengers + " до " + maxPassengers + ":");

        RailCar[] found = train.findCarsByPassengerRange(minPassengers, maxPassengers);

        if (found.length == 0) {
            System.out.println("Не знайдено жодного вагона в цьому діапазоні.");
        } else {
            for (RailCar car : found) {
                System.out.println(car);
            }
        }
    }
}

abstract class RailCar {

    private int passengerCount;
    private int baggageCount;  
    private int comfortLevel;   

    public RailCar(int passengerCount, int baggageCount, int comfortLevel) {
        this.passengerCount = passengerCount;
        this.baggageCount = baggageCount;
        this.comfortLevel = comfortLevel;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public int getBaggageCount() {
        return baggageCount;
    }

    public int getComfortLevel() {
        return comfortLevel;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "Тип: " + getType()
                + ", пасажирів: " + passengerCount
                + ", багаж (місць): " + baggageCount
                + ", комфортність: " + comfortLevel;
    }
}

class EconomyCar extends RailCar {

    public EconomyCar(int passengerCount, int baggageCount, int comfortLevel) {
        super(passengerCount, baggageCount, comfortLevel);
    }

    @Override
    public String getType() {
        return "Економ";
    }
}

class CoupeCar extends RailCar {

    public CoupeCar(int passengerCount, int baggageCount, int comfortLevel) {
        super(passengerCount, baggageCount, comfortLevel);
    }

    @Override
    public String getType() {
        return "Купе";
    }
}

class LuxuryCar extends RailCar {

    public LuxuryCar(int passengerCount, int baggageCount, int comfortLevel) {
        super(passengerCount, baggageCount, comfortLevel);
    }

    @Override
    public String getType() {
        return "Люкс";
    }
}

/**
 * Пасажирський потяг, що складається з масиву вагонів.
 */
class PassengerTrain {

    private RailCar[] cars;

    public PassengerTrain(RailCar[] cars) {
        this.cars = cars;
    }

    public RailCar[] getCars() {
        return cars;
    }

    /**
     * Загальна кількість пасажирів.
     */
    public int calculateTotalPassengers() {
        int total = 0;
        for (RailCar car : cars) {
            total += car.getPassengerCount();
        }
        return total;
    }

    /**
     * Загальна кількість місць для багажу.
     */
    public int calculateTotalBaggage() {
        int total = 0;
        for (RailCar car : cars) {
            total += car.getBaggageCount();
        }
        return total;
    }

    /**
     * Сортування за рівнем комфортності (1 — найвище).
     */
    public void sortByComfortLevel() {
        Arrays.sort(cars, new Comparator<RailCar>() {
            @Override
            public int compare(RailCar o1, RailCar o2) {
                return Integer.compare(o1.getComfortLevel(), o2.getComfortLevel());
            }
        });
    }

    /**
     * Пошук вагонів за діапазоном кількості пасажирів.
     */
    public RailCar[] findCarsByPassengerRange(int min, int max) {
        int count = 0;
        for (RailCar car : cars) {
            int passengers = car.getPassengerCount();
            if (passengers >= min && passengers <= max) {
                count++;
            }
        }

        RailCar[] result = new RailCar[count];
        int index = 0;
        for (RailCar car : cars) {
            int passengers = car.getPassengerCount();
            if (passengers >= min && passengers <= max) {
                result[index++] = car;
            }
        }

        return result;
    }

    /**
     * Вивести всі вагони.
     */
    public void printCars() {
        for (int i = 0; i < cars.length; i++) {
            System.out.println("Вагон " + (i + 1) + ": " + cars[i]);
        }
    }
}
