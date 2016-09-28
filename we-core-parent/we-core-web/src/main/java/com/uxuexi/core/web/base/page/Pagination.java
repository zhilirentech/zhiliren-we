package com.uxuexi.core.web.base.page;

import java.util.List;

import org.nutz.dao.pager.Pager;
import org.nutz.log.Log;
import org.nutz.log.Logs;

public class Pagination extends Pager {

	/**
	 * 当前页的数据
	 */
	private List<?> list;

	private static final long serialVersionUID = 8848523495013555357L;

	private static final Log log = Logs.get();

	// private static final int FIRST_PAGE_NUMBER = 1;

	private int pageNumber;
	private int pageSize;
	private int pageCount;
	private int recordCount;

	public Pagination() {
		pageNumber = 1;
		pageSize = DEFAULT_PAGE_SIZE;
	}

	public Pagination(int pageNumber, int pageSize, int recordCount, List<?> data) {
		setPageNumber(pageNumber);
		setPageSize(pageSize);
		setRecordCount(recordCount);
		setList(data);
	}

	@Override
	public Pagination resetPageCount() {
		pageCount = -1;
		return this;
	}

	@Override
	public int getPageCount() {
		if (pageCount < 0)
			pageCount = (int) Math.ceil((double) recordCount / pageSize);
		return pageCount;
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getRecordCount() {
		return recordCount;
	}

	@Override
	public Pagination setPageNumber(int pn) {
		if (1 > pn && log.isInfoEnabled())
			log.infof("PageNumber shall start at 1, but input is %d, that mean pager is disable", pn);
		pageNumber = pn;
		return this;
	}

	@Override
	public Pagination setPageSize(int pageSize) {
		this.pageSize = (pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE);
		return resetPageCount();
	}

	@Override
	public Pagination setRecordCount(int recordCount) {
		this.recordCount = recordCount > 0 ? recordCount : 0;
		this.pageCount = (int) Math.ceil((double) recordCount / pageSize);
		return this;
	}

	@Override
	public int getOffset() {
		return pageSize * (pageNumber - 1);
	}

	@Override
	public String toString() {
		return String
				.format("size: %d, total: %d, page: %d/%d", pageSize, recordCount, pageNumber, this.getPageCount());
	}

	@Override
	public boolean isFirst() {
		return pageNumber == 1;
	}

	@Override
	public boolean isLast() {
		if (pageCount == 0)
			return true;
		return pageNumber == pageCount;
	}

	@Override
	public boolean hasNext() {
		return !isLast();
	}

	@Override
	public boolean hasPrevious() {
		return !isFirst();
	}

	/**
	 * 获得分页内容
	 * 
	 * @return
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * 设置分页内容
	 * 
	 * @param list
	 */
	public void setList(List<?> list) {
		this.list = list;
	}
}
