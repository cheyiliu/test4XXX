package test4observer;

import java.util.ArrayList;
import java.util.List;

public class ContextNoisy implements IContext {
	private List<IPerson> mPersons = new ArrayList<IPerson>();

	@Override
	public void deliverInfo(IPerson fromWho, String what) {
		// too noisy, do nothing
	}

	@Override
	public void register(IPerson person) {
		if (!mPersons.contains(person)) {
			mPersons.add(person);
		}
	}

	@Override
	public void unRegister(IPerson person) {
		mPersons.remove(person);
	}

}
