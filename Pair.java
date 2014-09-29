package program1_take2;

public class Pair 
{
	private int u_id;
	private int v_id;
	private int maxWeight;
	
	public Pair()
	{
		
	}
	
	public Pair(int u_id_temp, int v_id_temp)
	{
		u_id = u_id_temp;
		v_id = v_id_temp;
		
	}
	public Pair(int u_id_temp, int v_id_temp, int maxWeight_temp)
	{
		u_id = u_id_temp;
		v_id = v_id_temp;
		maxWeight = maxWeight_temp;
	}
	public void set(int u_id_temp, int v_id_temp)
	{
		u_id = u_id_temp;
		v_id = v_id_temp;
	}
	public void set(int u_id_temp, int v_id_temp, int maxWeight_temp)
	{
		u_id = u_id_temp;
		v_id = v_id_temp;
		maxWeight = maxWeight_temp;
		
	}
	public void print()
	{
		System.out.print(u_id + " " + v_id + " " + maxWeight);
	}
}
