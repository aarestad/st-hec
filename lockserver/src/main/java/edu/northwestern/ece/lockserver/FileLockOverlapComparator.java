/*
 * Created on Jun 16, 2005
 */
package edu.northwestern.ece.lockserver;

import java.util.Comparator;

public final class FileLockOverlapComparator implements Comparator<FileLock> {
	@Override
	public int compare(FileLock left, FileLock right) {
		long llb = left.getOffset();
		long lub = left.getOffset() + left.getLength() - 1;
		long rlb = right.getOffset();
		long rub = right.getOffset() + right.getLength() - 1;
		
		if (lub < rlb) {
            //System.out.println(l + " < " + r);
            return -1;
		}

		if (rub < llb) {
            //System.out.println(r + " < " + l);
            return 1;
		}

		//System.out.println(l + " == (overlaps) " + r);
		return 0;    
	}
}
