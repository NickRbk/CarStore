package cursor.rybak.store.sort;

import cursor.rybak.store.domain.model.Car;

import java.util.Comparator;

class OrderByKeyComparator implements Comparator<Car>, SortConstants {
    private String key;

    OrderByKeyComparator(String key) {
        this.key = key;
    }

    @Override
    public int compare(Car o1, Car o2) {
        if(PRICE.equals(key)) {
            return (int) (o1.getPrice() - o2.getPrice());
        }

        if(YEAR.equals(key)) {
            return o1.getYear() - o2.getYear();
        }

        if(REGISTRATION.equals(key)) {
            return o1.getCountryOfRegistration().compareTo(o2.getCountryOfRegistration());
        }

        return 0;
    }
}
