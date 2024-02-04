package kz.iitu.itse1908.daniyal.finalspring;

import kz.iitu.itse1908.daniyal.finalspring.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.convert.ConversionService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
@EnableAspectJAutoProxy
@Slf4j
public class MainRunner implements CommandLineRunner {

    PrepareData prepareData;
    ConversionService conversionService;
    BookService bookService;
    FineService fineService;
    RoleService roleService;
    TicketService ticketService;
    UserDetailsService userDetailsService;
    UserService userService;

    @Autowired
    public void setPrepareData(PrepareData prepareData) {
        this.prepareData = prepareData;
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

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    @Autowired
    public MainRunner(PrepareData prepareData) {this.prepareData = prepareData;}

    @Override
    public void run(String... args) throws Exception {

        log.info("Creating tables...");
        prepareData.createTables();

        log.info("Inserting data...");
        userDetailsService.saveAll(prepareData.userDetailsList());
        roleService.saveAll(prepareData.roleList());
        ticketService.saveAll(prepareData.ticketList());
        bookService.saveAll(prepareData.BookList());
        fineService.saveAll(prepareData.fineList());
//        userService.saveAll(prepareData.userList());

//        System.out.println("------------------FIND ALL-------ВСЕ РАБОТАЮТ----------------");
        System.out.println(bookService.findAll());
//        System.out.println(userDetailsService.findAll());
//        System.out.println(roleService.findAll());
//        System.out.println(ticketService.findAll());
//        System.out.println(fineService.findAll());

//        System.out.println("-----------ВСЕ РАБОТАЮТ------------------");
//        System.out.println(bookService.findByAuthor("Tolkien"));
//        System.out.println(bookService.getByGenre("Fantasy"));
//        System.out.println(bookService.findById(Long.valueOf(1)));
//        System.out.println(bookService.findByName("Harry Potter"));
//        System.out.println(bookService.findBookByTicket_UserDetails_Id(Long.valueOf(2)));
//        bookService.deleteById(Long.valueOf(3));
//        bookService.updateBookById("updated", "updated",
//                  "updated", 523, "updated", Long.valueOf(10), Long.valueOf(1));

//        System.out.println("-----------ВСЕ РАБОТАЮТ------------------");
//          fineService.updateFineDescriptionById("updated", Long.valueOf(2));
//          System.out.println(fineService.findFineByLnameAndFname("Zhexenov", "Daniyal"));
//          System.out.println(fineService.findFineByUserDetails_Id(Long.valueOf(1)));
//          System.out.println(fineService.findById(Long.valueOf(2)));

//          fineService.deleteById(Long.valueOf(1));  //На него ссылается талон из другой табл, поэтому нельзя удвлить, нужно в контроллере создать сборщик исключений

 //           System.out.println("-----------ВСЕ РАБОТАЮТ------------------");
//            System.out.println(ticketService.findByUserDetails_Id(Long.valueOf(1)));
//            System.out.println(ticketService.findTicketsByFnameAndLname("Daniyal", "Zhexenov"));
//            ticketService.updateReceiveTimeAndDeadlineById(20.0, 30.0, Long.valueOf(1));
//            System.out.println(ticketService.findById(Long.valueOf(1)));

//              ticketService.deleteById(Long.valueOf(2)); //Ex: deleted object would be re-saved by cascade (remove deleted object from associations):

//      System.out.println("-----------ВСЕ РАБОТАЮТ------------------");
//        System.out.println(userDetailsService.findByFname("Daniyal"));
//        System.out.println(userDetailsService.findByLname("Zhexenov"));
//        userDetailsService.updateFnameAndLnameById("Gena", "Bukin", Long.valueOf(1));
//        System.out.println(userDetailsService.findById(Long.valueOf(1)));

//        userDetailsService.deleteById(Long.valueOf(1));  //На него ссылается талон из другой табл, поэтому нельзя удвлить, нужно в контроллере создать сборщик исключений

//        int size = CacheManager.ALL_CACHE_MANAGERS.size();
//        System.out.println("-------------------------------" + size + "-----------------------------------");


//        AuditReader reader = AuditReaderFactory.get(sessionFactory.openSession());
//        AuditQuery queryCustomer = reader.createQuery()
//                .forRevisionsOfEntity(Customer.class, true, true);
//        System.out.println(queryCustomer.toString());
    }
}
