package test4observer;

public interface IContext {
	public void deliverInfo();

	public void personEnter(IPerson person);

	public void personLeave(IPerson person);
}
