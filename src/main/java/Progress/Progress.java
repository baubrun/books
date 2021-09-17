package Progress;

public class Progress {
    private final int completed;
    private final int remaining;
    private final int inProgress;

    public Progress(int completed, int remaining, int inProgress) {
        this.completed = completed;
        this.remaining = remaining;
        this.inProgress = inProgress;
    }

    public int completed() {
        return this.completed;
    }

    public int remaining() {
        return this.remaining;
    }

    public int inProgress() {
        return this.inProgress;
    }
}
