package de.webcode.bordergames.event;

import de.webcode.bordergames.event.impl.Second1Event;
import de.webcode.bordergames.event.impl.Second5Event;

public class Listener implements org.bukkit.event.Listener {
    @EventTarget
    public void onSecond1(Second1Event event) {
        System.out.println("1 Second event works!");
    }

    @EventTarget
    public void onSecond5(Second5Event event) {
        System.out.println("Second 5 Event works!");
    }
}
