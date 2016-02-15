package development_tools;

import java.util.Scanner;

public class LevelCreater {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.print("What scale are you using? 1:");
		double scale = s.nextDouble();
		
		System.out.print("How many quads? ");
		int numQ = s.nextInt();
		System.out.println();

		FourNums[] quads = new FourNums[numQ];

		for (int i = 0; i < numQ; i++) {
			double[] nums = new double[4];
			for (int j = 0; j < 4; j++) {
				System.out.print(j + ": ");
				nums[j] = s.nextDouble() * scale;
			}
			quads[i] = new FourNums(nums);
			System.out.println();
		}

		for (FourNums a : quads) {
			System.out.println(a);
		}
		
		s.close();
	}

}

class FourNums {

	public double x1;
	public double x2;
	public double y1;
	public double y2;

	FourNums(double[] nums) {
		x1 = nums[0];
		y1 = nums[1];
		x2 = nums[2];
		y2 = nums[3];
	}

	public String toString() {
		//@formatter:off
		return "\n" + 
				"		<quad>\n" + 
				"			<x1>"+x1+"</x1>\n" + 
				"			<y1>"+y1+"</y1>\n" + 
				"			<x2>"+x2+"</x2>\n" + 
				"			<y2>"+y2+"</y2>\n" + 
				"		</quad>";
		//@formatter:on
	}

}