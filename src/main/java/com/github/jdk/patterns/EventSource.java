package com.github.jdk.patterns;

/**
 * Created by zk_chs on 16/7/7.
 */
public interface EventSource {

    void addEventListener(EventListener<? extends Event> listener);

    void removeEventListener(EventListener<? extends Event> listener);

    void notifyListeners(Event event);

}
