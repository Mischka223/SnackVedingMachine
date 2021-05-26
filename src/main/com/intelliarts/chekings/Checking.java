package com.intelliarts.chekings;


import com.intelliarts.services.SnackService;
import com.intelliarts.services.SnackServiceImpl;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checking {
    private final SnackService service = new SnackServiceImpl();


    public void methodCheck(String consoleLine) {
        String[] split = consoleLine.split(" ");
        String methodName = split[0];
        secondChecking(methodName, split);
    }

    public void secondChecking(String s, String[] array) {
        switch (s) {
            case "addCategory":
                addCategory(array);
                break;
            case "addItem":
                addItem(array);
                break;
            case "list":
                getAllSnacks();
                break;
            case "purchase":
                purchase(array);
                break;
            case "report":
                report(array);
            case "clear":
                clearCheck();
                break;
        }
    }

    public String getSnackName(String[] array) {
        String snackName = null;
        StringBuilder a = new StringBuilder();
        String regex = "[“\"]([^\"]*)[”\"]";
        for (int i = 1; i < array.length; i++) {
            a.append(array[i]).append(" ");
        }
        String s = String.valueOf(a).trim();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            snackName = matcher.group(1);
        }
        return snackName;
    }

    private int getSnackNumber(String[] array) {
        int length = array.length;
        return Integer.parseInt(array[length - 1]);
    }

    private double getSnackPrice(String[] array) {
        int length = array.length;
        return Double.parseDouble(array[length - 2]);
    }

    public void addCategory(String[] array) {
        String name = getSnackName(array);
        double price = getSnackPrice(array);
        int number = getSnackNumber(array);
        service.addCategory(name, price, number);
    }

    public void addItem(String[] array) {
        String snackName = getSnackName(array);
        int snackNumber = getSnackNumber(array);
        service.updateSnack(snackName, snackNumber);
    }

    public void purchase(String[] array) {
        LocalDate localDate = LocalDate.parse(array[array.length - 1]);
        String snackName = getSnackName(array);
        service.purchase(snackName, localDate);
    }

    public void clearCheck() {
        service.deleteAllEmpty();
    }

    public void getAllSnacks() {
        service.getAll();
    }

    public void report(String[] array) {
        String s = array[array.length - 1];
        String[] split = s.split("-");
        if (split.length == 3) {
            service.reportForDay(s);
        }
        if (split.length == 2) {
            try {
                service.reportForMonth(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
