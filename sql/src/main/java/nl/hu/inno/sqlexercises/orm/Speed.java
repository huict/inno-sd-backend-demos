package nl.hu.inno.sqlexercises.orm;
import jakarta.persistence.Embeddable;

public class Speed {
    private int kmH;

    protected Speed(){}
    public Speed(int kmH) {
        this.kmH = kmH;
    }

    public int getKmH() {
        return kmH;
    }

    public void setKmH(int newValue){
        this.kmH = newValue;
    }
}
