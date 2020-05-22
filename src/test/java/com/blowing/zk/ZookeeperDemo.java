package com.blowing.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

/**
 * @Classname ZookeeperDemo
 * @Description TODO
 * @Date 2020/5/21 13:37
 * @Created by 86152
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ZookeeperDemo {

    @Test
    public void connectZkCluster() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181",
                20000,
                new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                String path = watchedEvent.getPath();
                System.out.println("path:" + path);

                Watcher.Event.KeeperState state = watchedEvent.getState();
                System.out.println("keeperState:" + state);

                Watcher.Event.EventType type = watchedEvent.getType();
                System.out.println("EventType:" +type);

            }
        });
        zooKeeper.close();
    }
}
