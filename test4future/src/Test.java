import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Test {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
//		Task task = new Task();
//		Future<Integer> result = executor.submit(task);
//
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//
//		System.out.println("主线程在执行任务");
//
//		try {
//			System.out.println("task运行结果" + result.get());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("所有任务执行完毕");

		Task task2 = new Task();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task2) {

			@Override
			protected void done() {
				// TODO Auto-generated method stub
				super.done();
				System.out.println("done," + Thread.currentThread().getId());
				try {
					System.out.println("result," + get());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
		executor.submit(futureTask);
		executor.shutdown();
		System.out.println("main done," + Thread.currentThread().getId());
		
		{
			FutureTask<Void> futureTask2 = new FutureTask<Void>(new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					// TODO Auto-generated method stub
					return null;
				}
			});
			
			FutureTask<Integer> futureTask3 = new FutureTask<Integer>(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			}, 1);
		}
	}
}

class Task implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		
		Thread.sleep(3000);
		int sum = 0;
		for (int i = 0; i < 100; i++)
			sum += i;
		System.out.println("call," + Thread.currentThread().getId());
		return sum;
	}
}