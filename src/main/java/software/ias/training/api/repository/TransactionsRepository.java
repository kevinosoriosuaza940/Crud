package software.ias.training.api.repository;

import org.springframework.stereotype.Repository;
import software.ias.training.api.domain.BankTransaction;
import software.ias.training.api.test.Database;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.List;


@Repository
public class TransactionsRepository {
    private final Database database;

    // dependency inversion
    public TransactionsRepository(Database database) {
        this.database = database;
    }


    public void createBankTransaction(BankTransaction transaction) {
        database.insertData(transaction);
    }

    public List<Object> listBankTransactions() {
        return database.listData();
    }

    public void putBankTransaction(BankTransaction transaction, int index){
        int sizeList = listBankTransactions().size();

        if (index <= sizeList ){
            listBankTransactions().set(index, transaction);
            System.out.println("OK: 200");
        }
    }

    public void  deleteBankTransaction(int index){
        int sizeList = listBankTransactions().size();

        if (index <= sizeList ){
            listBankTransactions().remove(index);
            System.out.println("Delete ok");
        }
    }

    public List<Object> filterByDate (LocalDate fechaInicial, LocalDate fechaFinal){
        long numOfDaysBetween = ChronoUnit.DAYS.between(fechaInicial, fechaFinal);
        List<Object> transactionFiltradas = new ArrayList<Object>();
        List rangeDates = IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> fechaInicial.plusDays(i))
                .collect(Collectors.toList());
        for(int i = 0; i < listBankTransactions().size(); i++) {
            boolean found = rangeDates.contains(listBankTransactions().get(i));
            if(found) {
                transactionFiltradas.add(listBankTransactions().get(i));
                System.out.println("found");
            }
        }
        return transactionFiltradas;
    }

}
