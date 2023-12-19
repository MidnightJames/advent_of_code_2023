public class NodeHistory
{
    private int xPosition;
    private int yPosition;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    public NodeHistory(int xPosition, int yPosition, Direction direction)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        addDirection(direction);
    }

    public void addDirection(Direction direction)
    {
        switch (direction) {
            case Direction.DOWN:
                
                break;
        
            default:
                break;
        }
    }
}
