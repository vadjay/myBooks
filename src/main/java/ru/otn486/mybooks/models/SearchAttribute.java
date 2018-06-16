package ru.otn486.mybooks.models;

public class SearchAttribute {
    private String title;
    private String author;
    private String isbn;


    public SearchAttribute() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }






    public boolean isEmpty(){
        if ((title==null || title=="")&&
                (author==null || author=="")&&
                (isbn==null||isbn=="")){
            return true;
        }
        return false;
    }

}
