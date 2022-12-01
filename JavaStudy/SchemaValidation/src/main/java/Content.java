import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.lucas.schema
 * fileName       : Content
 * author         : lucas
 * date           : 2022-11-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-18        lucas       최초 생성
 */
@Getter
@Setter
@AllArgsConstructor
public class Content {
    private String id;
    private String title;
    private String type;
}
