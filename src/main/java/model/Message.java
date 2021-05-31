package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private int messageId;
    private String message;
    private String date;
    private int user_id;
    private String fullNAme;
}
