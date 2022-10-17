package lucas.base.util;

import java.util.Random;

/**
 * packageName    : lucas.base.util
 * fileName       : KeyGen
 * author         : lucas
 * date           : 2022-10-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-17        lucas       최초 생성
 */
public final class KeyGen {
    private static Random rand = new Random(System.currentTimeMillis());

    public static void setSeed(long seed){
        rand.setSeed(seed);
    }
    public static long next(){
        return rand.nextLong();
    }
}
