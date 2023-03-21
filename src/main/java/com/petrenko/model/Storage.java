package com.petrenko.model;

import java.util.HashSet;
import java.util.Set;
// todo DELETE
public class Storage { // todo Do I need a storage if I have SQL-table?
    Set<Detail> details = new HashSet<>();
    int countDetails; // todo Do I need to calculate a count of details if I can use method size()?
}
