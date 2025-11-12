package com.pluralsight.repository;

import com.pluralsight.model.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReceiptWriter {


    public void saveReceipt(Order order) {
        String date = String.format("%d-%d-%d",
                LocalDate.now().getYear(),
                LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth());

        String time = String.format("%d.%d.%d",
                LocalTime.now().getHour(),
                LocalTime.now().getMinute(),
                LocalTime.now().getSecond());

        String fileName = date + " - " + time + ".txt";

        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(date + "  -  " + time + "\n" + order.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
