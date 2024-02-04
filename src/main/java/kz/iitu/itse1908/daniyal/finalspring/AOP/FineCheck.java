package kz.iitu.itse1908.daniyal.finalspring.AOP;

import kz.iitu.itse1908.daniyal.finalspring.models.BuyRequest;
import kz.iitu.itse1908.daniyal.finalspring.models.Fine;
import kz.iitu.itse1908.daniyal.finalspring.service.FineService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Aspect
@Configuration
public class FineCheck {

    Logger log = LoggerFactory.getLogger(this.getClass());
    FineService fineService;
    @Autowired
    public void setFineService(FineService fineService) {
        this.fineService = fineService;
    }

    @Pointcut("execution(* kz.iitu.itse1908.daniyal.finalspring.controller.ClientController.buyBook(..))")
    public void checkUserFines(){
    }

    @Pointcut("execution(* kz.iitu.itse1908.daniyal.finalspring.controller.ClientController.returnBook(..))")
    public void checkUserDeadlines(){
    }


    @Before("checkUserDeadlines()")
    public void DeadlineAdvice(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String instancee = joinPoint.getArgs()[0].toString();
        String[] arr = instancee.split("'");
        log.warn("-BEFORE- Метод: " + methodName + " - DEadline " + instancee + " не обнаружено!\n");
    }

    @Before("checkUserFines()")
    public void FineAdvice(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String instancee = joinPoint.getArgs()[0].toString();
        String[] arr = instancee.split("'");
        String[] fullname = arr[1].split(" ");
        List<Fine> fineList = fineService.findFineByLnameAndFname(fullname[0], fullname[1]);
        if (fineList.isEmpty()){
            System.out.println("\n\n");
            log.warn("-BEFORE- Метод: " + methodName + " - Штрафов у пользователя " + arr[1] + " не обнаружено!\n");
        }
        else {
            System.out.println("\n\n");
            log.warn("-BEFORE- Метод: " + methodName + " - ВНИМАНИЕ! У пользвателя " + arr[1] + " обнаружены штрафы!\n");
            System.out.println("Список штрафов: ");
            for (Fine fine: fineList){
                System.out.println(" - " + fine.getDescription());
            }
        }
    }

}


