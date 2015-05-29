package biz;

import android.os.Bundle;

public abstract class iCmd {
	private boolean mCanceled = false;
	private Object mTag;
	protected static int counter = 0;
	public int no = 0;

	public void cancel() {
		mCanceled = true;
	}

	public boolean isCanceled() {
		return mCanceled;
	}

	public Object getTag() {
		return mTag;
	}

	public iCmd setTag(Object tag) {
		mTag = tag;
		return this;
	}

	public abstract Bundle exe() throws Exception;
}
