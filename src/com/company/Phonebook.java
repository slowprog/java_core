package com.company;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Phonebook {
    private LinkedHashMap<String, HashSet> list = new LinkedHashMap<>();

    public void add(String suname, String phone) {
        HashSet<String> phones = list.get(suname);

        if (phones == null) {
            phones = new HashSet<>();
        }

        phones.add(phone);

        list.put(suname, phones);
    }

    public String get(String suname) {
        Iterator iter = list.get(suname).iterator();
        String result = "";

        while (iter.hasNext()) {
            result += iter.next();

            if (iter.hasNext()) {
                result += ", ";
            }
        }

        return result;
    }
}
