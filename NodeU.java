package program1_take2;
import java.util.ArrayList;


public class NodeU 
{
	private int id;
	private int minId;
	private int maxId;
	private int weight;
	private ArrayList<NodeV> alNodeV;
//////////////////	
	public NodeU()
	{
		
	}
//////////////////	
	public NodeU(int id_temp, int minId_temp, int maxId_temp, int weight_temp, ArrayList<NodeV> v)
	{
		id = id_temp;
		minId = minId_temp;
		maxId = maxId_temp;
		weight = weight_temp;
		
		alNodeV = v;
	}
//////////////////
	public int id()
	{
		return id;
	}
//////////////////
	public int minID()
	{
		return minId;
	}
//////////////////
	public int maxID()
	{
		return maxId;
	}
//////////////////
	public int weight()
	{
		return weight;
	}
//////////////////
	public void addNodeV(NodeV vNode)
	{
		alNodeV.add(vNode);
	}
//////////////////
	public ArrayList<NodeV> alNodeV()
	{
		return alNodeV;
	}
//////////////////
	
	
	
	
	
	
}
