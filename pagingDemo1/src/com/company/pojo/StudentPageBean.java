package com.company.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/11 17:00
 */
public class StudentPageBean<T> implements Serializable {
    private List<T> studentData;//当前页数据
    private Integer pageSize;//每页有多少个
    private Integer totalSize;//总数据多少个
    private Integer totalPages;//一共多少页
    private Integer currentPage;//当前页数

    @Override
    public String toString() {
        return "StudentPageBean{" +
                "studentData=" + studentData +
                ", pageSize=" + pageSize +
                ", totalSize=" + totalSize +
                ", totalPages=" + totalPages +
                ", currentPage=" + currentPage +
                '}';
    }

    public List<T> getStudentData() {
        return studentData;
    }

    public void setStudentData(List<T> studentData) {
        this.studentData = studentData;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public StudentPageBean() {
    }

    public StudentPageBean(List<T> studentData, Integer pageSize, Integer totalSize, Integer totalPages, Integer currentPage) {
        this.studentData = studentData;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
}
