package com.github.jdk.patterns.source;

import com.github.jdk.patterns.event.Event;
import com.github.jdk.patterns.listener.EventListener;

/**
 * Created by zk_chs on 16/7/7.
 */
public interface EventSource {

    void addEventListener(EventListener<? extends Event> listener);

    void removeEventListener(EventListener<? extends Event> listener);

    void notifyListeners(Event event);

}
