package com.prudential.platform.init;

import com.prudential.platform.util.CouchbaseUtil;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class LifeCycle {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Inject
    CouchbaseUtil cbUtil;


    void onStart(@Observes StartupEvent ev) throws Exception {
        LOGGER.info("The application is starting...");
        cbUtil.initBucket();
        //dsUtil.openConnection();

    }

    void onStop(@Observes ShutdownEvent ev) throws Exception {
        LOGGER.info("The application is stopping...");
        cbUtil.closeBucket();
        //vd.stop();
        //dsUtil.closeConnection();

    }
}