package edu.northwestern.ece.lockserver;

import java.util.Date;

/**
 * Trivial class that represents a file lock
 */
public final class FileLock implements Comparable<FileLock> {
    private int lockID;

    private long offset;

    private long length;

    private long timeCreated;

    public FileLock(int id, long off, long len) {
        lockID = id;
        offset = off;
        length = len;
        timeCreated = new Date().getTime();
    }

    public FileLock(long off, long len) {
        this(-1, off, len);
    }

    public int compareTo(FileLock r) {
        FileLock l = this;
        long llb = l.offset;
        long lub = l.offset + l.length - 1;
        long rlb = r.offset;
        long rub = r.offset + r.length - 1;

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

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof FileLock && this.compareTo((FileLock) o) == 0);
    }

    @Override
    public int hashCode() {
        int result = (int) (offset ^ (offset >>> 32));
        result = 31 * result + (int) (length ^ (length >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "FileLock{" +
                "lockID=" + lockID +
                ", offset=" + offset +
                ", length=" + length +
                ", timeCreated=" + timeCreated +
                '}';
    }

    public long getOffset() {
        return offset;
    }

    public long getLength() {
        return length;
    }

    public int getLockID() {
        return lockID;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

}