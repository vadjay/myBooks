package ru.otn486.mybooks.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otn486.mybooks.models.Book;
import ru.otn486.mybooks.models.SearchAttribute;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BookDao {

    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addBook(Book book){
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }


    public void updateBook(Book book){
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
    }


    public void removeBook(int id){
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book)session.load(Book.class, new Integer(id));
        if (book!=null)
            session.delete(book);
    }


    public Book getBookById(int id){
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        return book;
    }



    public List<Book> listBooks(SearchAttribute searchAttribute) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.select(root);
        if(searchAttribute!=null&&!searchAttribute.isEmpty()){
            if (searchAttribute.getTitle()!=null&&searchAttribute.getTitle()!=""){
                criteriaQuery.where(builder.like(root.get("title"), "%"+searchAttribute.getTitle()+"%"));
            }
            if (searchAttribute.getAuthor()!=null&&searchAttribute.getAuthor()!=""){
                criteriaQuery.where(builder.like(root.get("author"), "%"+searchAttribute.getAuthor()+"%"));
            }
            if (searchAttribute.getIsbn()!=null&&searchAttribute.getIsbn()!=""){
                criteriaQuery.where(builder.like(root.get("isbn"), "%"+searchAttribute.getIsbn()+"%"));
            }

        }

        Query<Book> query = session.createQuery(criteriaQuery);
        return query.list();
    }


}

