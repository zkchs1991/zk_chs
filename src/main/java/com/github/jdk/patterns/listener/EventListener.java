package com.github.jdk.patterns.listener;

import com.github.jdk.patterns.event.Event;

/**
 * Created by zk_chs on 16/7/7
 */
public interface EventListener<T extends Event> {

    void handleEvent(T event);

}
