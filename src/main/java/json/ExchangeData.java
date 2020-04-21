package json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExchangeData {
    @SerializedName("Date")
    private String date;
    @SerializedName("Timestamp")
    private String timestamp;
    @SerializedName("Valute")
    private Valute valute;

    public Date getDate() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date.substring(0, 10));
    }

    public Date getTimestamp() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(timestamp.substring(0, 10));
    }

    public Valute getValute() {
        return valute;
    }
}