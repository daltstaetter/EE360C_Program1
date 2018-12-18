package program1_take2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class TestMain2 
{
	public static ArrayList<NodeU> uNodes = new ArrayList<>();
	public static ArrayList<NodeV> vNodes = new ArrayList<>();
	
	public static int maxCard;
	public static int tempCard;
	public static int maxWeight;
	public static int tempWeight;
	public static int count;
	public static int solutionNumber = 0;
	
	public static int global_start = 0;
	
	public static ArrayList<Integer> markedUnodes = new ArrayList<>();
	public static ArrayList<Integer> markedVnodes = new ArrayList<>();
	public static ArrayList<Pair> tempPair = new ArrayList<Pair>();
	public static ArrayList<ArrayList<Pair>> pair = new ArrayList<ArrayList<Pair>>();
	public static ArrayList<ArrayList<Pair>> truePair = new ArrayList<ArrayList<Pair>>();
	
	public static PrintWriter output;
	
	public static void main(String[] args) 
	{
		String filename = args[0]; // need to 
//		System.out.println(filename);
		String path = new String();
		try 
		{	String baseName;
			if(filename.contains("\\"))
			{
				baseName = new String(filename.substring(filename.lastIndexOf("\\")+1, filename.indexOf(".")));
			}
			else if(filename.contains("/"))
			{
				baseName = new String(filename.substring(filename.lastIndexOf("/")+1, filename.indexOf(".")));
			}	
			else
			{
				baseName = new String(filename.substring(0, filename.indexOf(".")));
			}
		
			output = new PrintWriter(new FileWriter(baseName + ".out")); // file im writing to
		} 
		catch (IOException e1) 
		{	e1.printStackTrace();	}
		
		try
		{
			
//////////////////////////////////////		
	
			File file = new File(filename);
			if(filename.contains("\\") )
			{
				int startBaseName = filename.lastIndexOf("\\") + 1;
				path = filename.substring(0,startBaseName-1);
				//filename = filename.substring(startBaseName,filename.length());
				//file = new File(filename.substring(startBaseName,filename.length()));
				
			}
			else if(filename.contains("/"))
			{
				int startBaseName = filename.lastIndexOf("/") + 1;
				path = filename.substring(0,startBaseName-1);
				//filename = filename.substring(startBaseName,filename.length());
				//file = new File(filename.substring(startBaseName,filename.length()));
			}
			
//			String baseName = new String(filename.substring(0, filename.indexOf(".")));
			String extension = new String(filename.substring(filename.indexOf("."), filename.length()));
//			File file = new File(filename);
			
			//output = new PrintWriter(new FileWriter(baseName + ".out"));
			
			
//////////////////////////////////////
			Scanner scanner = new Scanner(file);
			
			int inputBlocks = scanner.nextInt(); // number of blocks to read
			
			for(int qwe = 0; qwe < inputBlocks; qwe++)
			{
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
				//tempCard = 0;
				count = 0;
				NodeU rr = new NodeU();
				uNodes.add(rr); // this is bc of my stop condition in the recurse it won't allow me to index past the last one to make my return statement
				ArrayList<ArrayList<Pair>> truePairDbg = truePair;
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
				for(int p = 0; p < uNodes.size()-1; p++)
				{
					findCard(uNodes.get(0));
					//System.out.println("Max Card: " + maxCard + "\ntempCard: " + tempCard);
					uNodes.remove(uNodes.indexOf(rr));
					Collections.rotate(uNodes, -1);
					uNodes.add(rr);
				}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				tempWeight = 0;
				maxWeight = Integer.MIN_VALUE; // set initial value so that anything will replace it
				tempCard = 0;
				count = 0;
				// determine only the weight
		//		for(int p = 0; p < uNodes.size()-1; p++)
				{
					uNodes.remove(uNodes.indexOf(rr));
					Collections.rotate(uNodes, -1);
					uNodes.add(rr);
					recurse(uNodes.get(0));
					
	//				System.out.println("Max weight: " + maxWeight);
	//				uNodes.remove(uNodes.indexOf(rr));
	//				Collections.rotate(uNodes, -1);
	//				uNodes.add(rr);
					
					tempWeight = 0;
					tempCard = 0;
					count = 0;
					markedVnodes.clear();
					markedUnodes.clear();
				}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
	//			for(int p = 0; p < uNodes.size()-1; p++) // bc the last one I added doesn't really count
				{	//recurse(uNodes.get(0));
				
					//System.out.println("maxWeight: " + maxWeight);// + "\nmaxCard: " + maxCard );//+ "\npairs: " + pair);
//					System.out.println("##############\nTrying to READ THEM\n##############");
					tempWeight = 0;
					count = 0;
					markedVnodes.clear();
					markedUnodes.clear();
					pair.clear();
	
					recurseOnlyPairs(uNodes.get(0));
					
					//System.out.println("maxWeight: " + maxWeight + "\nmaxCard: " + maxCard );//+ "\npairs: " + pair);
	//				System.out.println("Solutions: " + solutionNumber + "-1");
	
					//System.out.println("maxWeight: " + maxWeight);// + "\nmaxCard: " + maxCard );//+ "\npairs: " + pair);
	
					tempWeight = 0;
					tempCard = 0;
					count = 0;
					
					uNodes.remove(uNodes.indexOf(rr));
					Collections.rotate(uNodes, -1);
					uNodes.add(rr);
				}
//				System.out.println("THE SIZE OF TRUE PAIR: " + truePair.size());
				
				TreeSet<String> tsOutput = new TreeSet<>();
				tsOutput = displayResults();
				
				tempCard = Integer.MIN_VALUE;
				tempWeight = Integer.MIN_VALUE;
				maxCard = Integer.MIN_VALUE;
				maxWeight = Integer.MIN_VALUE;
				markedUnodes.clear();
				markedVnodes.clear();
				uNodes.clear();
				vNodes.clear();
				count = 0;
				solutionNumber = 0;
				tempPair.clear();
				pair.clear();
				truePair.clear();
				truePairDbg.clear();
			}
			scanner.close();
			output.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Error: File not found. Exiting... ");
			e.printStackTrace();
			System.exit(-1);
		}
	}
//////////////////////
	public static TreeSet<String> displayResults()
	{
		TreeSet<String> builder = new TreeSet<>(); // used for intermediate lexicographical ordering
		TreeSet<String> finalBuilder = new TreeSet<>();
		String outputLine = new String();
		
		for(int numLines = 0; numLines < truePair.size(); numLines++)
		{
			for(int g = 0; g < truePair.get(0).size(); g++)
			{
				outputLine = truePair.get(numLines).get(g).uid() + ":" + truePair.get(numLines).get(g).vid() + "";
				builder.add(outputLine);
			}
			String s = new String();
			s = builder.toString().replaceAll("[^,^:^0-9^]]", "");
			s = s.toString().replaceAll(",", " ");
			builder.clear();
					//replaceAll(, ""));
			int a = 0;
			//finalBuilder.add(builder.toString().replaceAll(, ""));
			//System.out.println(builder.toString());
			finalBuilder.add(s);
//			System.out.printf("\n%d", finalBuilder.size());
			int b = 0;
			
		}
		
		Iterator<String> i = finalBuilder.iterator();
		
//		for(int y = 0; y < finalBuilder.size(); y++)
//		{
//		}
		
//		System.out.println("<--------------------->");
//		System.out.println(finalBuilder.size());
		output.println(finalBuilder.size());
		while(i.hasNext())
		{
			String s12 = new String(i.next().replaceAll("[^0-9^:^\\s]", ""));
			s12 = s12.replaceAll("\\s\\s+", " "); // match one or more whitespaces
//			System.out.println(s12);
			output.println(s12);
		}
		
		
//		System.out.println(finalBuilder.toString());
		
		return builder;
	}
	
//////////////////////
	public static void recurseOnlyPairs(NodeU unode)
	{
		if(count >= uNodes.size()-1)
		{
			return;
		}
		// iterates through all possible combos for this particular uNode
		boolean has_it_met_an_empty_node= true;
		
		int s_ = unode.alNodeV().size();
		int id = unode.id();
		int tmpwt = tempWeight;
		ArrayList<Integer> markedVnod = new ArrayList<>(markedVnodes);
		ArrayList<Integer> markedUnod = new ArrayList<>(markedUnodes);
		int cnt = count;
		
		for(int i = 0; i < unode.alNodeV().size(); ++i)
		{	
			int vnodeId = unode.alNodeV().get(i).id();
				if(markedVnodes.contains(vnodeId))
				{// I need to move onto its next available vnode
					// two things should happen 1) theres no available nodes
					// 2) I move onto the next node if there are matches left
					continue;
				}
			has_it_met_an_empty_node = false;
			/////////$$$$$$$ !!!!!!!!!!!!!!!!!! $$$$$$$$$$$$$$$ need to mark and unmark available id's
			// potential problematic if another uNode has claimed this particular unode's first choice for vNodes
			Pair uvPair = new Pair(unode.id(), unode.alNodeV().get(i).id(), unode.weight() + unode.alNodeV().get(i).weight());
			markedVnodes.add(unode.alNodeV().get(i).id()); markedVnod = new ArrayList<>(markedVnodes);
			//markedUnodes.add(unode.get)
			tempPair.add(uvPair);
			tempWeight += (unode.weight() + unode.alNodeV().get(i).weight()); tmpwt = tempWeight;
			tempCard = markedVnodes.size();
			
			if(tempWeight == maxWeight  && tempCard == maxCard)
			{ // print them lexicographically
				pair.clear();
				pair.add(new ArrayList<Pair>(tempPair));
				int prSize = pair.size();
out:     // the break jumps out of both for loops
				
				//for(int ttt = 0; ttt < pair.size(); ttt++)
				{
					//if(ttt == 0)
					//{ redundant++; x = redundant;}
					//if(pair.get(ttt).size() < truePair.get(index)  )
					// Here I need to skip it if it is less than the cardinality I have
					int currentTruePairSize = truePair.size();
					if (truePair.isEmpty()) // if empty add no matter what, after that check it against size every time
					{	truePair.add(new ArrayList<Pair>(pair.get(prSize-1)));
						for(int tt = 0; tt < pair.get(0).size(); tt++)
						{
//							pair.get(prSize-1).get(tt).print();
//							System.out.println("");
							truePair.get(truePair.indexOf(pair.get(prSize-1))).get(tt).setIdentifier(solutionNumber);
						}
//						System.out.println("---------------");
					
					}
					else
					{
						boolean dontAdd = false;
						//for(int bn = 0; bn < currentTruePairSize; bn += pair.get(0).size())
						{
//							if(pair.get(ttt).size() < truePair.get(bn).size() )
//							{ 	// the weight is wrong if it jumped in here I need to fix that back to the original weight
//								solutionNumber--; 
//								break out;	
//							} // solutionNumber will increment anyway,this undos it
							//if( pair.get(ttt).size() == truePair.get(bn).size())
							{
								for(int np = 0; np < truePair.size(); np++)
								{
									if(truePair.get(np).get(0).uid() == pair.get(0).get(0).uid()   && truePair.get(np).get(0).vid() == pair.get(0).get(0).vid() && truePair.get(np).get(1).uid() == pair.get(0).get(1).uid()   && truePair.get(np).get(1).vid() == pair.get(0).get(1).vid() && truePair.get(np).get(2).uid() == pair.get(0).get(2).uid()   && truePair.get(np).get(2).vid() == pair.get(0).get(2).vid()) 
									{
										dontAdd = true; // don't add it if all the values are the same for that input
									}
								}
								if(dontAdd == false)
								{ // I add it and display it
									truePair.add(new ArrayList<Pair>(pair.get(prSize-1))); 
								
									for(int tt = 0; tt < pair.get(0).size(); tt++)
									{
//										pair.get(prSize-1).get(tt).print();
//										System.out.println("");
										truePair.get(truePair.indexOf(pair.get(prSize-1))).get(tt).setIdentifier(solutionNumber);
									}
//									System.out.println("---------------");
								}
								
								
							}
						}
					}
					
					
//					for(int tt = 0; tt < pair.get(0).size(); tt++)
//					{
//						pair.get(ttt).get(tt).print();
//						System.out.println("");
//						truePair.get(truePair.indexOf(pair.get(ttt))).get(tt).setIdentifier(solutionNumber);
//					}System.out.println("---------------");
					
						
				}
				solutionNumber++;
			}
//			tempCard = pair.size();
			
			count++; cnt = count;
			recurseOnlyPairs(uNodes.get(count));
			// its jumping to here when I have a null node
			count--; cnt = count;
			//tempCard--;
			tempWeight -= (unode.weight() + unode.alNodeV().get(i).weight()); tmpwt = tempWeight;
			tempPair.remove(uvPair);//new Pair(unode.id(), unode.alNodeV().get(i).id()));
			
			//if(uNodes.get(0).id() != unode.id()) // removes the next node I need to look at
			{	markedVnodes.remove(markedVnodes.indexOf(vnodeId));	markedVnod = new ArrayList<>(markedVnodes);}
			tempCard = markedVnodes.size();
		}
		if(has_it_met_an_empty_node)
		{
			count++; cnt = count;
			recurseOnlyPairs(uNodes.get(count));
			count--; cnt = count;
			int b = 7; 
			int a = 0;
		}
		else if(has_it_met_an_empty_node == false && count < uNodes.size()-1)
		{
			
			// don't want to go 2 4 6 etc.
//			want to go 2 3 4 5 6--8 9 10 etc
			count++;cnt = count;
			recurseOnlyPairs(uNodes.get(count));
			count--; cnt = count;
			
			count += 2;cnt = count;
			if(count < uNodes.size()-2)
			{
				recurseOnlyPairs(uNodes.get(count));
			}
			count -= 2; cnt = count;	
		}
		
	}
/////////////////////
	public static void findCard(NodeU unode)
	{
		if(count >= uNodes.size()-1)
		{
			return;
		}
		ArrayList<Integer> markedVnod = new ArrayList<>(markedVnodes);
		// iterates through all possible combos for this particular uNode
		boolean has_it_met_an_empty_node= true;
		for(int i = 0; i < unode.alNodeV().size(); i++)
		{	
			int vnodeId = unode.alNodeV().get(i).id();
			
				if(markedVnodes.contains(vnodeId))
				{// I need to move onto its next available vnode two things should happen 1) theres no available nodes 2) I move onto the next node if there are matches left
					continue;
				}
				
			has_it_met_an_empty_node = false;
			int cnt = count;
//			int tmpcrd = tempCard;
//			int maxcrdtemp = maxCard;
//			int tempSize = tempPair.size();
//			int pairSize = pair.size();
			
			
			Pair uvPair = new Pair(unode.id(), unode.alNodeV().get(i).id(), unode.weight() + unode.alNodeV().get(i).weight());
			markedVnodes.add(unode.alNodeV().get(i).id()); markedVnod = markedVnodes;
			tempPair.add(uvPair);
			tempCard = markedVnodes.size(); //tmpcrd = tempCard;
			
			if(tempCard > maxCard)
			{
				maxCard = tempCard; //maxcrdtemp = maxCard;
			}
			
			count++;
			findCard(uNodes.get(count));
			// its jumping to here when I have a null node
			count--;
			tempPair.remove(uvPair);//new Pair(unode.id(), unode.alNodeV().get(i).id()));
			markedVnodes.remove(markedVnodes.indexOf(vnodeId)); markedVnod = markedVnodes;
			tempCard = markedVnodes.size(); //tmpcrd = tempCard;
		}
		if(has_it_met_an_empty_node)
		{
			count++;
			findCard(uNodes.get(count));
			count--;
		}
		else if(has_it_met_an_empty_node == false && count < uNodes.size()-1)
		{
			
			// don't want to go 2 4 6 etc.
//			want to go 2 3 4 5 6--8 9 10 etc
			count++;// = count;
			findCard(uNodes.get(count));
			count--;// cnt = count;
			
			count += 2;//cnt = count;
			if(count < uNodes.size()-2)
			{
				findCard(uNodes.get(count));
			}
			count -= 2;// cnt = count;	
		}
		
	}	
/////////////////////
	public static void recurse(NodeU unode)
	{
		if(count >= uNodes.size()-1 || maxCard == markedVnodes.size())
		{
			return;
		}
		int cnt;
		ArrayList<ArrayList<Pair>> pairDebug = pair;
		ArrayList<Integer> markedVnod = new ArrayList<>(markedVnodes);
		ArrayList<Integer> markedUnod = new ArrayList<>(markedUnodes);
		// iterates through all possible combos for this particular uNode
		boolean has_it_met_an_empty_node= true;
		for(int i = 0; i < unode.alNodeV().size(); i++)
		{	
			int vnodeId = unode.alNodeV().get(i).id();
			
				if(markedVnodes.contains(vnodeId))
				{// I need to move onto its next available vnode two things should happen 1) theres no available nodes 2) I move onto the next node if there are matches left
					continue;
				}
				
			has_it_met_an_empty_node = false;

			int mxwt = maxWeight; 	int id = unode.id();
			int tmpwt = tempWeight; int tempSize = tempPair.size();
			int pairSize = pair.size();
			
			Pair uvPair = new Pair(unode.id(), unode.alNodeV().get(i).id(), unode.weight() + unode.alNodeV().get(i).weight());
			markedVnodes.add(unode.alNodeV().get(i).id()); markedVnod = markedVnodes;
			markedUnodes.add(unode.id()); markedUnod = markedUnodes;
			tempPair.add(uvPair);
			tempWeight += (unode.weight() + unode.alNodeV().get(i).weight()); tmpwt = tempWeight;
			tempCard = markedVnodes.size(); // need to compare to my maxCard that is set
			
			if(tempWeight == maxWeight && tempCard == maxCard)
			{
				pair.add(tempPair);
//				System.out.println("MAX WEIGHT TIE: " + maxWeight);
			}
			
			if(tempWeight > maxWeight && tempCard == maxCard)
			{
				maxWeight = tempWeight; mxwt = maxWeight;
				pair = new ArrayList<ArrayList<Pair>>(pair);
				pair.add(tempPair); pairDebug = pair;
//				System.out.println("NEW MAX WEIGHT: " + maxWeight);
				
//				for(int ttt = 0; ttt < pair.size(); ttt++)
//				{
//					for(int tt = 0; tt < pair.get(0).size(); tt++)
//					{
//						pair.get(ttt).get(tt).print();
//						System.out.println("");
//					}
//				}
			}
			
			count++; cnt = count;
			recurse(uNodes.get(count));
			// its jumping to here when I have a null node
			count--; cnt = count;
			//tempCard--;
			tempWeight -= (unode.weight() + unode.alNodeV().get(i).weight()); tmpwt = tempWeight;
			tempPair.remove(uvPair);//new Pair(unode.id(), unode.alNodeV().get(i).id()));
			
			markedVnodes.remove(markedVnodes.indexOf(vnodeId)); markedVnod = markedVnodes;
			markedUnodes.remove(markedUnodes.indexOf(unode.id())); markedUnod = markedUnodes;
			
			int a = 0;
			count += a;
		}
		if(has_it_met_an_empty_node)
		{ // if it has gone through all its comibnations it will not go onto the next node that could be matched
			count++; cnt = count;
			recurse(uNodes.get(count));
			count--; cnt = count;
		}
		else if(has_it_met_an_empty_node == false && count < uNodes.size()-1)
		{
			
			// don't want to go 2 4 6 etc.
//			want to go 2 3 4 5 6--8 9 10 etc
			count++;cnt = count;
			recurse(uNodes.get(count));
			count--; cnt = count;
			
			count += 2;cnt = count;
			if(count < uNodes.size()-2)
			{
				recurse(uNodes.get(count));
			}
			count -= 2; cnt = count;	
		}
		
	}
	
	
}
