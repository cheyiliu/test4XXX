
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClassA().doTest(100);
	}
	
	public static class ClassA{
		public void doTest(final int paramIntVal) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(paramIntVal);
				}
			}).start();
		}
	}

}
