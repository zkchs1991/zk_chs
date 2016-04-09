package com.github.apache.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by zk_chs on 16/4/9.
 */
public class TestZKClient {

    private static ZkClient zkClient = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
    private static final String node = "/zk_chs";

    public static void main(String[] args) throws InterruptedException {
        testZkClient();
        subscribeDataChange();
        zkClient.writeData(node, "new data");
        zkClient.delete(node);
        Thread.sleep(1000);
    }

    private static void testZkClient (){
        if ( !zkClient.exists(node) ){
            zkClient.createPersistent(node, "this is first node");
        }
        System.out.println((String) zkClient.readData(node));
    }

    private static void subscribeDataChange (){
        zkClient.subscribeDataChanges(node, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("node data changed: " + dataPath + ", data: " + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("node data deleted: " + dataPath);
            }
        });
    }

}
