public class SpecialCell {
    private SpecialCell parent;
    private boolean explored;
    private int start;
    private int end;

    public SpecialCell(int start, int end) {
        this.start = start;
        this.end = end;
        this.explored = false;
        this.parent = null;
    }

    /** Getters and Setters **/
    public boolean isExplored() {
        return this.explored;
    }

    public SpecialCell getParent() {
        return this.parent;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public void setParent(SpecialCell parent) {
        this.parent = parent;
    }

}
