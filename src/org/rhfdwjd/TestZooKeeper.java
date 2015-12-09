package org.rhfdwjd;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Andrey on 12/8/2015.
 */
public class TestZooKeeper {

    private static final Logger log = LoggerFactory.getLogger(TestZooKeeper.class);

    public static void main(String[] args) throws Exception {
        String zookeeperConnectionString = "localhost:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, new ExponentialBackoffRetry(1000, 3));
        client.start();
        log.info("X={}", client.getData().forPath("/test"));
        //client.getData().watched().forPath("/test");
        //client.create().forPath("/test", "FOOK".getBytes());

        log.info("Test");

        while (true){
            Thread.sleep(1);
        }
    }
}
