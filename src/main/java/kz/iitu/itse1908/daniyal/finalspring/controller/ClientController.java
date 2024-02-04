package kz.iitu.itse1908.daniyal.finalspring.controller;

import kz.iitu.itse1908.daniyal.finalspring.ExceptionHandlers.BlogapiException;
import kz.iitu.itse1908.daniyal.finalspring.models.*;
import kz.iitu.itse1908.daniyal.finalspring.service.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController("ClientController")
@RequestMapping(value = "/api/client")
public class ClientController {
    //-----------------------Dependency Injection---------------------------
    BookService bookService;
    FineService fineService;
    RoleService roleService;
    TicketService ticketService;
    UserDetailsService userDetailsService;
    UserService userService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    @Autowired
    public void setFineService(FineService fineService) {
        this.fineService = fineService;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //--------------------------EndPoints--------------------------------------//

    private static final SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private static final SimpleDateFormat dateHours = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);
    DateFormat df = DateFormat.getDateInstance();


    //---------------Book------------------//

    @PostMapping("/buyBook")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ResponseStatus(HttpStatus.OK)
    public BuyRequest buyBook(@RequestBody BuyRequest request){
        List<Book> books = new ArrayList<>();
        Ticket ticket = null;
        System.out.println("\n\n-------------------------------------------BUY BOOK---------------------------------------------");
        String[] fl = request.getClientFullname().split(" ");
        if (userDetailsService.findByLnameAndFname(fl[0], fl[1]).isEmpty()) throw new BlogapiException(HttpStatus.NOT_FOUND, "No such user!");
        else {
            ticket = Ticket.ticketBuilder().setDeadline(request.getDeadline()).
                    setReceieveTime(date.format(new Date())).setUserDetails(userDetailsService.findByLnameAndFname(fl[0], fl[1]).get()).setStatus("Open").build();
            ticketService.save(ticket);
            System.out.println("New ticket: " + ticket);

            if (request.getBookNames().isEmpty()) throw new BlogapiException(HttpStatus.BAD_REQUEST, "No books chosen!");
            for(String bookName: request.getBookNames()){
                if (bookService.findByName(bookName) == null){
                    System.out.println("Sorry there is no book with name \"" + bookName + "\"");
                    continue;
                }
                if (bookService.findByName(bookName).getStock() < 1 && request.getBookNames().size() <= 1){
                    throw new BlogapiException(HttpStatus.CONFLICT, "Sorry " + bookService.findByName(bookName).getName() + " out of stock");
                }
                if (bookService.findByName(bookName).getStock() < 1) {
                    System.out.println("**Sorry " + bookService.findByName(bookName).getName() + " out of stock**");
                    continue;
                }

                Book book = bookService.findByName(bookName);
                book.setTicket(ticket);
                book.setStock(book.getStock()-1);
                bookService.updateBookById(book.getName(), book.getAuthor(),
                        book.getGenre(), book.getPages(), book.getCover(),
                        book.getStock()-1, bookService.findByName(bookName).getId());

                bookService.save(book);
                System.out.println("new book: " + book);
                books.add(bookService.findByName(bookName));
            }
        }

        System.out.println("-------------------------------------------BUY BOOK---------------------------------------------\n\n");
        return request;
    }

