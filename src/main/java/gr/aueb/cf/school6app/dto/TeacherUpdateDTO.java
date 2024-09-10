package gr.aueb.cf.school6app.dto;

public class TeacherUpdateDTO extends BaseDTO {
    private Integer id;

    public TeacherUpdateDTO(){}



    public TeacherUpdateDTO( Integer id, String firstname, String lastname) {
        super(firstname, lastname);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
