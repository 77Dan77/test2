##Zhexenov Daniyal ITSE-1908R. Final project

## Library Management System


Digital Library Management Project. It may be useful for other organizations that have libraries, which will facilitate and automate much of the work, and reduce the burden on employees.
The main idea is to provide functionality for quickly searching for data about the user, books, etc., for automatically generating coupons, counting the deadline, for users who received books and much more, which should significantly speed up the speed and quality of service, reduce the number of errors, and reduce the workload of workers.

####What does this system allow you to do?

Here is a list of the main functions for the *USER*:
- The *user* can independently search for books in the database, see the number of *books* left in stock, search for specific books by title, author, genre, etc.
- The *user* can independently receive books by setting the deadline time
- Before the *user* receives the books, the system automatically checks if he has *fines*, and then notifies him of the result of the check (if fines are found, a list of them is displayed)
- The system automatically tracks the number of *books* in stock, if the *user* tries to get books that are not available, the system will notify him of this.
- After the user receives the book, a coupon is automatically created, which indicates the time of receipt, the deadline, the user who took the books, as well as the names of the books received.
- After creating a coupon, for each individual, the time to the deadline in days starts counting from the current time.
- The user can return the book to the library, after which, the status of his coupon changes to "Closed".
- A mini chatbot that can be asked to do a few things, like asking for a list of all the books.

List of main functions for ADMIN:
- Completely receive lists of all *users*, *books*, *coupons*, *fines*, etc.
- Create *books*, *users*, *coupons*.
- Update *books*, *user* *data*, *coupons*, *fines*.
- Delete *books*, *users*, *coupons*, *fines*.
- Do a deep search for everything, according to certain parameters (search only for "open" *coupons*, search for *users* who received a specific *book*)

###Classes:
* Models:
  - [Book](src/main/java/kz/iitu/itse1908/daniyal/finalspring/models/Book.java) - book model with all its characteristics and connections
  - [Fine](src/main/java/kz/iitu/itse1908/daniyal/finalspring/models/Fine.java) - fine model with all its characteristics and connections
  - [Ticket](src/main/java/kz/iitu/itse1908/daniyal/finalspring/models/Ticket.java) - ticket model with all its characteristics and connections
  - [User](src/main/java/kz/iitu/itse1908/daniyal/finalspring/models/User.java) - user model with all its characteristics and connections
  - [UserDetails](src/main/java/kz/iitu/itse1908/daniyal/finalspring/models/UserDetails.java) - userDetails model with all its characteristics and connections
  - [Role](src/main/java/kz/iitu/itse1908/daniyal/finalspring/models/Role.java) - role model with all its characteristics and connections
  - [BuyRequest](src/main/java/kz/iitu/itse1908/daniyal/finalspring/models/BuyRequest.java) - buyRequest model with all its characteristics, used for buying book
  - [ReturnRequest](src/main/java/kz/iitu/itse1908/daniyal/finalspring/models/ReturnRequest.java) - returnRequest model with all its characteristics, used for returnin book


* Controllers:
  - [AdminController](src/main/java/kz/iitu/itse1908/daniyal/finalspring/controller/AdminController.java) - сontains all endpoints for the admin, as well as clarifications for security
  - [ClientController](src/main/java/kz/iitu/itse1908/daniyal/finalspring/controller/ClientController.java) - сontains all endpoints for the client, as well as clarifications for security


* Repository:
  - [BookRepository](src/main/java/kz/iitu/itse1908/daniyal/finalspring/repository/BookRepository.java) - jpa repository for creating book database queries
  - [FineRepository](src/main/java/kz/iitu/itse1908/daniyal/finalspring/repository/FineRepository.java) - jpa repository for creating fine database queries
  - [RoleRepository](src/main/java/kz/iitu/itse1908/daniyal/finalspring/repository/RoleRepository.java) - jpa repository for creating role database queries
  - [TicketRepository](src/main/java/kz/iitu/itse1908/daniyal/finalspring/repository/TicketRepository.java) - jpa repository for creating ticket database queries
  - [UserRepository](src/main/java/kz/iitu/itse1908/daniyal/finalspring/repository/UserRepository.java) - jpa repository for creating user queries
  - [UserDetailsRepository](src/main/java/kz/iitu/itse1908/daniyal/finalspring/repository/UserDetailsRepository.java) - jpa repository for creating userDetails database queries


