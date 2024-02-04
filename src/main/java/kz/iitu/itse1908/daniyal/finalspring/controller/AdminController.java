package kz.iitu.itse1908.daniyal.finalspring.controller;

import kz.iitu.itse1908.daniyal.finalspring.models.*;
import kz.iitu.itse1908.daniyal.finalspring.service.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("AdminController")
@RequestMapping(value = "/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
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



    //---------------Book------------------//
    @GetMapping("/getAllBooks")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }

    @GetMapping("getBookById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Book>> getBook(@PathVariable(name = "id") Long id) {
        bookService.findById(id);
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @GetMapping("getBookByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public String getBookByName(@PathVariable(name = "name") String name) {
        if (bookService.findByName(name) == null) return "There is no such Book!";
        else{
            return bookService.findByName(name).toString();
        }
    }

    @GetMapping("getBookByAuthor/{author}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> getBookByAuthor(@PathVariable(name = "author") String author) {
        bookService.findByAuthor(author);
        return new ResponseEntity<>(bookService.findByAuthor(author), HttpStatus.OK);
    }

    @GetMapping("getBookByGenre/{genre}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable(name = "genre") String genre) {
        bookService.getByGenre(genre);
        return new ResponseEntity<>(bookService.getByGenre(genre), HttpStatus.OK);
    }

    @GetMapping("getBooksOfClient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Book>> getBooksOfClient(@PathVariable(name = "id") Long id) {
        bookService.findBookByTicket_UserDetails_Id(id);
        return new ResponseEntity<>(bookService.findBookByTicket_UserDetails_Id(id), HttpStatus.OK);
    }

    @PostMapping("/addBook")
    @ResponseStatus(HttpStatus.OK)
    public String addBook(@RequestBody Book book){ ///?
        bookService.save(book);
        return "\"" + book.getName() + "\" have been added!";
    }

    @PutMapping("/updateBook/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateBook(@PathVariable(name = "id") Long id, @RequestBody Book book) {
        Optional<Book> oldBook = bookService.findById(id);
        bookService.updateBookById(book.getName(),book.getAuthor(), book.getGenre(), book.getPages(),
                book.getCover(), book.getStock(), id);
        return "\"" + oldBook.get().getName() + "\" changed to \"" + bookService.findById(id).get().getName() + "\"";
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(name = "id") Long id) {
        if (bookService.findById(id).isEmpty()) return "Exception: There is no book with this ID!!!";
        else {
            String name = bookService.findById(id).get().getName();
            bookService.deleteById(id);
            return name + " has been deleted!";
        }
    }

    //----------------------Fine-------------------------//

    @GetMapping("/getAllFines")
    @ResponseStatus(HttpStatus.OK)
    public List<Fine> getAllFines(){
        Hibernate.initialize(fineService.findAll().get(0).getUserDetails());
        return fineService.findAll();
    }

    @GetMapping("getFine/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getFine(@PathVariable(name = "id") Long id) {
        if (fineService.findById(id).isEmpty()) return "There is no Fine with such ID";
        else {
            return fineService.findById(id).toString();
        }
    }

    @GetMapping("getFineByFullname/{fullname}")
    @ResponseStatus(HttpStatus.OK)
    public String getFine(@PathVariable(name = "fullname") String fullname) {
        String[] fl = fullname.split(" ");
        if (userDetailsService.findByLname(fl[0]).isEmpty() || userDetailsService.findByFname(fl[1]).isEmpty()) return "There is no Client with such fullname!";
        System.out.println(fl);
        return fineService.findFineByLnameAndFname(fl[0], fl[1]).toString();
    }

    @GetMapping("getFineOfClient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getFineOfClient(@PathVariable(name = "id") Long id) {
        if (userDetailsService.findById(id).isEmpty()) return "There is no such client!";
        else{
            fineService.findFineByUserDetails_Id(id);
            return fineService.findFineByUserDetails_Id(id).toString();
        }
    }

    @PostMapping("/addFine")
    @ResponseStatus(HttpStatus.OK)
    public String addFine(@RequestBody Fine fine){ ///?
        fineService.save(fine);
        return "Fine have been added!";
    }

    @PutMapping("/updateFine/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateFine(@PathVariable(name = "id") Long id, @RequestBody Fine fine) {
        Optional<Fine> oldFine = fineService.findById(id);
        fineService.updateFineDescriptionById(fine.getDescription(), id);
        return "\"" + oldFine.get().getDescription() + "\" changed to \"" + fineService.findById(id).get().getDescription() + "\"";
    }

    @DeleteMapping("/deleteFine/{id}")
    public String deleteFine(@PathVariable(name = "id") Long id) {
        if (fineService.findById(id).isEmpty()) return "Exception: There is no Fine with this ID!!!";
       // if (userDetailsService.findByFines_Id(id) != null) return "Exception: Cannot delete because it has relation with foreign key of other entity!!!";
        else {
            fineService.deleteById(id);
            return "Fine with id: " + id + " has been deleted!";
        }
    }

    //---------------------Ticket------------------//

    @GetMapping("/getAllTickets")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Ticket>> getAllTickets(){
        return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
    }

    @GetMapping("getTicket/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getTicket(@PathVariable(name = "id") Long id) {
        if (ticketService.findById(id).isEmpty()) return "There is no Ticket with such ID";
        else {
            return ticketService.findById(id).toString();
        }
    }

    @GetMapping("getTicketByFullname/{fullname}")
    @ResponseStatus(HttpStatus.OK)
    public String getTicketByFullname(@PathVariable(name = "fullname") String fullname) {
        String[] fl = fullname.split(" ");
        if (userDetailsService.findByLname(fl[0]).isEmpty() || userDetailsService.findByFname(fl[1]).isEmpty()) return "There is no Client with such fullname!";
        System.out.println(fl);
        return ticketService.findTicketsByFnameAndLname(fl[0], fl[1]).toString();
    }

    @GetMapping("getTicketOfClient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getTicketOfClient(@PathVariable(name = "id") Long id) {
        if (userDetailsService.findById(id).isEmpty()) return "There is no such client!";
        else{
            return ticketService.findByUserDetails_Id(id).toString();
        }
    }

    @GetMapping("getTicketOfClientByStatus/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public String getTicketOfClient(@PathVariable(name = "id") Long id, @PathVariable(name = "status") String status) {
        if (userDetailsService.findById(id).isEmpty()) return "There is no such client!";
        else{
            return ticketService.findByUserDetails_IdAndStatus(id, status).toString();
        }
    }

    @PostMapping("/addTicket")
    @ResponseStatus(HttpStatus.OK)
    public String addTicket(@RequestBody Ticket ticket){ ///?
        ticketService.save(ticket);
        return "Ticket for " + ticket.getUserDetails().getFname() + " have been added!";
    }

    @PutMapping("/updateTicket/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateTicket(@PathVariable(name = "id") Long id, @RequestBody Ticket ticket) {
        Optional<Ticket> oldTicket = ticketService.findById(id);
        ticketService.updateReceiveTimeAndDeadlineById(ticket.getStatus(), ticket.getReceiveTime(), ticket.getDeadline(), id);
        return "Deadline: \"" + oldTicket.get().getDeadline() + "\" changed to \"" + ticketService.findById(id).get().getDeadline() + "\"";
    }

    @DeleteMapping("/deleteTicket/{id}")
    public String deleteTicket(@PathVariable(name = "id") Long id) {
        if (ticketService.findById(id).isEmpty()) return "Exception: There is no Ticket with this ID!!!";
       // if (userDetailsService.findByTickets_Id(id) != null) return "Exception: Cannot delete because it has relation with foreign key of other entity [delete associated book]!!!";
        else {
            ticketService.deleteById(id);
            return "Ticket with id: " + id + " has been deleted!";
        }
    }


    //---------------------UserDetails--------------------//

    @GetMapping("/getAllUserDetails")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDetails>> getAllUserDetails(){
        return new ResponseEntity<>(userDetailsService.findAll(), HttpStatus.OK);
    }

    @GetMapping("getUserDetailsById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getUserDetails(@PathVariable(name = "id") Long id) {
        if (userDetailsService.findById(id).isEmpty()) return "There is no Client with such ID";
        else {
            return userDetailsService.findById(id).toString();
        }
    }

    @GetMapping("getUserDetailsByLname/{lname}")
    @ResponseStatus(HttpStatus.OK)
    public String getUserDetailsLname(@PathVariable(name = "lname") String lname) {
        if (userDetailsService.findByLname(lname).isEmpty()) return "There is no Client with such Last Name";
        else {
            return userDetailsService.findByLname(lname).toString();
        }
    }

    @GetMapping("getUserDetailsByFname/{fname}")
    @ResponseStatus(HttpStatus.OK)
    public String getUserDetailsFname(@PathVariable(name = "fname") String fname) {
        if (userDetailsService.findByFname(fname).isEmpty()) return "There is no Client with such First Name";
        else {
            return userDetailsService.findByFname(fname).toString();
        }
    }

    @PostMapping("/addUserDetails")
    @ResponseStatus(HttpStatus.OK)
    public String addUserDetails(@RequestBody UserDetails userDetails){ ///?
        userDetailsService.save(userDetails);
        return userDetails.getLname() + " " + userDetails.getFname() + " have been added!";
    }

    @PutMapping("/updateUserDetails/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUserDetails(@PathVariable(name = "id") Long id, @RequestBody UserDetails userDetails) {
        if (userDetailsService.findById(id).isEmpty()) return "There is no Client with such ID";
        else {
            Optional<UserDetails> oldUserDetails = userDetailsService.findById(id);
            userDetailsService.updateFnameAndLnameById(userDetails.getFname(), userDetails.getLname(), id);
            return "Client: \"" + oldUserDetails.get().getFname() + " " + oldUserDetails.get().getLname()
                    + "\" changed to \"" + userDetailsService.findById(id).get().getFname() + " " +
                    userDetailsService.findById(id).get().getLname() + "\"";
        }
    }

    @DeleteMapping("/deleteUserDetails/{id}")
    public String deleteUserDetails(@PathVariable(name = "id") Long id) {
        if (userDetailsService.findById(id).isEmpty()) return "Exception: There is no Client with this ID!!!";
        //if (ticketService.getByUserDetails_Id(id) != null) return "Exception: Cannot delete because it has relation with foreign key of other entity [delete associated ticket]!!!";
       // if (fineService.findFineByUserDetails_Id(id) != null) return "Exception: Cannot delete because it has relation with foreign key of other entity [delete associated fine]!!!";
        else {
            userDetailsService.deleteById(id);
            return "Client with id: " + id + " WITH ALL HIS ASSOCIATIONS has been deleted!";
        }
    }


    //---------------------Role--------------------//

    @GetMapping("/getAllRoles")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }


    //---------------------User--------------------//

    @GetMapping("/getAllUsers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("getUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getUser(@PathVariable(name = "id") Long id) {
        if (userService.findById(id).isEmpty()) return "There is no User with such ID";
        else {
            return userService.findById(id).toString();
        }
    }

    @GetMapping("getUsersByRole/{roleName}")
    @ResponseStatus(HttpStatus.OK)
    public String getUsersByRole(@PathVariable(name = "roleName") ERole role) {
        if (roleService.findByName(role) == null) return "There is no Role with such name";
        else {
            return userService.findByRoles_Name(role).toString();
        }
    }

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.OK)
    public String addUser(@RequestBody User user){ ///?
        userService.save(user);
        return "User have been added!";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        if (userService.findById(id).isEmpty()) return "Exception: There is no User with this ID!!!";
        else {
            userService.deleteById(id);
            return "User with id: " + id + " has been deleted!";
        }
    }


}
