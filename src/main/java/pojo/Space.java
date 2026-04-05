package pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Space {

    private String title;
    private Integer external_id;


}