    @PostMapping("/returnBook")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ResponseStatus(HttpStatus.OK)
    public String returnBook(@RequestBody ReturnRequest request){
        List<Book> books = new ArrayList<>();
        String rez = "";
        System.out.println("\n\n-------------------------------------------RETURN TICKET---------------------------------------------");
        String[] fl = request.getClientFullname().split(" ");
        if (userDetailsService.findByLnameAndFname(fl[0], fl[1]).isEmpty()) throw new BlogapiException(HttpStatus.NOT_FOUND, "No such user!");
        else {


            //if (!fineService.findFineByLnameAndFname(fl[0], fl[1]).isEmpty()) return request; //AOP

            if (ticketService.findById(request.getTicketId()).isEmpty()) throw new BlogapiException(HttpStatus.NOT_FOUND, "No such ticket!");
            Ticket ticket = ticketService.findById(request.getTicketId()).get();

            books = bookService.findByTicket_Id(request.getTicketId());

            System.out.println("update ticket........");
            ticket.setStatus("Closed");
            ticketService.updateReceiveTimeAndDeadlineById(ticket.getStatus(), ticket.getReceiveTime(), ticket.getDeadline(), ticket.getId());
//            ticketService.deleteById(request.getTicketId());

            for(Book book: books){
                rez += "\"" + book.getName() + "\" ";
            }

        }
        System.out.println("-------------------------------------------RETURN TICKET---------------------------------------------\n\n");
        return "You returned " + rez;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 10000)//обязательно ждет пердыдущего выполнения задачи
    public void reportFixedDelay() throws ParseException {
        List<UserDetails> userDetails = userDetailsService.findAll();
        List<List<Ticket>> openTickets = new ArrayList<>();

        System.out.println("\n\n-------------------------------------------SCHEDULER---------------------------------------------");

        if (!userDetails.isEmpty()){
            for (UserDetails userDetailsIterator: userDetails){
                if (!ticketService.findByUserDetails_IdAndStatus(userDetailsIterator.getId(), "Open").isEmpty()){
                    openTickets.add(ticketService.findByUserDetails_IdAndStatus(userDetailsIterator.getId(), "Open"));
                }
            }

            if (!openTickets.isEmpty()){
                    Date deadline = new Date();
                    Date recieveTime = new Date();
                    int t = 0;
                    for (int i = 0; i < openTickets.size(); i++){
                        if (!openTickets.get(i).isEmpty()){
                            for (int j = 0; j < openTickets.get(i).size(); j++){
                                   // System.out.println((j+1) + "Deadline of " + openTickets.get(i).get(j).getUserDetails().getFname() + "s ticket: " + openTickets.get(i).get(j).getDeadline());
    //
                                     deadline = date.parse(openTickets.get(i).get(j).getDeadline());
                                     recieveTime = date.parse(openTickets.get(i).get(j).getReceiveTime());
                                     t = j;
    //                                System.out.println("test1 " + (deadline.getTime() - recieveTimeD.getTime()));

                                   // Duration diff = Duration.between(deadline, recieveTime);
                                    //long diffDays = diff.toDays();
                                    //System.out.println("Remaining time for: " + openTickets.get(i).get(j).getUserDetails().getFname()+ " is: " + diffDays);
                                }
                                 if (openTickets.get(i).get(t).getStatus().equals("Open")) {
                                     System.out.println((i + 1) + " Deadline of " + openTickets.get(i).get(t).getUserDetails().getFname() + "s ticket: " + openTickets.get(i).get(t).getDeadline()
                                             + " Remaining time: " + (deadline.getTime() - recieveTime.getTime()) / (1000 * 60 * 60 * 24) + " days");
                                 }
                                 else {
                                     System.out.println("Нет открытых кейсов");
                                 }
                            }

                        }
            }
            else {
                System.out.println("NO DATA!");
            }
        }
        System.out.println("-------------------------------------------SCHEDULER---------------------------------------------\n\n");
    }

    @Autowired
    JmsTemplate jmsTemplate;

    @PostMapping("/sendMessageTopic")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseEntity<String> sendMessageTopic(@RequestBody String msg) throws JMSException { ///?
        jmsTemplate.convertAndSend("OrderTransactionQueue", msg);
        return new ResponseEntity<>("Message have been sended into topic!", HttpStatus.OK);
    }

    @GetMapping("/getAllBooks")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("getBookById/{id}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Book>> getBook(@PathVariable(name = "id") Long id) {
        bookService.findById(id);
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @GetMapping("getBookByName/{name}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ResponseStatus(HttpStatus.OK)
    public String getBookByName(@PathVariable(name = "name") String name) {
        if (bookService.findByName(name) == null) return "There is no such Book!";
        else {
            return bookService.findByName(name).toString();
        }
    }

    @GetMapping("getBookByAuthor/{author}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> getBookByAuthor(@PathVariable(name = "author") String author) {
        bookService.findByAuthor(author);
        return new ResponseEntity<>(bookService.findByAuthor(author), HttpStatus.OK);
    }

    @GetMapping("getBookByGenre/{genre}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable(name = "genre") String genre) {
        bookService.getByGenre(genre);
        return new ResponseEntity<>(bookService.getByGenre(genre), HttpStatus.OK);
    }


}
