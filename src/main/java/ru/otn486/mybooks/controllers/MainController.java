package ru.otn486.mybooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.otn486.mybooks.dao.BookDao;
import ru.otn486.mybooks.models.Book;
import ru.otn486.mybooks.models.SearchAttribute;
import ru.otn486.mybooks.service.BookService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private BookService bookService;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping(value = {"/{page}","/"})
    public String index(@ModelAttribute SearchAttribute searchAttribute,
                        @PathVariable(value = "page", required = false) String page,
                        @RequestParam(required=false) String action,
                        HttpServletRequest req,
                        Model model) {
        if (action == null || action.equals("Очистить")) {
            searchAttribute = null;
        }

        PagedListHolder<Book> pagedListHolder = null;
        if (page==null){
            List<Book> listBook = bookService.listBooks(searchAttribute);
            pagedListHolder= new PagedListHolder<Book>();
            pagedListHolder.setSource(listBook);
            pagedListHolder.setPageSize(10);
            req.getSession().setAttribute("pagedListBook", pagedListHolder);

        } else if (page.equals("next")){
            pagedListHolder=(PagedListHolder<Book>)req.getSession().getAttribute("pagedListBook");
            pagedListHolder.nextPage();
        } else if (page.equals("prev")){
            pagedListHolder=(PagedListHolder<Book>)req.getSession().getAttribute("pagedListBook");
            pagedListHolder.previousPage();
        } else {
            pagedListHolder=(PagedListHolder<Book>)req.getSession().getAttribute("pagedListBook");
            int pageNum = Integer.parseInt(page);
            pagedListHolder.setPage(pageNum-1);
        }

        /*model.addAttribute("pagedListBook", pagedListBook);
        model.addAttribute("listBooks", listBook);*/
        model.addAttribute("searchAttribute", searchAttribute);
        return "index";
    }



    @GetMapping("/add")
    public String add(@ModelAttribute("book") Book book, Model model){
        model.addAttribute("title", "add");
        model.addAttribute("book", new Book());
        return "data";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("title", "edit");
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "data";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute Book book){
        System.out.println(book.toString());
        if (book.getId()==0){
            bookService.addBook(book);
        } else {
            bookService.updateBook(book);
        }
        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id, Model model){
        bookService.removeBook(id);
        return "redirect:/";
    }

    @GetMapping("/read/{id}")
    public String read(@PathVariable("id") int id, Model model){
        Book book = bookService.getBookById(id);
        book.setReadAlready(true);
        bookService.updateBook(book);
        return "redirect:/";
    }

    @GetMapping("/test")
    public String test(Model model){
        Book book = bookService.getBookById(10);
        String s= book.toString();
        model.addAttribute("book", book);
        return "test";
    }



}
