package cursor.rybak.store.sort;

import java.util.Arrays;
import java.util.List;

public interface SortConstants {
    String YEAR = "year";
    String PRICE = "price";
    String REGISTRATION = "registration";

    List<String> SORT_CRITERIA = Arrays.asList(YEAR, PRICE, REGISTRATION);
}
