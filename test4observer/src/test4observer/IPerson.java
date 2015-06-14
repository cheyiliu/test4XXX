package test4observer;

public interface IPerson {
	public void say(String content);

	public void hear(IPerson from, String content);
}
