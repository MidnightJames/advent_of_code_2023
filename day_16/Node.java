public class Node
{
    private int xPosition;
    private int yPosition;
    private Direction direction;

    public Node(int xPosition, int yPosition, Direction direction)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.direction = direction;
    }

    public void setX(int newX)
    {
        xPosition = newX;
    }

    public void setY(int newY)
    {
        yPosition = newY;
    }

    public void setDirection(Direction newDirection)
    {
        direction = newDirection;
    }

    public int getX()
    {
        return xPosition;
    }

    public int getY()
    {
        return yPosition;
    }

    public Direction getDirection()
    {
        return direction;
    }
}
