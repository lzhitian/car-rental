package com.car.rental.service;

import com.car.rental.model.dto.BorrowedDateDto;
import com.car.rental.model.dto.CustomerDto;
import com.car.rental.repository.BorrowedDateDAO;
import com.car.rental.service.impl.BorrowedDateServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

// TODO: Administrator 2020/3/1 16:22 要调整为集成测试，目前都只是单一测试
public class BorrowedDateServiceImplTest {

    BorrowedDateDto borrowedDate;

    CustomerDto customer;

    BorrowedDateDAO borrowedDateDAO;

    BorrowedDateService borrowedDateService;

    BorrowedDateServiceImpl borrowedDateServiceImpl;

    @Test
    public void countDays() throws Exception {

        customer = new CustomerDto();
        customer.setFullName("John Smith");
        customer.setTotalPrice(new BigDecimal(100));

        Calendar start = Calendar.getInstance();
        start.set(2017, 01, 01, 00, 00, 00);
        Calendar end = Calendar.getInstance();
        end.set(2017, 01, 10, 00, 00, 00);
        borrowedDate = new BorrowedDateDto();
        borrowedDate.setStartDate(start);
        borrowedDate.setEndDate(end);

        // long days1 = borrowedDateService.countDays(borrowedDate);
        // assertEquals(10, days1, 0);

        long days = daysBetween(start, end);
        customer.setTotalPrice(customer.getTotalPrice().multiply(new BigDecimal(days)));
        BigDecimal totalSum = customer.getTotalPrice();

        assertEquals(10, days, 0);
        assertEquals(new BigDecimal(1000), totalSum);
    }

    private long daysBetween(Calendar startDate, Calendar endDate) {
        endDate.add(Calendar.DATE, 1);
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }
}