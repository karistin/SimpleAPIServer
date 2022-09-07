package agent.bridge.datastore;

/**
 * packageName    : agent.bridge.datastore
 * fileName       : RecordSql
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public enum RecordSql {
    obfuscated, raw, off;

    public static RecordSql get(String value) {
        if (value == null) {
            return obfuscated;
        }
        return Enum.valueOf(RecordSql.class, value);
    }
}
