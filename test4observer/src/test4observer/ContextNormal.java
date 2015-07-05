package test4observer;

import java.util.ArrayList;
import java.util.List;

public class ContextNormal implements IContext {
	private List<IPerson> mPersons = new ArrayList<IPerson>();

	@Override
	public void deliverInfo(IPerson fromWho, String what) {
		for (int i = 0; i < mPersons.size(); i++) {
			IPerson iPerson = mPersons.get(i);
			System.out.println("deliverInfo, from " + fromWho.getName()
					+ ", to " + iPerson.getName() + " " + what);
			iPerson.hear(fromWho, what);
		}
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
