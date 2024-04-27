package loadbalancer;

import java.util.List;

public class RoundRobinLoadBalancer {
    private final List<String> servers;
    private int currentIndex;

    public RoundRobinLoadBalancer(List<String> servers) {
        this.servers = servers;
    }

    public String nextServer() {
        int index = currentIndex % servers.size();
        currentIndex++;
        return servers.get(index);
    }

    public static void main(String[] args) {
        List<String> servers = List.of("server1", "server2", "server3", "server4");
        RoundRobinLoadBalancer roundRobinLoadBalancer = new RoundRobinLoadBalancer(servers);

        for (int i = 0; i < 10; i++) {
            String server = roundRobinLoadBalancer.nextServer();
            System.out.println("요청 " + (i + 1) + "번째 " + server + "로 진입");
        }
    }
}
