package com.jira.dev;

import com.google.common.eventbus.EventBus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReportEventTest {

    private ReportEventListener listener;
    private EventBus eventBus;

    @Before
    public void setUp() {
        eventBus = new EventBus();
        listener = new ReportEventListener();

        eventBus.register(listener);
    }

    @After
    public void tearDown() {
        eventBus.unregister(listener);
    }

    @Test
    public void generateSingleEvent() throws InterruptedException {
        listener.resetEvents();

        ReportGenerationEvent report1= new ReportGenerationEvent("Report-1");

        eventBus.post(report1);

        assertFalse(listener.isReportCreated(report1) );
        Thread.sleep(2000);
        assertFalse(listener.isReportCreated(report1) );

        Thread.sleep(10000);

        assertNotNull(listener.retrieveReport(report1)) ;
    }

    @Test
    public void generateTwoEvent() throws InterruptedException {
        listener.resetEvents();

        ReportGenerationEvent report1= new ReportGenerationEvent("Report-1");
        ReportGenerationEvent report2= new ReportGenerationEvent("Report-2");

        eventBus.post(report1);

        assertFalse(listener.isReportCreated(report1) );
        Thread.sleep(2000);

        eventBus.post(report2);

        assertFalse(listener.isReportCreated(report1) );

        Thread.sleep(10000);
        assertTrue(listener.isReportCreated(report1) );
        assertFalse(listener.isReportCreated(report2) );

        assertNotNull(listener.retrieveReport(report1)) ;

        Thread.sleep(10000);
        assertTrue(listener.isReportCreated(report2) );
    }

}
