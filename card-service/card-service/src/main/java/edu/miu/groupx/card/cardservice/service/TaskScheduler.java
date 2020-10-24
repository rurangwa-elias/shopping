package edu.miu.groupx.card.cardservice.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskScheduler {

    @Scheduled(cron = "0 55 23 30 4,6,9,11 *")
    public void creditCardStatusUpdaterForThirtyDayMonths() {
        /**
         *  1 - get all the cards for each financial institution
         *  2 - check the expiration date and update then update the card status if  so
         */
    }

    @Scheduled(cron = "0 55 23 31 1,3,5,7,8,10,12 *")
    public void creditCardStatusUpdaterForThirtyOneDayMonths() {

    }

    @Scheduled(cron = "0 55 23 28 2 *")
    public void creditCardStatusUpdaterForTwentyEightDayMonths() {

    }

    private void handler() {

    }
}
