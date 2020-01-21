package com.jira.dev;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import static java.lang.Thread.*;

public class ReportEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(ReportEventListener.class);
    private static Map<String, ReportGenerationEvent> fCache = new HashMap();
    private static Map<String, String> fResultCache = new HashMap();

    @Subscribe
    public void generateReport(ReportGenerationEvent event) {
        LOG.info("Request for generating Report  [" + event.getConfig() + "]");

        if (!fCache.containsKey(event.getKey())) {
            fCache.put(event.getKey(), event);
            CompletableFuture.runAsync(() ->
                    startProcessing(event));
        }
    }


    public Boolean isReportCreated(ReportGenerationEvent event) {
        return fResultCache.containsKey(event.getKey());
    }

    private Runnable startProcessing(ReportGenerationEvent event) {

        // TODO: mock code for testing, remove this block
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!fResultCache.containsKey(event.getKey())) {
            fResultCache.put(event.getKey(), "Reports Created @ " + System.currentTimeMillis());
            System.out.println("Reports in Cache Added : " + event.getKey());
        }

        LOG.info("Reports Created : " + event.getKey());

        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Reports Created : " + event.getKey());
            }
        };
    }

    public String retrieveReport(ReportGenerationEvent event) {

        if (!fResultCache.containsKey(event.getKey())) {
            throw new IllegalStateException("Reports not ready");
        }

        return fResultCache.get(event.getKey());
    }

    public void resetEvents() {
        fCache.clear();
        fResultCache.clear();
    }
}
