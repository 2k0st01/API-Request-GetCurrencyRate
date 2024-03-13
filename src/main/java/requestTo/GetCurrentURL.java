package requestTo;
import java.time.LocalDate;

public class GetCurrentURL {
    private static String url;
    private static LocalDate currentDate = LocalDate.now();

    public static String getUrl(){
        url = "https://bank.gov.ua/NBU_Exchange/exchange_site?start=20240101&end=%20"
                +currentDate.toString().replace("-","")+
                "&valcode=usd&sort=exchangedate&order=desc&json";
        return url;
    }
}
