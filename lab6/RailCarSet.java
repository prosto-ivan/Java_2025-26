import java.util.*;

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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        RailCar other = (RailCar) obj;
        return this.passengerCount == other.passengerCount &&
               this.baggageCount == other.baggageCount &&
               this.comfortLevel == other.comfortLevel;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(passengerCount, baggageCount, comfortLevel);
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

class RailCarSet<T extends RailCar> implements Set<T> {
    
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private int size;
    
    public RailCarSet() {
        this.head = null;
        this.size = 0;
    }
    
    public RailCarSet(T element) {
        this();
        add(element);
    }
    
    public RailCarSet(Collection<? extends T> collection) {
        this();
        addAll(collection);
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        
        Node current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;
            private Node previous = null;
            private Node beforePrevious = null;
            
            @Override
            public boolean hasNext() {
                return current != null;
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                beforePrevious = previous;
                previous = current;
                T data = current.data;
                current = current.next;
                return data;
            }
            
            @Override
            public void remove() {
                if (previous == null) {
                    throw new IllegalStateException();
                }
                
                if (beforePrevious == null) {
                    head = previous.next;
                } else {
                    beforePrevious.next = previous.next;
                }
                
                previous = beforePrevious;
                size--;
            }
        };
    }
    
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        int index = 0;
        
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        
        return array;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            a = (E[]) java.lang.reflect.Array.newInstance(
                a.getClass().getComponentType(), size);
        }
        
        Node current = head;
        int index = 0;
        
        while (current != null) {
            a[index++] = (E) current.data;
            current = current.next;
        }
        
        if (a.length > size) {
            a[size] = null;
        }
        
        return a;
    }
    
    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new NullPointerException("Null elements are not allowed");
        }
         
        if (contains(element)) {
            return false;
        }
        
        Node newNode = new Node(element);
        
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        
        size++;
        return true;
    }
    
    @Override
    public boolean remove(Object o) {
        if (o == null || head == null) {
            return false;
        }
        
        if (head.data.equals(o)) {
            head = head.next;
            size--;
            return true;
        }
        
        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(o)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }
    
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<T> it = iterator();
        
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        
        return modified;
    }
    
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            if (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }
    
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        
        sb.append("]");
        return sb.toString();
    }
}