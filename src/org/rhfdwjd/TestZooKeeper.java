package org.rhfdwjd;

import com.oracle.webservices.internal.api.message.DistributedPropertySet;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
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
        //log.info("X={}", client.getData().forPath("/test"));
        //client.getData().watched().forPath("/test");
        //client.createContainers("/test");
        //client.setData().forPath("/test", "FOOK".getBytes());

        //client.create().withMode(CreateMode.EPHEMERAL).forPath("/testEv", "FOOK".getBytes());

        log.info("Test");

        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            public void takeLeadership(CuratorFramework client) throws Exception {
                while (true) {
                    log.info("I'm still a big leader yea!");
                    Thread.sleep(1000);
                }
            }
        };

        LeaderSelector selector = new LeaderSelector(client, "/leader", listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();

        while (true) {
            Thread.sleep(1);
        }
    }
}
