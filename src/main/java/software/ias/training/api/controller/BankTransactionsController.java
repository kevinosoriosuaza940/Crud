package software.ias.training.api.controller;

import org.springframework.web.bind.annotation.*;
import software.ias.training.api.domain.BankTransaction;
import software.ias.training.api.repository.TransactionsRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class BankTransactionsController {
    private final TransactionsRepository repository;

    public BankTransactionsController(TransactionsRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Object> listTransactions() {
        return repository.listBankTransactions();
    }

    @PostMapping("/create")
    public void createTransaction(
            @RequestBody BankTransaction transaction
    ) {
        repository.createBankTransaction(transaction);
        System.out.println(transaction);
    }

    @PutMapping("/update")
    public void updateTransaction(
            @RequestBody BankTransaction transaction, @RequestParam int index
    ){
        repository.putBankTransaction(transaction, index);
    }
    @DeleteMapping("/delete")
    public void deleteTransaction(
            @RequestParam int index
    ){
        repository.deleteBankTransaction(index);
    }

    @RequestMapping(value="/filter", method = RequestMethod.GET)
    public List<Object>  filterTransaction(
            @RequestBody
            @RequestParam String fechaInicial, @RequestParam String fechaFinal
            ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate fechaIni = LocalDate.parse(fechaInicial, formatter);
        LocalDate fechaFin = LocalDate.parse(fechaFinal, formatter);
        return repository.filterByDate(fechaIni, fechaFin);
    }
}
