package au.com.jaycar.mdm.controller;
import au.com.jaycar.search.service.SolrjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class SolrSyncScheduler {

    @Autowired
    private SolrjService service;


    @Scheduled(cron = "0 0 0 * * ? ")
    public void taskCycle() {
        try {
            service.syncMdm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}