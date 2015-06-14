package test4observer;

public interface IContext {
	public void deliverInfo(IPerson fromWho, String what);

	public void register(IPerson person);

	public void unRegister(IPerson person);
}
