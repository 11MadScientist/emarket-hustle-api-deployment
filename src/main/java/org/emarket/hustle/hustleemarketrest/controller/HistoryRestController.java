package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.History;
import org.emarket.hustle.hustleemarketrest.entity.request.GetRequestHistory;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.service.HistoryService;
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
public class HistoryRestController
{
	@Autowired
	HistoryService historyService;

	/*
	 * #######################################
	 * ########### GET HISTORY ###############
	 * #######################################
	 */

	@GetMapping("/histories")
	public List<History> getOrder(@RequestBody(required = false) GetRequestHistory getRequest)
	{
		if(getRequest == null)
		{
			return historyService.getHistory();
		}

		return historyService.getHistory(getRequest);
	}

	/*
	 * #######################################
	 * ######## GET HISTORY BY ID ############
	 * #######################################
	 */

	@GetMapping("/histories/{id}")
	public History getOrderById(@PathVariable int id)
	{
		return historyService.getHistoryById(id);
	}

	/*
	 * #######################################
	 * ############# ADD HISTORY ###############
	 * #######################################
	 */

	@PostMapping("/histories")
	public ProcessConfirmation addHistory()
	{
		return new ProcessConfirmation("FAILED", "HISTORY", "UNSUPPORTED ENDPOINT");
	}

	/*
	 * #######################################
	 * ########### UPDATE HISTORY ############
	 * #######################################
	 */

	@PutMapping("/histories")
	public History updateHistory(@RequestBody History history)
	{
		historyService.saveHistory(history);
		return history;
	}

	/*
	 * #######################################
	 * ########### DELETE HISTORY ############
	 * #######################################
	 */

	@DeleteMapping("/histories")
	public ProcessConfirmation deleteHistory(@RequestBody History history)
	{
		historyService.deleteHistory(history);

		return new ProcessConfirmation("SUCCESS", "HISTORY",
				"THE HISTORY WITH ID:" + history.getId() + " WAS DELETED.");
	}

	@DeleteMapping("/histories/{id}")
	public ProcessConfirmation deleteHistory(@PathVariable int id)
	{
		historyService.deleteHistoryById(id);
		return new ProcessConfirmation("SUCCESS", "HISTORY",
				"THE HISTORY WITH ID:" + id + " WAS DELETED.");
	}

}
