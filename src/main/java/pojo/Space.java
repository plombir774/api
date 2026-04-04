package pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Space {

    private String title;
    private Integer external_id;
    private String parent_entity_uid;
    private String for_everyone_access_role_id;

}