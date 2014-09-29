package program1_take2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TestMain2 
{

	public static ArrayList<NodeU> uNodes = new ArrayList<>();
	public static ArrayList<NodeV> vNodes = new ArrayList<>();
	
	public static int maxCard;
	public static int tempCard;
	public static int maxWeight;
	public static int tempWeight;
	public static int count;
	
	public static ArrayList<Integer> markedVnodes = new ArrayList<>();
	public static ArrayList<Pair> tempPair = new ArrayList<Pair>();
	public static ArrayList<ArrayList<Pair>> pair = new ArrayList<ArrayList<Pair>>();
	
	public static void main(String[] args) 
	{
		String filename = "file0_input.txt";
		File file = new File(filename);
		System.out.println(file.toString());

		try
		{
			Scanner scanner = new Scanner(file);
			
			int inputBlocks = scanner.nextInt(); // number of blocks to read
			int sizeSetU = scanner.nextInt(); // read number of tics
			int sizeSetV = scanner.nextInt(); // read number of tacs
			
			// set values for each NodeU
			for(int i = 0; i < sizeSetU; i++)
			{
				int id = scanner.nextInt();
				int minId = scanner.nextInt();
				int maxId = scanner.nextInt();
				int weight = scanner.nextInt();
				
				uNodes.add(new NodeU(id, minId, maxId, weight, new ArrayList<NodeV>()));
			}
			
			// set values for each NodeU
			for(int i = 0; i < sizeSetV; i++)
			{
				int id = scanner.nextInt();
				int weight = scanner.nextInt();
				
				vNodes.add(new NodeV(id, weight));
			}
			
			// set possible matches for each uNode
			for(int i = 0; i < sizeSetU; i++)
			{
				for(int j = 0; j < sizeSetV; j++)
				{
					boolean inUpperBound = vNodes.get(j).id() <= uNodes.get(i).maxID();
					boolean inLowerBound = vNodes.get(j).id() >= uNodes.get(i).minID();
					if( inUpperBound && inLowerBound )
					{
						uNodes.get(i).addNodeV(vNodes.get(j));
					}
				}
			}
			// Graph is now made
			// need to jump into the recursion
			tempWeight = 0;
			maxWeight = Integer.MIN_VALUE; // set initial value so that anything will replace it
			tempCard = 0;
			maxCard = 0;
			count = 0;
			NodeU rr = new NodeU();
			uNodes.add(rr); // this is bc of my stop condition in the recurse it won't allow me to index past the last one to make my return statement
			
			//for(int p = 0; p < uNodes.size()-1; p++) // bc the last one I added doesn't really count
			{	recurse(uNodes.get(0));
				
//				uNodes.remove(uNodes.indexOf(rr));
//				Collections.rotate(uNodes, -1);
//				uNodes.add(rr);
//				System.out.println("maxWeight: " + maxWeight + "\nmaxCard: " + maxCard + "\npairs: " + pair);
//				
//				tempWeight = 0;
//				maxWeight = Integer.MIN_VALUE; // set initial value so that anything will replace it
//				tempCard = 0;
//				maxCard = 0;
//				count = 0;
			}
			
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Error: File not found. Exiting... ");
			e.printStackTrace();
			System.exit(-1);
		}
	}
//////////////////////
	public static void recurse(NodeU unode)
	{
		if(count >= uNodes.size()-1)
		{
			//count--;
			//tempCard--;
			//tempWeight -= (unode.weight() + unode.alNodeV().get(i).weight());
			//tempPair.remove(new Pair(unode.id(), unode.alNodeV().get(i).id()));
			//if(pair.contains(tempPair)
			return;
		}
		// iterates through all possible combos for this particular uNode
		for(int i = 0; i < unode.alNodeV().size(); i++)
		{	
			int vnodeId = unode.alNodeV().get(i).id();
				if(markedVnodes.contains(vnodeId))
				{// I need to move onto its next available vnode
					// two things should happen 1) theres no available nodes
					// 2) I move onto the next node if there are matches left
					continue;
				}
			
			/////////$$$$$$$ !!!!!!!!!!!!!!!!!! $$$$$$$$$$$$$$$ need to mark and unmark available id's
			// potential problematic if another uNode has claimed this particular unode's first choice for vNodes
			Pair uvPair = new Pair(unode.id(), unode.alNodeV().get(i).id(), unode.weight() + unode.alNodeV().get(i).weight());
			markedVnodes.add(unode.alNodeV().get(i).id());
			tempPair.add(uvPair);
			tempWeight += (unode.weight() + unode.alNodeV().get(i).weight());
			tempCard++;
			
			if(tempCard >= maxCard)
			{  maxCard = tempCard;	}
			
			if(tempWeight == maxWeight)
			{
				pair.add(tempPair);
			}
			
			if(tempWeight > maxWeight)
			{
				maxWeight = tempWeight;
				System.out.println("new Max Weight, Pairs: " + pair.toString());
				//System.out.println("new pair: " + tempPair.
				pair = new ArrayList<ArrayList<Pair>>();
				pair.add(tempPair);
			}
			
			count++;
			recurse(uNodes.get(count));
			// its jumping to here when I have a null node
			count--;
			tempCard--;
			tempWeight -= (unode.weight() + unode.alNodeV().get(i).weight());
			tempPair.remove(uvPair);//new Pair(unode.id(), unode.alNodeV().get(i).id()));
			
			markedVnodes.remove(markedVnodes.indexOf(vnodeId));
			
		}
		
	}
	
	
}
