package com.tie.collegeapp.ui.Ebook;

public class EbookModel {
    private String EbookTitle, EbookUrl;

    public EbookModel() {
    }

    public EbookModel(String ebookTitle, String EbookUrl) {
        EbookTitle = ebookTitle;
        this.EbookUrl = EbookUrl;
    }

    public String getEbookTitle() {
        return EbookTitle;
    }

    public void setEbookTitle(String ebookTitle) {
        EbookTitle = ebookTitle;
    }

    public String getEbookUrl() {
        return EbookUrl;
    }

    public void setEbookUrl(String tile) {
        this.EbookUrl = EbookUrl;
    }
}
