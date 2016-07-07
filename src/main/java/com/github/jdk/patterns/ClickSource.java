package com.github.jdk.patterns;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zk_chs on 16/7/7.
 */
public class ClickSource implements EventSource {

    protected List<EventListener<? extends Event>> listeners = new LinkedList<>();

    @Override
    public void addEventListener(EventListener<? extends Event> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeEventListener(EventListener<? extends Event> listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListeners(Event event) {
        for (EventListener listener : listeners){
            listener.handleEvent(event);
        }
    }

}
