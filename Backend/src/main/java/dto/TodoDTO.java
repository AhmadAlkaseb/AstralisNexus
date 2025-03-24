package dto;

import lombok.*;
import persistence.model.Account;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    private Integer id;
    private String date;
    private String description;
    private boolean status;
    private String done_by;
    private Account account;
}
