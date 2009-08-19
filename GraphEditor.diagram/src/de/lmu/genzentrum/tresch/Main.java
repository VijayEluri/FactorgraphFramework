package de.lmu.genzentrum.tresch;

import java.util.LinkedList;


public class Main {

	public static void main(String[] args) throws NoValueException {
	/*	Object[] defWerte=new Object[3];
		defWerte[0]=0;
		defWerte[1]=1;
		defWerte[2]=2;
		
		Object[][] funcFA=new Object[2][3];
		funcFA[0][0]=0;
		funcFA[1][0]=0.4;
		funcFA[0][1]=1;
		funcFA[1][1]=0.4;
		funcFA[0][2]=2;
		funcFA[1][2]=0.2;
		
		Object[][] funcFB=new Object[2][3];
		funcFB[0][0]=0;
		funcFB[1][0]=0.5;
		funcFB[0][1]=1;
		funcFB[1][1]=0.3;
		funcFB[0][2]=2;
		funcFB[1][2]=0.2;
		
		int[] nodeArrayFC=new int[3];
		nodeArrayFC[0]=1;
		nodeArrayFC[1]=3;
		nodeArrayFC[2]=9;		
		Object[][] funcFC=new Object[4][27];
		funcFC[0][0]=0;
		funcFC[1][0]=0;
		funcFC[2][0]=0;
		funcFC[3][0]=0.08;
		funcFC[0][1]=1;
		funcFC[1][1]=0;
		funcFC[2][1]=0;
		funcFC[3][1]=0.03;
		funcFC[0][2]=0;
		funcFC[1][2]=1;
		funcFC[2][2]=0;
		funcFC[3][2]=0.02;
		funcFC[0][3]=0;
		funcFC[1][3]=0;
		funcFC[2][3]=1;
		funcFC[3][3]=0.02;
		funcFC[0][4]=1;
		funcFC[1][4]=1;
		funcFC[2][4]=0;
		funcFC[3][4]=0.025;
		funcFC[0][5]=1;
		funcFC[1][5]=0;
		funcFC[2][5]=1;
		funcFC[3][5]=0.02;
		funcFC[0][6]=0;
		funcFC[1][6]=1;
		funcFC[2][6]=1;
		funcFC[3][6]=0.03;
		funcFC[0][7]=1;
		funcFC[1][7]=1;
		funcFC[2][7]=1;
		funcFC[3][7]=0.04;
		funcFC[0][8]=2;
		funcFC[1][8]=0;
		funcFC[2][8]=0;
		funcFC[3][8]=0.06;
		funcFC[0][9]=0;
		funcFC[1][9]=2;
		funcFC[2][9]=0;
		funcFC[3][9]=0.04;
		funcFC[0][10]=0;
		funcFC[1][10]=0;
		funcFC[2][10]=2;
		funcFC[3][10]=0.05;
		funcFC[0][11]=2;
		funcFC[1][11]=2;
		funcFC[2][11]=0;
		funcFC[3][11]=0.015;
		funcFC[0][12]=2;
		funcFC[1][12]=0;
		funcFC[2][12]=2;
		funcFC[3][12]=0.03;
		funcFC[0][13]=0;
		funcFC[1][13]=2;
		funcFC[2][13]=2;
		funcFC[3][13]=0.04;
		funcFC[0][14]=2;
		funcFC[1][14]=2;
		funcFC[2][14]=2;
		funcFC[3][14]=0.015;
		funcFC[0][15]=1;
		funcFC[1][15]=2;
		funcFC[2][15]=2;
		funcFC[3][15]=0.02;
		funcFC[0][16]=2;
		funcFC[1][16]=1;
		funcFC[2][16]=2;
		funcFC[3][16]=0.03;
		funcFC[0][17]=2;
		funcFC[1][17]=2;
		funcFC[2][17]=1;
		funcFC[3][17]=0.05;
		funcFC[0][18]=1;
		funcFC[1][18]=1;
		funcFC[2][18]=2;
		funcFC[3][18]=0.025;
		funcFC[0][19]=1;
		funcFC[1][19]=2;
		funcFC[2][19]=1;
		funcFC[3][19]=0.01;
		funcFC[0][20]=2;
		funcFC[1][20]=1;
		funcFC[2][20]=1;
		funcFC[3][20]=0.025;
		funcFC[0][21]=0;
		funcFC[1][21]=1;
		funcFC[2][21]=2;
		funcFC[3][21]=0.07;
		funcFC[0][22]=1;
		funcFC[1][22]=0;
		funcFC[2][22]=2;
		funcFC[3][22]=0.045;
		funcFC[0][23]=1;
		funcFC[1][23]=2;
		funcFC[2][23]=0;
		funcFC[3][23]=0.06;		
		funcFC[0][24]=2;
		funcFC[1][24]=1;
		funcFC[2][24]=0;
		funcFC[3][24]=0.03;
		funcFC[0][25]=2;
		funcFC[1][25]=0;
		funcFC[2][25]=1;
		funcFC[3][25]=0.07;
		funcFC[0][26]=0;
		funcFC[1][26]=2;
		funcFC[2][26]=1;
		funcFC[3][26]=0.05;
		
		int[] nodeArrayFD=new int[2];
		nodeArrayFD[0]=9;
		nodeArrayFD[1]=4;
		Object[][] funcFD=new Object[3][9];
		funcFD[0][0]=0;
		funcFD[1][0]=0;
		funcFD[2][0]=0.1;
		funcFD[0][1]=1;
		funcFD[1][1]=1;
		funcFD[2][1]=0.15;
		funcFD[0][2]=2;
		funcFD[1][2]=2;
		funcFD[2][2]=0.05;
		funcFD[0][3]=0;
		funcFD[1][3]=1;
		funcFD[2][3]=0.2;
		funcFD[0][4]=1;
		funcFD[1][4]=0;
		funcFD[2][4]=0.05;
		funcFD[0][5]=0;
		funcFD[1][5]=2;
		funcFD[2][5]=0.05;
		funcFD[0][6]=2;
		funcFD[1][6]=0;
		funcFD[2][6]=0.2;
		funcFD[0][7]=1;
		funcFD[1][7]=2;
		funcFD[2][7]=0.1;
		funcFD[0][8]=2;
		funcFD[1][8]=1;
		funcFD[2][8]=0.1;
		
		int[] nodeArrayFE=new int[2];
		nodeArrayFE[0]=9;
		nodeArrayFE[1]=6;
		Object[][] funcFE=new Object[3][9];
		funcFE[0][0]=0;
		funcFE[1][0]=0;
		funcFE[2][0]=0.05;
		funcFE[0][1]=1;
		funcFE[1][1]=1;
		funcFE[2][1]=0.2;
		funcFE[0][2]=2;
		funcFE[1][2]=2;
		funcFE[2][2]=0.05;
		funcFE[0][3]=0;
		funcFE[1][3]=1;
		funcFE[2][3]=0.15;
		funcFE[0][4]=1;
		funcFE[1][4]=0;
		funcFE[2][4]=0.1;
		funcFE[0][5]=0;
		funcFE[1][5]=2;
		funcFE[2][5]=0.1;
		funcFE[0][6]=2;
		funcFE[1][6]=0;
		funcFE[2][6]=0.1;
		funcFE[0][7]=1;
		funcFE[1][7]=2;
		funcFE[2][7]=0.2;
		funcFE[0][8]=2;
		funcFE[1][8]=1;
		funcFE[2][8]=0.05;
		
		FactorNode f1=new FactorNode("fA",0,funcFA);
		FactorNode f2=new FactorNode("fB",2,funcFB);
		FactorNode f3=new FactorNode("fC",8, funcFC, nodeArrayFC);
		FactorNode f4=new FactorNode("fD",5, funcFD, nodeArrayFD);
		FactorNode f5=new FactorNode("fE",7, funcFE, nodeArrayFE);
		
		VariableNode v1=new VariableNode("x1",1, defWerte, false);
		VariableNode v2=new VariableNode("x2",3, defWerte, false);
		VariableNode v3=new VariableNode("x3",9, defWerte, false);	
		VariableNode v4=new VariableNode("x4",4, defWerte, true);
		VariableNode v5=new VariableNode("x5",6, defWerte, true);
		
		LinkedList<Message> mSeq= new LinkedList<Message>();
		
		Message message=new Message(f1, v1);
		mSeq.add(message);
		
		message=new Message(f2, v2);
		mSeq.add(message);
		
		message=new Message(v4, f4);
		mSeq.add(message);
		
		message=new Message(v5, f5);
		mSeq.add(message);
		
		message=new Message(v1, f3);
		mSeq.add(message);
		
		message=new Message(v2, f3);
		mSeq.add(message);
		
		message=new Message(f4, v3);
		mSeq.add(message);
		
		message=new Message(f5, v3);
		mSeq.add(message);
		
		message=new Message(f3, v3);
		mSeq.add(message);
		
		message=new Message(v3, f3);
		mSeq.add(message);
		
		message=new Message(f3, v1);
		mSeq.add(message);
		
		message=new Message(f3, v2);
		mSeq.add(message);
		
		message=new Message(v3, f4);
		mSeq.add(message);
		
		message=new Message(v3, f5);
		mSeq.add(message);
		
		message=new Message(v1, f1);
		mSeq.add(message);
		
		message=new Message(v2, f2);
		mSeq.add(message);
		
		message=new Message(f4, v4);
		mSeq.add(message);
		
		message=new Message(f5, v5);
		mSeq.add(message); */
		
		Object[] defWerte=new Object[2];
		defWerte[0]=0;
		defWerte[1]=1;
		
		
		Object[][] funcFA=new Object[2][2];
		funcFA[0][0]=0;
		funcFA[1][0]=0.4;
		funcFA[0][1]=1;
		funcFA[1][1]=0.6;
		
		Object[][] funcFB=new Object[2][2];
		funcFB[0][0]=0;
		funcFB[1][0]=0.7;
		funcFB[0][1]=1;
		funcFB[1][1]=0.3;
		
		int[] nodeArrayFC=new int[3];
		nodeArrayFC[0]=1;
		nodeArrayFC[1]=3;
		nodeArrayFC[2]=9;		
		Object[][] funcFC=new Object[4][8];
		funcFC[0][0]=0;
		funcFC[1][0]=0;
		funcFC[2][0]=0;
		funcFC[3][0]=0.1;
		funcFC[0][1]=1;
		funcFC[1][1]=0;
		funcFC[2][1]=0;
		funcFC[3][1]=0.2;
		funcFC[0][2]=0;
		funcFC[1][2]=1;
		funcFC[2][2]=0;
		funcFC[3][2]=0.05;
		funcFC[0][3]=0;
		funcFC[1][3]=0;
		funcFC[2][3]=1;
		funcFC[3][3]=0.15;
		funcFC[0][4]=1;
		funcFC[1][4]=1;
		funcFC[2][4]=0;
		funcFC[3][4]=0.2;
		funcFC[0][5]=1;
		funcFC[1][5]=0;
		funcFC[2][5]=1;
		funcFC[3][5]=0.1;
		funcFC[0][6]=0;
		funcFC[1][6]=1;
		funcFC[2][6]=1;
		funcFC[3][6]=0.15;
		funcFC[0][7]=1;
		funcFC[1][7]=1;
		funcFC[2][7]=1;
		funcFC[3][7]=0.05;
		
		int[] nodeArrayFD=new int[2];
		nodeArrayFD[0]=9;
		nodeArrayFD[1]=4;
		Object[][] funcFD=new Object[3][4];
		funcFD[0][0]=0;
		funcFD[1][0]=0;
		funcFD[2][0]=0.25;
		funcFD[0][1]=1;
		funcFD[1][1]=0;
		funcFD[2][1]=0.2;
		funcFD[0][2]=0;
		funcFD[1][2]=1;
		funcFD[2][2]=0.15;
		funcFD[0][3]=1;
		funcFD[1][3]=1;
		funcFD[2][3]=0.4;
		
		int[] nodeArrayFE=new int[2];
		nodeArrayFE[0]=9;
		nodeArrayFE[1]=6;
		Object[][] funcFE=new Object[3][4];
		funcFE[0][0]=0;
		funcFE[1][0]=0;
		funcFE[2][0]=0.3;
		funcFE[0][1]=1;
		funcFE[1][1]=0;
		funcFE[2][1]=0.15;
		funcFE[0][2]=0;
		funcFE[1][2]=1;
		funcFE[2][2]=0.2;
		funcFE[0][3]=1;
		funcFE[1][3]=1;
		funcFE[2][3]=0.35;
		
		FactorNode f1=new FactorNode("fA",0,funcFA);
		FactorNode f2=new FactorNode("fB",2,funcFB);
		FactorNode f3=new FactorNode("fC",8, funcFC, nodeArrayFC);
		FactorNode f4=new FactorNode("fD",5, funcFD, nodeArrayFD);
		FactorNode f5=new FactorNode("fE",7, funcFE, nodeArrayFE);
		
		VariableNode v1=new VariableNode("x1",1, defWerte, false);
		VariableNode v2=new VariableNode("x2",3, defWerte, false);
		VariableNode v3=new VariableNode("x3",9, defWerte, false);	
		VariableNode v4=new VariableNode("x4",4, defWerte, true);
		VariableNode v5=new VariableNode("x5",6, defWerte, true);
		
		LinkedList<Message> mSeq= new LinkedList<Message>();
		
		Message message=new Message(f1, v1);
		mSeq.add(message);
		
		message=new Message(f2, v2);
		mSeq.add(message);
		
		message=new Message(v4, f4);
		mSeq.add(message);
		
		message=new Message(v5, f5);
		mSeq.add(message);
		
		message=new Message(v1, f3);
		mSeq.add(message);
		
		message=new Message(v2, f3);
		mSeq.add(message);
		
		message=new Message(f4, v3);
		mSeq.add(message);
		
		message=new Message(f5, v3);
		mSeq.add(message);
		
		message=new Message(f3, v3);
		mSeq.add(message);
		
		message=new Message(v3, f3);
		mSeq.add(message);
		
		message=new Message(f3, v1);
		mSeq.add(message);
		
		message=new Message(f3, v2);
		mSeq.add(message);
		
		message=new Message(v3, f4);
		mSeq.add(message);
		
		message=new Message(v3, f5);
		mSeq.add(message);
		
		message=new Message(v1, f1);
		mSeq.add(message);
		
		message=new Message(v2, f2);
		mSeq.add(message);
		
		message=new Message(f4, v4);
		mSeq.add(message);
		
		message=new Message(f5, v5);
		mSeq.add(message); 
		
		
		Graph test= new Graph(mSeq);
		test.fillFinalHash();
	//	SumProduct sum=new SumProduct(test);
		/*sum.calcFactorToVariable(0);
		sum.calcFactorToVariable(1);
		sum.calcVariableToFactor(2);
		sum.calcVariableToFactor(3);
		sum.calcVariableToFactor(4);
		sum.calcVariableToFactor(5);
		sum.calcFactorToVariable(6);
		sum.calcFactorToVariable(7);
		sum.calcFactorToVariable(8);
		sum.calcVariableToFactor(9);*/
		
		
	//	sum.doSum();		
	//	test.print();	
		
		
	
		MaxProduct max= new MaxProduct(test, v1);
	/*	max.calcFactorToVariable(0);
		max.calcFactorToVariable(1);
		max.calcVariableToFactor(2);
		max.calcVariableToFactor(3);
		max.calcVariableToFactor(4);
		max.calcVariableToFactor(5);
		max.calcFactorToVariable(6);
		max.calcFactorToVariable(7);
		max.calcFactorToVariable(8);
		max.calcVariableToFactor(9);
		max.calcFactorToVariable(10);
		max.calcFactorToVariable(11);
		max.calcVariableToFactor(12);
		max.calcVariableToFactor(12);
		max.calcVariableToFactor(13);
		max.calcVariableToFactor(14);
		max.calcVariableToFactor(15);
		max.calcFactorToVariable(16);
		max.calcFactorToVariable(17); */
		max.doMax();
		System.out.println("");
		//test.print();	
		
		

	}

}
