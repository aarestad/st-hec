package edu.northwestern.ece.lockserver.util;

import edu.northwestern.ece.lockserver.FileLock;
import jdsl.core.api.Comparator;
import jdsl.core.api.Locator;
import jdsl.core.ref.RedBlackTree;
import net.datastructures.Entry;
import net.datastructures.RBTree;

import java.util.*;

public final class RBTreeTester {
    public static void main(String[] args) {
        Comparator fileLockComparator = new FileLockOverlapComparator();

//        edu.northwestern.ece.lockserver.util.RedBlackTree templeTree = new edu.northwestern.ece.lockserver.util.RedBlackTree();
        RBTree netDatastructuresTree = new RBTree();
        jdsl.core.ref.RedBlackTree jdslTree = new RedBlackTree(fileLockComparator);

        // Insert and remove 10000 random locks (maybe up this if we're
        // ambitious :)
        List<FileLock> insertList = new ArrayList<>();
        long off = 0;
        Random r = new Random();
        for (int i = 0; i < 10000; ++i) {
            long len = Math.abs(r.nextInt() % 100000);
            insertList.add(new FileLock(off, len));
            off += len + Math.abs(r.nextInt() % 1000);
        }
        List<FileLock> deleteList = new ArrayList<>(insertList);
        Collections.shuffle(insertList);
        Collections.shuffle(deleteList);

        Iterator lockIter;
        long timeBegin;

        // Now give each tree a workout
//		lockIter = insertList.iterator();
//		timeBegin = System.currentTimeMillis();

//		while (lockIter.hasNext()) {
//			templeTree.add(lockIter.next());
//		}
//
//		long templeTreeAddTime = System.currentTimeMillis() - timeBegin;
//
//		lockIter = deleteList.iterator();
//		timeBegin = System.currentTimeMillis();
//
//		while (lockIter.hasNext()) {
//			templeTree.delete(lockIter.next());
//		}
//
//		long templeTreeDeleteTime = System.currentTimeMillis() - timeBegin;

        lockIter = insertList.iterator();
        Set<Entry> entrySet = new HashSet<>();
        timeBegin = System.currentTimeMillis();

        while (lockIter.hasNext()) {
            Object nextLock = lockIter.next();
            entrySet.add(netDatastructuresTree.insert(nextLock, nextLock));
        }

        long netDatastructuresTreeAddTime = System.currentTimeMillis() - timeBegin;

        Iterator entryIter = entrySet.iterator();
        timeBegin = System.currentTimeMillis();

        while (entryIter.hasNext()) {
            netDatastructuresTree.remove((Entry) entryIter.next());
        }

        long netDatastructuresTreeDeleteTime = System.currentTimeMillis() - timeBegin;

        lockIter = insertList.iterator();
        Set<Locator> locatorSet = new HashSet<>();
        timeBegin = System.currentTimeMillis();

        while (lockIter.hasNext()) {
            Object nextLock = lockIter.next();
            locatorSet.add(jdslTree.insert(nextLock, nextLock));
        }

        long jdslTreeAddTime = System.currentTimeMillis() - timeBegin;

        Iterator locatorIter = locatorSet.iterator();
        timeBegin = System.currentTimeMillis();

        while (locatorIter.hasNext()) {
            jdslTree.remove((Locator) locatorIter.next());
        }

        long jdslTreeDeleteTime = System.currentTimeMillis() - timeBegin;

//        System.out.println("Temple tree: add time = " + templeTreeAddTime + "ms; delete time = " + templeTreeDeleteTime + " ms");

        System.out.println("net.datastructures tree: add time = " + netDatastructuresTreeAddTime + "ms; delete time = " + netDatastructuresTreeDeleteTime + " ms");

        System.out.println("JDSL tree: add time = " + jdslTreeAddTime + "ms; delete time = " + jdslTreeDeleteTime + " ms");
    }
}
