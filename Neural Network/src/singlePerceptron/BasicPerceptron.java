package singlePerceptron;

import java.util.Random;

/**
 * @author Chang-Hyun
 *
 */

//http://natureofcode.com/book/chapter-10-neural-networks/

public class BasicPerceptron implements Perceptron{
	double c = 0.01;
	private double[] weights;
	
	public BasicPerceptron(int n) {
		weights=new double[n+1]; //1 more for bias (constant)
		for (int i = 0; i < weights.length; i++) {//The weights are picked randomly to start.
			weights[i] = random(-1,1);
		}
	}
	
	public int feedforward(double[] inputs) {
		double sum = 0;
		int wl=weights.length;
	    for (int i = 0; i < wl-1; i++) {
	      sum += inputs[i]*weights[i];
	    }
	    sum += weights[wl-1];
	    //Result is the sign of the sum, -1 or +1. Here the perceptron is making a guess. Is it on one side of the line or the other?
	    return activate(sum);
	  }
	
	public void train(double[] inputs, int desired) {
		int guess = feedforward(inputs);
		double error = desired - guess;
		int wl=weights.length;
		for (int i = 0; i < wl-1; i++) {
			weights[i] += c * error * inputs[i];
		}
		weights[wl-1] += c * error; //adjust bias
	}
	
	private int activate(double sum) {
		//Return a 1 if positive, -1 if negative.
		  if (sum > 0) return 1;
		  else return -1;
		}

	private double random(double rangeMin, double rangeMax) {
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		return randomValue;
	}
	
	public double[] getWeights() {
		return weights;
	}
	
}
