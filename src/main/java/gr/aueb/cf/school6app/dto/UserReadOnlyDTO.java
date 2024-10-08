package gr.aueb.cf.school6app.dto;



public class UserReadOnlyDTO extends BaseUserDTO {

    private Integer id;

    public UserReadOnlyDTO() {}

    public UserReadOnlyDTO(Integer id, String username, String password) {
        super(username, password);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}