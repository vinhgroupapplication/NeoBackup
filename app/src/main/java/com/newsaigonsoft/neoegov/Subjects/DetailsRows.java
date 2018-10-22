package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by VinhCN on 4/26/2017.
 */

public class DetailsRows {
    //    String Title;
//    String TextContent;
//
//    public DetailsRows(String title, String textContent) {
//        Title = title;
//        TextContent = textContent;
//    }
//
//    public String getTitle() {
//        return Title;
//    }
//
//    public String getTextContent() {
//        return TextContent;
//    }
    String title;
    String style;
    String value;

    public DetailsRows(String title, String style, String value) {
        this.title = title;
        this.style = style;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
