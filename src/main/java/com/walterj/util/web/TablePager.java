package com.walterj.util.web;

import com.walterj.util.Range;

import java.util.List;

/**
 * @author Walter Jordan
 */
public class TablePager<T> {
    private int rowsPerPage = 10;
    private long totalRows = 0;
    private int currentPage = 0;
    private int sortBy = -1;
    private boolean sortAscending = true;

    private List<String> headers;
    private List<T> rows;

    public TablePager(int rowsPerPage,
                      long totalRows) {
        this(rowsPerPage,totalRows, -1, true);
    }

    public TablePager(int rowsPerPage,
                      long totalRows,
                      int sortBy,
                      boolean asc) {
        this.rowsPerPage = rowsPerPage;
        this.totalRows = totalRows;
        this.sortBy = sortBy;
        this.sortAscending = asc;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getRowOffset() {
        return currentPage * rowsPerPage;
    }

    public int getTotalPages() {
        return rowsPerPage > 0 ? (int)totalRows/rowsPerPage : -1;
    }

    public Range<Integer> getRowRange() {

        int lo = (currentPage*rowsPerPage);
        int hi = (lo + rowsPerPage <= totalRows) ? lo + rowsPerPage : (int)totalRows;
        return new Range<>(lo+1, hi);
    }

    @Override public String toString() {
        return "TablePager{" +
            "\n  rowsPerPage=" + rowsPerPage +
            "\n  totalRows=" + totalRows +
            "\n  currentPage=" + currentPage +
            "\n  rows=" + ((rows == null) ? "0" : rows.size()) +
            "\n  sortBy=" + sortBy +
            "\n  sortAsc=" + sortAscending +
            "\n}";
    }
}