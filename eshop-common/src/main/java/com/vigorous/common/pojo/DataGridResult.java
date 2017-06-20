package com.vigorous.common.pojo;

import java.util.List;

public class DataGridResult {
	private long total;
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	public DataGridResult(long toa,List<?> datas) {
		this.total = toa;
		this.rows = datas;
	}
	
	public DataGridResult() {
	}
}
