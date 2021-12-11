package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.hustleemarketrest.entity.Transaction;
import org.emarket.hustle.hustleemarketrest.entity.request.GetRequestTransaction;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.basePath}")
public class TransactionRestController
{
	Logger log = Logger.getLogger(TransactionRestController.class.getName());

	@Autowired
	TransactionService transactionService;

	/*
	 * #######################################
	 * ############# GET TRANSACTION ###############
	 * #######################################
	 */

	@GetMapping("/transactions")
	public List<Transaction> getTransaction(@RequestBody(required = false) GetRequestTransaction getRequest)
	{
		if(getRequest == null)
		{
			return transactionService.getTransaction();
		}

		return transactionService.getTransaction(getRequest);
	}

	/*
	 * #######################################
	 * ########### GET TRANSACTION BY ID #####
	 * #######################################
	 */

	@GetMapping("/transactions/{id}")
	public Transaction getTransactionById(@PathVariable int id)
	{
		return transactionService.getTransactionById(id);
	}

	/*
	 * #######################################
	 * ############# ADD TRANSACTION #########
	 * #######################################
	 */

	@PostMapping("/transactions")
	public List<Transaction> addTransactions(@RequestBody List<Transaction> transactions)
	{

		transactionService.saveTransaction(transactions);

		return transactions;
	}

	/*
	 * #######################################
	 * ########### UPDATE TRANSACTION ##############
	 * #######################################
	 */

	@PutMapping("/transactions")
	public Transaction updateTransaction(@RequestBody Transaction transaction)
	{
		transactionService.saveTransaction(transaction);
		return transaction;
	}

	/*
	 * #######################################
	 * ########### DELETE HISTORY ############
	 * #######################################
	 */

	@DeleteMapping("/transactions")
	public ProcessConfirmation deleteTransaction(@RequestBody Transaction transaction)
	{
		transactionService.deleteTransaction(transaction);

		return new ProcessConfirmation("SUCCESS", "TRANSACTION",
				"THE TRANSACTION WITH ID:" + transaction.getId() + " WAS DELETED.");
	}

	@DeleteMapping("/transactions/{id}")
	public ProcessConfirmation deleteTransaction(@PathVariable int id)
	{
		transactionService.deleteTransactionById(id);
		return new ProcessConfirmation("SUCCESS", "TRANSACTION",
				"THE TRANSACTION WITH ID:" + id + " WAS DELETED.");
	}

	/*
	 * #######################################
	 * ############# PREPARE TRANSACTION #####
	 * #######################################
	 */

	@GetMapping("/checkouts/{id}")
	public List<Transaction> checkout(@PathVariable int id)
	{
		return transactionService.checkout(id);
	}
}
