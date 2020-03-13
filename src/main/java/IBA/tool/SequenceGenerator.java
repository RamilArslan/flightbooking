package IBA.tool;

import java.util.concurrent.atomic.AtomicLong;

import static IBA.tool.Constants.LAST_SEQUENCE_ID;

public class SequenceGenerator {
    private static AtomicLong sequence = new AtomicLong(LAST_SEQUENCE_ID);


    public static long getNext(){
        return sequence.getAndIncrement();
    };
}
