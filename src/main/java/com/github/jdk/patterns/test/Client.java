package com.github.jdk.patterns.test;

import com.github.jdk.patterns.event.ClickEvent;
import com.github.jdk.patterns.event.DblClickEvent;
import com.github.jdk.patterns.event.Event;
import com.github.jdk.patterns.listener.ClickEventListener;
import com.github.jdk.patterns.listener.DblClickEventListener;
import com.github.jdk.patterns.source.ClickSource;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zk_chs on 16/7/7.
 */
public class Client {

    private Event currentEvent;

    private ClickSource clickSource;

    @Before
    public void init (){
        clickSource = new ClickSource();
        clickSource.addEventListener((ClickEventListener) event -> System.out.println("click!"));
        clickSource.addEventListener((DblClickEventListener) event -> System.out.println("double Click!"));
    }

    @Test
    public void testClick (){
        currentEvent = new ClickEvent();
        clickSource.notifyListeners(currentEvent);
        currentEvent = new DblClickEvent();
        clickSource.notifyListeners(currentEvent);
    }

}
