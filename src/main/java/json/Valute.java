package json;

import com.google.gson.annotations.SerializedName;

public class Valute {
    @SerializedName("USD")
    private ValuteData usd;
    @SerializedName("EUR")
    private ValuteData eur;

    public ValuteData getUsd() {
        return usd;
    }

    public ValuteData getEur() {
        return eur;
    }
}
