package de.lmu.genzentrum.tresch;

import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws NoValueException {
		double[] defWerte = new double[2];
		defWerte[0] = 0;
		defWerte[1] = 1;

		double[][] funcFA = new double[2][2];
		funcFA[0][0] = 0;
		funcFA[1][0] = 0.4;
		funcFA[0][1] = 1;
		funcFA[1][1] = 0.6;

		double[][] funcFB = new double[2][2];
		funcFB[0][0] = 0;
		funcFB[1][0] = 0.7;
		funcFB[0][1] = 1;
		funcFB[1][1] = 0.3;

		String[] nodeArrayFC = new String[3];
		nodeArrayFC[0] = "x1";
		nodeArrayFC[1] = "x2";
		nodeArrayFC[2] = "x3";
		double[][] funcFC = new double[4][8];
		funcFC[0][0] = 0;
		funcFC[1][0] = 0;
		funcFC[2][0] = 0;
		funcFC[3][0] = 0.1;
		funcFC[0][1] = 1;
		funcFC[1][1] = 0;
		funcFC[2][1] = 0;
		funcFC[3][1] = 0.2;
		funcFC[0][2] = 0;
		funcFC[1][2] = 1;
		funcFC[2][2] = 0;
		funcFC[3][2] = 0.05;
		funcFC[0][3] = 0;
		funcFC[1][3] = 0;
		funcFC[2][3] = 1;
		funcFC[3][3] = 0.15;
		funcFC[0][4] = 1;
		funcFC[1][4] = 1;
		funcFC[2][4] = 0;
		funcFC[3][4] = 0.2;
		funcFC[0][5] = 1;
		funcFC[1][5] = 0;
		funcFC[2][5] = 1;
		funcFC[3][5] = 0.1;
		funcFC[0][6] = 0;
		funcFC[1][6] = 1;
		funcFC[2][6] = 1;
		funcFC[3][6] = 0.15;
		funcFC[0][7] = 1;
		funcFC[1][7] = 1;
		funcFC[2][7] = 1;
		funcFC[3][7] = 0.05;

		String[] nodeArrayFD = new String[2];
		nodeArrayFD[0] = "x3";
		nodeArrayFD[1] = "x4";
		double[][] funcFD = new double[3][4];
		funcFD[0][0] = 0;
		funcFD[1][0] = 0;
		funcFD[2][0] = 0.25;
		funcFD[0][1] = 1;
		funcFD[1][1] = 0;
		funcFD[2][1] = 0.2;
		funcFD[0][2] = 0;
		funcFD[1][2] = 1;
		funcFD[2][2] = 0.15;
		funcFD[0][3] = 1;
		funcFD[1][3] = 1;
		funcFD[2][3] = 0.4;

		String[] nodeArrayFE = new String[2];
		nodeArrayFE[0] = "x3";
		nodeArrayFE[1] = "x5";
		double[][] funcFE = new double[3][4];
		funcFE[0][0] = 0;
		funcFE[1][0] = 0;
		funcFE[2][0] = 0.3;
		funcFE[0][1] = 1;
		funcFE[1][1] = 0;
		funcFE[2][1] = 0.15;
		funcFE[0][2] = 0;
		funcFE[1][2] = 1;
		funcFE[2][2] = 0.2;
		funcFE[0][3] = 1;
		funcFE[1][3] = 1;
		funcFE[2][3] = 0.35;

		FactorNode f1 = new FactorNode("fA", 0, funcFA);
		FactorNode f2 = new FactorNode("fB", 2, funcFB);
		FactorNode f3 = new FactorNode("fC", 8, funcFC, nodeArrayFC);
		FactorNode f4 = new FactorNode("fD", 5, funcFD, nodeArrayFD);
		FactorNode f5 = new FactorNode("fE", 7, funcFE, nodeArrayFE);

		VariableNode v1 = new VariableNode("x1", 1, defWerte);
		VariableNode v2 = new VariableNode("x2", 3, defWerte);
		VariableNode v3 = new VariableNode("x3", 9, defWerte);
		VariableNode v4 = new VariableNode("x4", 4, defWerte);
		VariableNode v5 = new VariableNode("x5", 6, defWerte);

		LinkedList<Message> mSeq = new LinkedList<Message>();

		Message message = new Message(f1, v1);
		mSeq.add(message);

		message = new Message(f2, v2);
		mSeq.add(message);

		message = new Message(v4, f4);
		mSeq.add(message);

		message = new Message(v5, f5);
		mSeq.add(message);

		message = new Message(v1, f3);
		mSeq.add(message);

		message = new Message(v2, f3);
		mSeq.add(message);

		message = new Message(f4, v3);
		mSeq.add(message);

		message = new Message(f5, v3);
		mSeq.add(message);

		message = new Message(f3, v3);
		mSeq.add(message);

		message = new Message(v3, f3);
		mSeq.add(message);

		message = new Message(f3, v1);
		mSeq.add(message);

		message = new Message(f3, v2);
		mSeq.add(message);

		message = new Message(v3, f4);
		mSeq.add(message);

		message = new Message(v3, f5);
		mSeq.add(message);

		message = new Message(v1, f1);
		mSeq.add(message);

		message = new Message(v2, f2);
		mSeq.add(message);

		message = new Message(f4, v4);
		mSeq.add(message);

		message = new Message(f5, v5);
		mSeq.add(message);

		Graph test = new Graph(mSeq);
		SumProduct sum = new SumProduct();
		sum.doSum(test);
		test.print();

	}

}
