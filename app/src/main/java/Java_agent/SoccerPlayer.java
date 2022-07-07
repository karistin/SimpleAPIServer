package Java_agent;

public class SoccerPlayer implements Soccer, Soccer2 {
    private String name = "kakak";
    private int num = 11;

    public SoccerPlayer(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }
}
