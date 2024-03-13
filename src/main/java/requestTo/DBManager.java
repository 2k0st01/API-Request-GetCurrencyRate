package requestTo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class DBManager {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("API-Request");
    private static EntityManager em = emf.createEntityManager();
    private static final List<CurrencyRate> list = GetConnection.getApiDate();

    public static void updateDate() {
        em.getTransaction().begin();
        try {
            String jpql = "DELETE FROM CurrencyRate";
            int affectedRows = em.createQuery(jpql).executeUpdate();
            System.out.println("Видалено записів: " + affectedRows);
            for (CurrencyRate cr : list) {
//                System.out.println(cr);
                em.persist(cr);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static CurrencyRate getRateByDate(LocalDate date) {
        try {
            TypedQuery<CurrencyRate> typedQuery = em.createQuery(
                    "SELECT c FROM CurrencyRate c  WHERE c.exchangeDate = :date", CurrencyRate.class);
            typedQuery.setParameter("date", date);
            CurrencyRate cr = typedQuery.getSingleResult();

            return cr;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getRateByDates(LocalDate from, LocalDate to) {
        try {
            TypedQuery<CurrencyRate> typedQuery = em.createQuery(
                    "SELECT c FROM CurrencyRate c WHERE c.exchangeDate >= :from AND c.exchangeDate <= :to",
                    CurrencyRate.class);
            typedQuery.setParameter("from", from);
            typedQuery.setParameter("to", to);
            List<CurrencyRate> list = typedQuery.getResultList();

            for (CurrencyRate cr : list)
                System.out.println(cr);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAnAverageRate(LocalDate from, LocalDate to) {
        int temp = 0;
        double sum = 0;
        try {
            TypedQuery<CurrencyRate> typedQuery = em.createQuery(
                    "SELECT c FROM CurrencyRate c WHERE c.exchangeDate >= :from AND c.exchangeDate <= :to",
                    CurrencyRate.class);
            typedQuery.setParameter("from", from);
            typedQuery.setParameter("to", to);
            List<CurrencyRate> list = typedQuery.getResultList();

            for (CurrencyRate cr : list){
                temp++;
                sum = sum+ cr.getRate();
            }
            System.out.println(sum/temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
