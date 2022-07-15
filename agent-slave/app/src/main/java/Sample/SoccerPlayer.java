package Sample;

public class SoccerPlayer implements Player {
    private String Name;
    private int Num;

    public SoccerPlayer(String name, int num) {
        Name = name;
        Num = num;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }
}
