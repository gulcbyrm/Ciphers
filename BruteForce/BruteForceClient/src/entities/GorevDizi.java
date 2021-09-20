package entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class GorevDizi implements Serializable{

    public static final long serialVersionUID = 0L;

    @Getter @Setter private int baslangic;
    @Getter @Setter private int bitis;

    @Override
    public String toString() {
        return "baslangic=" + baslangic + ", bitis=" + bitis;
    }

}
