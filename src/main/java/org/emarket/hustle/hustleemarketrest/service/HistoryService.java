package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.History;
import org.emarket.hustle.hustleemarketrest.entity.request.GetRequestHistory;

public interface HistoryService
{
	public List<History> getHistory();

	public List<History> getHistory(GetRequestHistory getRequest);

	public History getHistoryById(int id);

	public void saveHistory(History history);

	public void deleteHistory(History history);

	public void deleteHistoryById(int id);

}
