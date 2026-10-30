/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    public GuitarString(double frequency) 
    {
    }

    public GuitarString(double[] array) 
    {
    }

    public void pluck() 
    {
    }

    // advance the simulation one time step
    public void tic() 
    {
    }

    // return the current sample
    public double sample() 
    {
        return 0.0;
    }

    // return number of times tic was called
    public int time() 
    {
        return 0;
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
