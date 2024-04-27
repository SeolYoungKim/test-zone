package loadbalancer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeastConnectionsLoadBalancer {
    private final List<String> servers;
    private final Map<String, Integer> connectionsCountMap;

    public LeastConnectionsLoadBalancer(List<String> servers) {
        this.servers = List.copyOf(servers);
        this.connectionsCountMap = new ConcurrentHashMap<>();

        for (String server : servers) {
            connectionsCountMap.put(server, 0);
        }
    }

    public String nextServer() {
        String nextServer = null;
        int minConnections = Integer.MAX_VALUE;

        for (String server : servers) {
            int connections = connectionsCountMap.get(server);
            if (connections < minConnections) {
                minConnections = connections;
                nextServer = server;
            }
        }

        connectionsCountMap.put(nextServer, connectionsCountMap.get(nextServer) + 1);
        return nextServer;
    }

    public void releaseConnection(String server) {
        Integer connections = connectionsCountMap.get(server);
        if (connections > 0) {
            connectionsCountMap.put(server, connections - 1);
        }
    }

    public static void main(String[] args) {
        List<String> servers = List.of("server1", "server2", "server3", "server4");
        LeastConnectionsLoadBalancer leastConnectionsLoadBalancer = new LeastConnectionsLoadBalancer(servers);

        for (int i = 0; i < 10; i++) {
            String server = leastConnectionsLoadBalancer.nextServer();
            System.out.println("요청 " + (i + 1) + "번째 " + server + "로 진입");
        }

        leastConnectionsLoadBalancer.releaseConnection("server1");
        leastConnectionsLoadBalancer.releaseConnection("server1");

        for (int i = 0; i < 10; i++) {
            String server = leastConnectionsLoadBalancer.nextServer();
            System.out.println("요청 " + (i + 1) + "번째 " + server + "로 진입");
        }
    }
}
