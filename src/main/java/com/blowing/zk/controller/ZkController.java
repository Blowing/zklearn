package com.blowing.zk.controller;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZookeeperBanner;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Classname ZkController
 * @Description TODO
 * @Date 2020/5/20 14:12
 * @Created by wujie
 */

@RestController
@RequestMapping("/zk")
public class ZkController {


    private String key;
    @GetMapping("/hello")
    public String hello() throws IOException, InterruptedException {
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
        return key;
    }

    @GetMapping("/sql2")
    private String sql () throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 20000, null);

//        zk.create("/abc", "wujie".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        Stat stat  = new Stat();
        byte[] data = zk.getData("/config/zklearn/dev/hello.hss", false, stat);
        System.out.println(new String(data));
        System.out.println(stat.getDataLength());

//        zk.getData("/abc", watchedEvent -> {
//            System.out.println("path:" + watchedEvent.getPath());
//            System.out.println("KeeperState:" + watchedEvent.getState());
//            System.out.println("EventType:" + watchedEvent.getType());
//        }, null);
//
//        zk.setData("/abc", "456".getBytes(), -1);
//        zk.setData("/abc", "789".getBytes(), -1);
//        Thread.sleep(1000);
        zk.close();
        return "你好";
    }
}
