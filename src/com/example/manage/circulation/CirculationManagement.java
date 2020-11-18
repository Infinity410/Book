package com.example.manage.circulation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CirculationManagement {
    List<Circulation> circulations;
    private static final File file = new File("Circulation.txt");
    CirculationManagement(){
        circulations = new ArrayList<>();
    }
    public void lendBook(){

    }
    public void returnBook(){

    }
    public void query(){

    }
}