* Service:
  - [BookService](src/main/java/kz/iitu/itse1908/daniyal/finalspring/service/BookService.java) - utility class implementing jpa book repository
  - [FineService](src/main/java/kz/iitu/itse1908/daniyal/finalspring/service/FineService.java) - utility class implementing jpa fine repository
  - [RoleService](src/main/java/kz/iitu/itse1908/daniyal/finalspring/service/RoleService.java) - utility class implementing jpa role repository
  - [TicketService](src/main/java/kz/iitu/itse1908/daniyal/finalspring/service/TicketService.java) - utility class implementing jpa ticket repository
  - [UserService](src/main/java/kz/iitu/itse1908/daniyal/finalspring/service/UserService.java) - utility class implementing jpa user repository
  - [UserDetailsService](src/main/java/kz/iitu/itse1908/daniyal/finalspring/service/UserDetailsService.java) - utility class implementing jpa userDetails repository


* Config:
  - [Configuration](src/main/java/kz/iitu/itse1908/daniyal/finalspring/config/Configuration.java) - configuration class that stores the basic settings of the beans, hibernet, and data sources


* Security:
  - [AuthenticationEntryPointImpl](src/main/java/kz/iitu/itse1908/daniyal/finalspring/security/AuthenticationEntryPointImpl.java) - user privacy check class
  - [MethodSecurityConfig](src/main/java/kz/iitu/itse1908/daniyal/finalspring/security/MethodSecurityConfig.java) - class providing access to some security antonations
  - [WebSecurityConfig](src/main/java/kz/iitu/itse1908/daniyal/finalspring/security/WebSecurityConfig.java) - class of project security settings, password encryption


* JMS:
  - [JmsConfig](src/main/java/kz/iitu/itse1908/daniyal/finalspring/JMS/JmsConfig.java) - jms configuration and settings class
  - [MessageReciever](src/main/java/kz/iitu/itse1908/daniyal/finalspring/JMS/MessageReciever.java) - jms message applicator class, working as a mini chat bot


* AOP:
  - [FineCheck](src/main/java/kz/iitu/itse1908/daniyal/finalspring/AOP/FineCheck.java) - aspect class for implicitly checking user penalties


* Exception Handlers:
  - [BlogapiException](src/main/java/kz/iitu/itse1908/daniyal/finalspring/ExceptionHandlers/BlogapiException.java) - exception catcher, used to create exceptions


* Main package:
  - [FinalSpringApplication](src/main/java/kz/iitu/itse1908/daniyal/finalspring/FinalSpringApplication.java) - main class
  - [MainRunner](src/main/java/kz/iitu/itse1908/daniyal/finalspring/MainRunner.java) - class that executes the main logic
  - [PrepareData](src/main/java/kz/iitu/itse1908/daniyal/finalspring/PrepareData.java) - class, for preparing and creating data

* Used annotations:
  * *@Bean*, *@Component*, *@Repository*, *@Service*, *@Configuration* - bean initialization annotations
  * *@EntityScan*
  * *@EnableAspectJAutoProxy*
  * *@EnableScheduling*
  * *@EnableJms* - enables to browse all JmsListeners and use it to resieve a message
  * *@EnableScheduling* - allows to use scheduled tasks
  * *@Entity* - associates an entity in a database with a java class
  * *@Table* - defining a class as a table with a name
  * *@Column* - defining an attribute as a field of an entity with a name
  * *@ManyToOne* - defining a Two-Way Table Relationship
  * *@OneToMany* - defining a Two-Way Table Relationship
  * *@ManyToMany*
  * *@OneToOne*
  * *@JoinTable*
  * *@OneToOne*
  * *@Cascade*
  * *@JmsListener* - defines the method as listener that recieves the message according destination
  * *@RestController* - marks the class as a controller to be able to send requests
  * *@RequestMapping* - specifies url address
  * *@PreAuthorize*
  * *@ResponseStatus* - returns a specific message after receiving a response
  * *@PostMapping* - marks post method request
  * *@GetMapping* - marks get method request
  * *@PutMapping* -  marks put method request
  * *@DeleteMapping* -marks delete method request 
  * *@GetMapping*
  * *@EnableTransactionManagement* - ability to manage transactions
  * *@EnableWebSecurity*
  * *@Aspect*
  * *@Pointcut*
  * *@Before*
  * *@Scheduled* - run tasks according attributes
  * *@Transactional* - defines the scope of a single database transaction
  * *@Query* - for creating own queries
  * *@DependsOn*
  * *@Validated*
  * *@Audited* - to be able to check entity reporting
  * *@Cacheable* - ability to using second level cache


