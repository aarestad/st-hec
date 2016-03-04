/*
 * Created on Sep 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package edu.northwestern.ece.lockserver.util;

import jdsl.core.api.Comparator;
import edu.northwestern.ece.lockserver.FileLock;

public final class FileLockOverlapComparator implements Comparator {

	public int compare(Object a, Object b) {
		return ((FileLock) a).compareTo((FileLock) b);
	}

	public boolean isGreaterThan(Object a, Object b) {
		return ((FileLock) a).compareTo((FileLock) b) > 0;
	}

	public boolean isLessThan(Object a, Object b)  {
		return ((FileLock) a).compareTo((FileLock) b) < 0;
	}

	public boolean isGreaterThanOrEqualTo(Object a, Object b) {
		return ((FileLock) a).compareTo((FileLock) b) >= 0;
	}

	public boolean isLessThanOrEqualTo(Object a, Object b)  {
		return ((FileLock) a).compareTo((FileLock) b) <= 0;
	}

	public boolean isComparable(Object o) {
		return o instanceof FileLock;
	}

	public boolean isEqualTo(Object a, Object b) {
		return a.equals(b);
	}
}
