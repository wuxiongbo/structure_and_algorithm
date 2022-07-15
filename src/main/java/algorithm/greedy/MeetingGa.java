package src.main.java.algorithm.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 贪心算法  greedy src.main.java.algorithm
 * 会议问题  meeting
 *
 */
public class MeetingGa {

    private static class Meeting implements Comparable<Meeting> {

        int meNum; // 编号
        int startTime; // 开始时间
        int endTime; // 结束时间

        public Meeting(int meNum, int startTime, int endTime) {
            super();
            this.meNum = meNum;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.endTime > o.endTime) {
                return 1;
            }
            return -1;
        }

        @Override
        public String toString() {
            return "Meeting [meNum=" + meNum + ", startTime=" + startTime
                    + ", endTime=" + endTime + "]";
        }
    }

    //test
    public static void main(String[] args) {
        //初始化 会议列表。
        Scanner cin = new Scanner(System.in);
        List<Meeting> meetings = new ArrayList<>();
        int n = cin.nextInt(); // 会议个数
        for (int i = 0 ;i < n; i++){
            int start = cin.nextInt(); // 会议开始时间
            int end = cin.nextInt();   // 会议结束时间
            Meeting meeting = new Meeting(i+1, start, end);
            meetings.add(meeting);
        }

        meetings.sort(null);//根据会议结束时间排序; 已实现Comparable，所以传入null

        int curTime = 0; //当前的时间(上个会议结束时间);   从一天的0点开始, 如果领导要求从8点开始, 那curTime=8
        for(int i = 0 ; i < n; i ++){
            Meeting meeting = meetings.get(i);
            if(meeting.startTime >= curTime){  //会议的开始时间比我们当前的要大 表示可以开
                System.out.println(meeting);
                curTime = meeting.endTime;     //本次会议到的结束时间。
            }
        }
    }
}
