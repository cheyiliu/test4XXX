package a.b;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassA cA = new ClassA();
		ClassA cB = new ClassA(cA);
		cA.i = 1222;
		System.out.println(cB.object.i);
	}

	public static class ClassA {
		public ClassA object;
		public int i = 100;

		public ClassA() {
		}

		public ClassA(ClassA object) {
			this.object = object;
		}
	}
}
