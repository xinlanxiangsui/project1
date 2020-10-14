package com.stx.project1.model;

import java.util.ArrayList;

public interface Person {
    boolean login(ArrayList arr);

    boolean register(Person p, ArrayList arr);

    boolean clock(ArrayList arr);

    boolean remove(ArrayList arr);

    boolean changePwd(ArrayList arr);

    boolean showMenu(ArrayList stuArr);

    boolean showMenu(ArrayList stuArr, ArrayList teaArr);

    boolean showMenu(ArrayList stuArr, ArrayList teaArr, ArrayList mngArr);
}
