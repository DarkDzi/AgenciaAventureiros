package TP1.example.Aventura.Utils;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class  FormatarTimeStamp {


    public static String TimeStampParaString(Timestamp timestamp) {
        if (timestamp == null) {
            return "Não Informado";
        }
        else {
            return timestamp
                    .toLocalDateTime()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        }
    }

}
