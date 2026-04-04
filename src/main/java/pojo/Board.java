package pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class Board {
    private String title;
    private String description;
}
