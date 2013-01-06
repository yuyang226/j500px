
package com.github.yuyang226.j500px;

import java.util.ArrayList;

/**
 * Search result list with additional meta data.
 *
 * @author yayu
 */
public class SearchResultList<E> extends ArrayList<E>{

    private static final long serialVersionUID = -7962319033867024935L;
    private int currentPage;
    private int totalPages;
    private int perPage;
    private int totalItems;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int page) {
        this.currentPage = page;
    }

    public void setPage(String page) {
        if (page != null && page.length() != 0) {
            setCurrentPage(Integer.parseInt(page));
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int pages) {
        this.totalPages = pages;
    }

    public void setTotalPages(String pages) {
        if (pages != null && pages.length() != 0) {
            setTotalPages(Integer.parseInt(pages));
        }
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public void setPerPage(String perPage) {
        if (perPage != null && perPage.length() != 0) {
            setPerPage(Integer.parseInt(perPage));
        }
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int total) {
        this.totalItems = total;
    }

    public void setTotalItems(String total) {
        if (total != null && total.length() != 0) {
            setTotalItems(Integer.parseInt(total));
        }
    }

}
