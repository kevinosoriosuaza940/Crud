package software.ias.training.api.controller;

import org.springframework.web.bind.annotation.*;
import software.ias.training.api.domain.BankTransaction;
import software.ias.training.api.repository.TransactionsRepository;

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

}
