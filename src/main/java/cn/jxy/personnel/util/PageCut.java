package cn.jxy.personnel.util;

import java.util.LinkedList;
import java.util.List;

public class PageCut<T> {
	private int currentPage;//当前第几页
	private int pageSize;//每页的数量
	private int pageNum;/*页数*/
	private int count;/*所有页共有多少元素*/
	
	private List<T> data;/*本页元素的集合*/
	
	private int nextPage;/*下一页是第几页*/
	private int prePage;/*上一页是第几页*/
	
	public PageCut(){};
	
	public PageCut(int currentPage, int pageSize, int count) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.count = count;
		if(count ==0){
			pageNum=1;
		}else{
			pageNum=(count-1)/pageSize+1;
		}
		
		if(currentPage > pageNum){
			currentPage = pageNum;
		}
		prePage=currentPage>1?currentPage-1:1;
		nextPage=currentPage<pageNum?currentPage+1:pageNum;
		this.data = new LinkedList<>();
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
}
