package program1_take2;

public class Pair 
{
	private int u_id;
	private int v_id;
	private int maxWeight;
	private int identifier1;
	
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
	public void set(int u_id_temp, int v_id_temp, int maxWeight_temp, int identifier_temp)
	{
		u_id = u_id_temp;
		v_id = v_id_temp;
		maxWeight = maxWeight_temp;
		identifier1 = identifier_temp;
	}
	public void setIdentifier(int tempIdentifier)
	{
		identifier1 = tempIdentifier;
	}
	
	public int weight()
	{
		return maxWeight;
	}
	public int uid()
	{
		return u_id;
	}
	public int vid()
	{
		return v_id;
	}
	public int identifier()
	{
		return identifier1;
	}
	public void print()
	{
		System.out.print(u_id + " " + v_id + " " + maxWeight);
	}
}