#cURL
##Admin:
  - ###Get all:
    - GET GetAllBooks
      http://localhost:8080/api/admin/getAllBooks
        - `curl --location --request GET 'http://localhost:8080/api/admin/getAllBooks' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
    - GET
      GetAllFines
      http://localhost:8080/api/admin/getAllFines
      - `curl --location --request GET 'http://localhost:8080/api/admin/getAllFines' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
    - GET
      GetAllTickets
      http://localhost:8080/api/admin/getAllTickets
      - `curl --location --request GET 'http://localhost:8080/api/admin/getAllTickets' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
    - GET
      GetAllUserDetails
      http://localhost:8080/api/admin/getAllUserDetails
      - `curl --location --request GET 'http://localhost:8080/api/admin/getAllUserDetails' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
    - GET
      GetAllUsers
      http://localhost:8080/api/admin/getAllUsers
      - `curl --location --request GET 'http://localhost:8080/api/admin/getAllUsers' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
    - GET
      GetAllRoles
      http://localhost:8080/api/admin/getAllRoles
      - `curl --location --request GET 'http://localhost:8080/api/admin/getAllRoles' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
  

  - ###Delete:
    - DEL
      Delete Book
      http://localhost:8080/api/admin/deleteBook/1
      - `curl --location --request DELETE 'http://localhost:8080/api/admin/deleteBook/1' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM=' \`
    - DEL
      Delete Ticket
      http://localhost:8080/api/admin/deleteTicket/4
      - `curl --location --request DELETE 'http://localhost:8080/api/admin/deleteTicket/4' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM=' \`
    - DEL
      Delete UserDetails
      http://localhost:8080/api/admin/deleteUserDetails/2
      - `curl --location --request DELETE 'http://localhost:8080/api/admin/deleteUserDetails/2' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
    - DEL
      Delete User
      http://localhost:8080/api/admin/deleteUser/1
      - `curl --location --request DELETE 'http://localhost:8080/api/admin/deleteUser/1' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`


  - ###Update
    - PUT
      Update book
      http://localhost:8080/api/admin/updateBook/4
      - `curl --location --request PUT 'http://localhost:8080/api/admin/updateBook/4' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM=' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "name": "d",
        "author": "C.S.Ledsfdswis",
        "genre": "Fandsfdftasy",
        "pages": 58,
        "cover": "Hardsdfcover",
        "stock": 10
        }'`
    - PUT
      Update Fine
      http://localhost:8080/api/admin/updateFine/1
      - `curl --location --request PUT 'http://localhost:8080/api/admin/updateFine/1' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM=' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "description": "DDDDDFGDFGDG"
        }'`
    - PUT
      Update Ticket
      http://localhost:8080/api/admin/updateTicket/1
      - `curl --location --request PUT 'http://localhost:8080/api/admin/updateTicket/1' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM=' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "receiveTime": "2022342342.05.16",
        "deadline": "2022234234.05.23"
        }'`
    - PUT
      Update UserDetails
      http://localhost:8080/api/admin/updateUserDetails/1
      - `curl --location --request PUT 'http://localhost:8080/api/admin/updateUserDetails/1' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM=' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "fname": "Daniwerweyal",
        "lname":"Zheewrwerxenov"
        }'`


  - ###Create
    - POST
      Add Book
      http://localhost:8080/api/admin/addBook
      - `curl --location --request POST 'http://localhost:8080/api/admin/addBook' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM=' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "name": "d",
        "author": "J.K.Roasdasdwling",
        "genre": "Fantasy",
        "pages": 643,
        "cover": "Hardcover",
        "stock": 1
        }'`
    - POST
      Add Fine
      http://localhost:8080/api/admin/addFine
      - `curl --location --request POST 'http://localhost:8080/api/admin/addFine' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM=' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "description": "Bad guy"
        }'`
    - POST
      Add UserDetails
      http://localhost:8080/api/admin/addUserDetails
      - `curl --location --request POST 'http://localhost:8080/api/admin/addUserDetails' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM=' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "fname": "Daniwerweyal",
        "lname":"Zheewrwerxenov"
        }'`
    - POST
      Add Ticket.
      http://localhost:8080/api/admin/addTicket
      - `curl --location --request POST 'http://localhost:8080/api/admin/addTicket' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM=' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "receiveTime": "2022342342.05.16",
        "deadline": "2022234234.05.23"
        }'`
    - POST
      Add User.
      http://localhost:8080/api/admin/addUser
      - `curl --location --request POST 'http://localhost:8080/api/admin/addUser' \
        --header 'Authorization: Basic ZGFuaXlhbDoxMjM=' \
        --data-raw ''`


  - ###Find
    - ###Book:
      - GET
        Get Book By ID
        http://localhost:8080/api/admin/getBookById/3
        - `curl --location --request GET 'http://localhost:8080/api/admin/getBookById/3' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
      - GET
        Get Book By Name
        http://localhost:8080/api/admin/getBookByName/Harry Potter
        - `curl --location --request GET 'http://localhost:8080/api/admin/getBookByName/Harry Potter' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
      - GET
        Get Book By Author
        http://localhost:8080/api/admin/getBookByAuthor/C.S.Lewis
        - `curl --location --request GET 'http://localhost:8080/api/admin/getBookByAuthor/C.S.Lewis' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
      - GET
        Get Book By Genre
        http://localhost:8080/api/admin/getBookByGenre/Fantasy
        - `curl --location --request GET 'http://localhost:8080/api/admin/getBookByGenre/Fantasy' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
      - GET
        Get Book of particular client by ID
        http://localhost:8080/api/admin/getBooksOfClient/1
        - `curl --location --request GET 'http://localhost:8080/api/admin/getBooksOfClient/1' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
        
    - ###Ticket
      - GET
        Get Ticket by ID
        http://localhost:8080/api/admin/getTicket/4
        - `curl --location --request GET 'http://localhost:8080/api/admin/getTicket/4' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
      - GET
        Get Ticket of particular client by Fullanme
        http://localhost:8080/api/admin/getTicketByFullname/Zhexenov Daniyal
        - `curl --location --request GET 'http://localhost:8080/api/admin/getTicketByFullname/Zhexenov Daniyal' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
      - GET
        Get Ticket of client by ID
        http://localhost:8080/api/admin/getTicketOfClient/1
        - `curl --location --request GET 'http://localhost:8080/api/admin/getTicketOfClient/1' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
      - GET
        Get Ticket of client by status
        http://localhost:8080/api/admin/getTicketOfClientByStatus/1/Open
        - `curl --location --request GET 'http://localhost:8080/api/admin/getTicketOfClientByStatus/1/Open' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
        
    - ###Fine
      - GET
        Get Fine by ID
        http://localhost:8080/api/admin/getFine/3
        - `curl --location --request GET 'http://localhost:8080/api/admin/getFine/3' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
      - GET
        Get Fines of particular client by Fullname
        http://localhost:8080/api/admin/getFineByFullname/Zhexenov Daniyal
        - `curl --location --request GET 'http://localhost:8080/api/admin/getFineByFullname/Zhexenov Daniyal' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
        
    - ###UserDetails
      - GET
        Get UserDetails By ID
        http://localhost:8080/api/admin/getUserDetailsById/1
        - `curl --location --request GET 'http://localhost:8080/api/admin/getUserDetailsById/1' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
      - GET
        Get UserDetails By Last name
        http://localhost:8080/api/admin/getUserDetailsByLname/Zhexenov
        - `curl --location --request GET 'http://localhost:8080/api/admin/getUserDetailsByLname/Zhexenov' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
      - GET
        Get UserDetails by First name
        http://localhost:8080/api/admin/getUserDetailsByFname/Daniyal
        - `curl --location --request GET 'http://localhost:8080/api/admin/getUserDetailsByFname/Daniyal' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
        
    - ###User
      - GET
        Get User by ID
        http://localhost:8080/api/admin/getUser/1
        - `curl --location --request GET 'http://localhost:8080/api/admin/getUser/1' \
          --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`

