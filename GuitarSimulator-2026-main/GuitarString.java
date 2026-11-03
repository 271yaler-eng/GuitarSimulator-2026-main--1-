/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    protected RingBuffer guitarbuffer;
    protected static int count;
    public GuitarString(double frequency) 
    {
        guitarbuffer = new RingBuffer((int)(44100/frequency));
        int size = (int)(44100/frequency);

        for(int i = 0; i < size; i++)
        {
            guitarbuffer.add(0.0);
        }
    }

    public GuitarString(double[] array) 
    {
        guitarbuffer = new RingBuffer(array.length);
        for(int i = 0; i < array.length; i++)
        {
            guitarbuffer.add(array[i]);
        }
    }

    public void pluck() 
    {
        int size = guitarbuffer.size();
        for(int i = 0; i < size; i++)
        {
            guitarbuffer.remove();
        }
        for(int j = 0; j < size; j++)
        {
            double random = (Math.random())-0.5;
            guitarbuffer.add(random);
        }
    }

    // advance the simulation one time step
    // Apply the Karplus-Strong update: delete the sample at the front of the ring buffer 
    // and add to the end of the ring buffer the average of the first two samples, 
    // multiplied by the energy decay factor.  
    public void tic() 
    {
        double removed = guitarbuffer.remove();
        double calculated = 0.994*0.5*(removed+guitarbuffer.peek());
        guitarbuffer.add(calculated);
        count++;
    }

    // return the current sample
    public double sample() 
    {
        return guitarbuffer.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return count;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
