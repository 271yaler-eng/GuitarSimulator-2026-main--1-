/**
 * This GuitarSimulator object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarSimulator
{
    public static void main(String[] args) 
    {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] keys = new GuitarString[37];

        for(int i = 0; i < keyboard.length(); i++)
        {
            keys[i] = new GuitarString(Math.pow(1.05956, (i-24)) * 440);
        }

        // the main input loop
        while (true) 
        {
            double sample = 0;
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) 
            {
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                int value = keyboard.indexOf(key);
                if(value != -1)
                {
                    keys[value].pluck();
                }
            }
            for(int i = 0; i < keyboard.length(); i++)
            {
                sample += keys[i].sample();
            }
            // send the result to standard audio
            StdAudio.play(sample);
            for(int i = 0; i < keyboard.length(); i++)
            {
                keys[i].tic();
            }
        }
    }
}
