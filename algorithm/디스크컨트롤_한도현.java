public static class Node{
        int startTime;
        int cost;
    }
    public static int solution(int[][] jobs) {
        int answer = 0;
        int N = jobs.length;
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        Arrays.sort(jobs, new Comparator<int []>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        int nodeNum = 0;
        int curTime = 0;
        while(true){
            if(pq.isEmpty() && (nodeNum!=N)){
                Node newNode = new Node();
                newNode.cost = jobs[nodeNum][1];
                newNode.startTime = jobs[nodeNum][0];
                pq.add(newNode);
                nodeNum++;
                curTime = newNode.startTime;
            }
            if(pq.isEmpty()) break;
            while(!pq.isEmpty()){
                Node cur = pq.poll();
                answer += (curTime - cur.startTime + cur.cost);
                curTime += cur.cost;
                for(int i=nodeNum;i<N;i++){
                    nodeNum = i+1;
                    if(jobs[i][0] > curTime){
                        nodeNum = i;
                        break;
                    }
                    Node next = new Node();
                    next.startTime = jobs[i][0];
                    next.cost = jobs[i][1];
                    pq.add(next);
                }
            }

        }
        return answer / N;
    }
