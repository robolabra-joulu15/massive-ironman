
public class MovingPointAverageFilter {

	private int[] cache;
	private int cache_size;
	private int index;
	private int sum;
	private int average;
	
	public MovingPointAverageFilter(int cache_size)
	{
		this.cache_size = cache_size;
		cache = new int[cache_size];
	}
	
	public int filter(int input)
	{
		if (index == cache_size) index = 0;
		
		sum -= cache[index];
		cache[index] = input;
		sum += input;
		index += 1;
		
		average = sum/cache_size;
		return average;
	}
	public float floatFilter(int input)
	{
		if (index == cache_size) index = 0;
		
		sum -= cache[index];
		cache[index] = input;
		sum += input;
		index += 1;
		
		//average = sum/ cache_size;
		return (float) sum / (float) cache_size;
	}
	
}