##Client
  - POST
    Buy Book
    http://localhost:8080/api/client/buyBook
    - `curl --location --request POST 'http://localhost:8080/api/client/buyBook' \
      --header 'Authorization: Basic Y2xpZW50OjEyMw==' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "clientFullname": "Kalzhigitov Nurbol",
      "deadline": "2022.06.17 03:02:06",
      "bookNames": [
      "d",
      "Harry Potter",
      "The Chronicles of Narnia"
      ]
      }'`
  - POST
    Return Book
    http://localhost:8080/api/client/returnBook
    - `curl --location --request POST 'http://localhost:8080/api/client/returnBook' \
      --header 'Authorization: Basic Y2xpZW50OjEyMw==' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "clientFullname": "Zhexenov Daniyal",
      "ticketId": 4
      }'`
  - POST
    sendMessageTopic
    http://localhost:8080/api/client/sendMessageTopic
    - `curl --location --request POST 'http://localhost:8080/api/client/sendMessageTopic' \
      --header 'Authorization: Basic Y2xpZW50OjEyMw==' \
      --header 'Content-Type: text/plain' \
      --data-raw 'Hello'`
  - GET
    Get List of books
    http://localhost:8080/api/client/getAllBooks
    - `curl --location --request GET 'http://localhost:8080/api/client/getAllBooks' \
      --header 'Authorization: Basic ZGFuaXlhbDoxMjM='`
      