package gr.aueb.cf.school6app.dto;

public class InsertUserDTO extends BaseUserDTO {

    public InsertUserDTO() {}

    public InsertUserDTO(String username, String password, String confirmedPassword) {
        super(username, password, confirmedPassword);
    }
}