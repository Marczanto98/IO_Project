package com.example.app2;


public class Info {

    private String title;
    private String date;
    private String apply;
    private String info;
    private boolean expanded;

    public Info(String title, String date, String apply, String info) {
        this.title = title;
        this.date = date;
        this.apply = apply;
        this.info = info;
        this.expanded = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", apply='" + apply + '\'' +
                ", info='" + info + '\'' +
                ", expanded=" + expanded +
                '}';
    }
}
