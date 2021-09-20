package entities;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ErrCheck<T> {

    private T field;
    private String description;
    
}
