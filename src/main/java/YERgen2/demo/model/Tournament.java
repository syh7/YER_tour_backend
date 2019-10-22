package YERgen2.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String referee;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate enrolDate;
    private int[] maxDisciplines;
    private int[] categories;
    @ManyToOne
    private Admin admin;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setReferee(String referee) {
        this.referee = referee;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setEnrolDate(LocalDate enrolDate) {
        this.enrolDate = enrolDate;
    }
    public void setMaxDisciplines(int[] maxDisciplines) {
        this.maxDisciplines = maxDisciplines;
    }
    public void setCategories(int[] categories) {
        this.categories = categories;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getReferee() {
        return referee;
    }
    public String getLocation() {
        return location;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public LocalDate getEnrolDate() {
        return enrolDate;
    }
    public int[] getMaxDisciplines() {
        return maxDisciplines;
    }
    public int[] getCategories() {
        return categories;
    }
    public Admin getAdmin() {
        return admin;
    }

}
