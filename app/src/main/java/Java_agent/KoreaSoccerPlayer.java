package Java_agent;

public class KoreaSoccerPlayer extends SoccerPlayer{
    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setNum(int num) {
        super.setNum(num);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getNum() {
        return super.getNum();
    }

    public KoreaSoccerPlayer(String name, int num) {
        super(name, num);
    }
}
