package pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Column {

    private String title;
    private Integer  sort_order;
    private Integer type;

}
