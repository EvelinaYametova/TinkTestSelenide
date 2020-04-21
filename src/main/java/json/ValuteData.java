package json;

import com.google.gson.annotations.SerializedName;

public class ValuteData {
    @SerializedName("Value")
    private Double value;

    public Double getValue() {
        return value;
    }
}