import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * About:
 * Other:
 * Created: tyy on 14:05 2018/11/19
 * Editored:
 */
public class TestZoo {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                String path = event.getPath();
                System.out.println(path);
                Event.EventType type = event.getType();
                System.out.println(type);
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("event:" + event);
                }
            }
        };
        ZooKeeper zoo = new ZooKeeper("172.26.113.129:2181", 30000, watcher);
//        zoo.create("/a", "abc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        byte[] rs = zoo.getData("/a", true, new Stat());
        String s = new String(rs);
        System.out.println(s);
        System.out.println("==========");
    }
}