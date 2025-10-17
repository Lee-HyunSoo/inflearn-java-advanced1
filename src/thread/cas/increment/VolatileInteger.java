package thread.cas.increment;

public class VolatileInteger implements IncrementInteger{

    volatile private int value;
    volatile private boolean flag;

    public void setFlag() {
        flag = false;
    }

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
