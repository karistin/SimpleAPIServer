package Com.Agent;

/**
 * packageName    : Com.Agent
 * fileName       : CostAccounter
 * author         : lucas
 * date           : 2022-08-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-25        lucas       최초 생성
 */
public class CostAccounter {
    private static final ThreadLocal<Long> allocationCost =
            new ThreadLocal<Long>(){
                @Override
                protected Long initialValue() {
                    return 0L;
                }
            };

    public static void recordAllocation(final String typeName) {
        checkAllocationCost(1);
    }

    public static void recordArrayAllocation(final int length, final int multiplier) {
        checkAllocationCost((long) length * multiplier);
    }

    public static void checkAllocationCost(final long additional) {
        final long newValue = additional + allocationCost.get();
        allocationCost.set(newValue);
    }
    public static long getAllocationCost(){
        return allocationCost.get();
    }
    public static void resetCounter(){
        allocationCost.set(0L);
    }
}
