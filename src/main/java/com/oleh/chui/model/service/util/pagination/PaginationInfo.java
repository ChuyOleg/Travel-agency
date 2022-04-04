package com.oleh.chui.model.service.util.pagination;

import com.oleh.chui.model.entity.Tour;

import java.util.List;

public class PaginationInfo {

    private List<Tour> tourListPage;
    private int toursCount;
    private int pagesCount;

    public PaginationInfo() {}

    public PaginationInfo(List<Tour> tourList, int toursCount, int pagesNumber) {
        this.tourListPage = tourList;
        this.toursCount = toursCount;
        this.pagesCount = pagesNumber;
    }

    public List<Tour> getTourListPage() {
        return tourListPage;
    }

    public void setTourListPage(List<Tour> tourListPage) {
        this.tourListPage = tourListPage;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public int getToursCount() {
        return toursCount;
    }

    public void setToursCount(int toursCount) {
        this.toursCount = toursCount;
    }
}
