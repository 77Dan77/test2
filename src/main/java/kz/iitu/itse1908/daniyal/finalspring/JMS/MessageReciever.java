package kz.iitu.itse1908.daniyal.finalspring.JMS;

import kz.iitu.itse1908.daniyal.finalspring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReciever {

    BookService bookService;
    @Autowired
    public MessageReciever(BookService bookService) {
        this.bookService = bookService;
    }

    @JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
    public void receiveMessage(String transaction) {

        if (transaction.contains("book list") || transaction.contains("list of books")){
            System.out.println("Of course!\n Here is the list of books:\n" + bookService.findAll());
        }
        else
        {
            switch (transaction.toLowerCase()){
                case "hello":
                    System.out.println("Hello!");
                    break;

                case "how are you?":
                    System.out.println("Fine, thanks!");
                    break;

                case "thanks":
                    System.out.println("Thank you to!");
                    break;

                case "thank you":
                    System.out.println("Thank you to!");
                    break;

                default:
                    System.out.println("Cool! But sorry, im not so clever to recognize this request now, but am learning;)");
                    break;

            }
        }
    }

}
