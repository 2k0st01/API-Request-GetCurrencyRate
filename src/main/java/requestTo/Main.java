package requestTo;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        //DBManager.updateDate();
        System.out.println(DBManager.getRateByDate(LocalDate.of(2024,01,13)));
        DBManager.getRateByDates(LocalDate.of(2024,01,01), LocalDate.of(2024,01,13));
    }
}
