/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */

public class RingBuffer 
{

    private double[] ringbuffer;
    private int front;
    private int rear;
    private int size;
    public RingBuffer(int capacity)
    {
        this.ringbuffer = new double[capacity];
        this.rear = -1;
        this.front = 0;
        this.size = 0;
    }

    public int size()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public boolean isFull()
    {
        return size == ringbuffer.length;
    }
    
    public void add(double value)
    {
        if(isFull())
        {
            double[] newbuffer = new double[ringbuffer.length * 2];
            for(int i = 0; i < size; i++)
            {
                newbuffer[i] = ringbuffer[(front + i) % ringbuffer.length];
            }
            ringbuffer = newbuffer;
            front = 0;
            rear = size - 1;
        }
        rear = (rear + 1) % ringbuffer.length;
        ringbuffer[rear] = value;
        size++;
    }
    
    public double peek()
    {
        return ringbuffer[front];
    }
    
    public double remove()
    {
        double temp = ringbuffer[front];
        ringbuffer[front] = 0;
        front = (front + 1) % ringbuffer.length;
        size--;
        if(size == 0)
        {
            front = 0;
            rear = -1;
        }
        return temp;
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
