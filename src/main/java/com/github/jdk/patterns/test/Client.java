package com.github.jdk.patterns.test;

import com.github.jdk.patterns.event.ClickEvent;
import com.github.jdk.patterns.event.Event;
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
        clickSource.addEventListener(event -> System.out.println("click!"));
        clickSource.addEventListener(event -> System.out.println("click!"));
        clickSource.addEventListener(event -> System.out.println("click!"));
    }

    @Test
    public void testClick (){
        currentEvent = new ClickEvent();
        clickSource.notifyListeners(currentEvent);
    }

}
