package me.whaless.app.domain.model;

import java.util.List;

/**
 * User: Joy
 * Date: 2017-01-08
 * Time: 18:27
 */

public class PageListModel<T> {

	// 当前页
	private int currentPage;
	private int totalPage;
	// 总数
	private int total;
	// 每页数量
	private int pageNumber;
	private List<T> items;
	private boolean end;

	public PageListModel() {
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}
}
