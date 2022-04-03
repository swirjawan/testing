package com.prudential.platform.util;

import com.couchbase.client.java.Cluster;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CouchbaseUtil {

    private static final Logger LOGGER = Logger.getLogger("CouchbaseUtil");
    @ConfigProperty(name = "prudential.couchbase.cluster")
    public String clusterProperties;
    @ConfigProperty(name = "prudential.couchbase.username")
    public String username;
    @ConfigProperty(name = "prudential.couchbase.password")
    public String password;
    private Cluster cluster;

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public void initBucket() {
        LOGGER.info(clusterProperties);
        LOGGER.info(username);
        LOGGER.info(password);
        setCluster(Cluster.connect(clusterProperties, username, password));
        //cluster.waitUntilReady(Duration.ofSeconds(5));
        LOGGER.info("couchbase is up");


    }

    public void closeBucket() {
        cluster.disconnect();
        LOGGER.info("couchbase is down");
    }

}
