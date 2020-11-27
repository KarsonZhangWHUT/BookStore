package com.karson.bean;

import java.util.List;

public class Page<T> {

	private int pageNo;
	private int totalPage;
	private int totalCount;
	private int pageSize = 4;

	// 告诉数据库从哪个索引开始查
	private int index;
	private boolean hasNext;
	private boolean hasPre;

	// 封装了查询出来的分页数据
	private List<T> pageData;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		pageNo = pageNo > 0 ? pageNo : 1;

		// 要想获取真正的totalPage必须有totalCount、pageSize,所以再service中应先设置这两个值
		pageNo = pageNo < getTotalPage() ? pageNo : getTotalPage();
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		int t = getTotalCount() / getPageSize();
		if (!(getTotalCount() % getPageSize() == 0))
			t++;
		return t;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getIndex() {
		int i = (getPageNo() - 1) * getPageSize();
		if (i < 0)
			i = 0;
		return i;
	}

	public boolean isHasNext() {
		return getPageNo() < getTotalPage();
	}

	public boolean isHasPre() {
		return getPageNo() > 1;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public Page(int pageNo, int totalPage, int totalCount, int pageSize, int index, boolean hasNext, boolean hasPre,
			List<T> pageData) {
		super();
		this.pageNo = pageNo;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.index = index;
		this.hasNext = hasNext;
		this.hasPre = hasPre;
		this.pageData = pageData;
	}

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", pageSize="
				+ pageSize + ", index=" + index + ", hasNext=" + hasNext + ", hasPre=" + hasPre + ", pageData="
				+ pageData + "]";
	}

}
