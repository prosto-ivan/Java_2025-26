/* C11 = 7 -> Визначити клас меблі, який складається як мінімум з 5-и полів.
    Створити клас із виконавчим методом, в якому створити масив з об’єктів класу визначеному варіантом (п. 2). 
    Відсортувати масив, за одним з полів за зростанням, а за іншим — за спаданням, 
    використовуючи при цьому стандартні засоби сортування (). Після цього знайти в масиві об’єкт, який ідентичний заданому.
*/

import java.util.Arrays;

public class Mebli {
    String name;
    String purpose;
    double weigth;
    String material;
    int count;
    
    public Mebli(String name, String purpose, double weigth, String material, int count) {
        this.name = name;
        this.purpose = purpose;
        this.weigth = weigth;
        this.material = material;
        this.count = count;
    }
    
//сортування масиву по кількостіі за спаданням: 
    static  void sortArrayFromMaxCount(Mebli[] meblis) {
        Arrays.sort(meblis, (m1, m2) -> Integer.compare(m2.count, m1.count));
    }

//сортування за зростанням назви
    static  void sortArrayFromMinName(Mebli[] meblis) {
        Arrays.sort(meblis, (m1, m2) -> m1.name.compareTo(m2.name));
    }
// Вивід всіх об'єктів
    static  void printArray(Mebli[] meblis) {
        System.out.println("\n");
        for (Mebli mebel : meblis) {
            System.out.println(
                "Name: " + mebel.name + ", Purpose: " + mebel.purpose + ", Weight: " + mebel.weigth + ", Material: " + mebel.material + ", Count: " + mebel.count);
            }
        System.out.println("\n");
    }

/* 
  Метод для перевірки наявності заданого об'єкту у масиві 
  за допомогою методу equals який ми раніше перезаписали
*/ 
static boolean containsMebli(Mebli[] meblis, Mebli target) {
        for (Mebli m : meblis) {
            if (m.equals(target)) {
                return true;
            }
        }
        return false;
    }
//Перезаваантаження методу equals під наш об'єкт
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mebli other = (Mebli) obj;
        return Double.compare(other.weigth, weigth) == 0 &&
                count == other.count &&
                name.equals(other.name) &&
                purpose.equals(other.purpose) &&
                material.equals(other.material);
    }

    public static void main(String[] args) {
        Mebli[] listMeblis = new Mebli[5];
        listMeblis[0] = new Mebli("Table", "For working", 18.5, "Wood", 2);
        listMeblis[1] = new Mebli("Chair", "For sitting", 5.2, "Metal", 2);
        listMeblis[2] = new Mebli("Wardrobe", "For storing clothes", 45.0, "Chipboard", 3);
        listMeblis[3] = new Mebli("Sofa", "For relaxing", 32.8, "Fabric", 3);
        listMeblis[4] = new Mebli("Bed", "For sleeping", 40.3, "Wood", 3);

        sortArrayFromMinName(listMeblis);
        sortArrayFromMaxCount(listMeblis);
        printArray(listMeblis);

        Mebli findMebl = new Mebli("Sofa", "For relaxing", 32.8, "Fabric", 3);
        System.out.println("Чи присутній заданий об'єкт у масиві" + containsMebli(listMeblis, findMebl));
    }
}

