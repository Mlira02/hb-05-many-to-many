package entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course
{

//    Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;


    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "course_student",
               joinColumns = @JoinColumn(name = "course_id"),
               inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;



//    Constructors
    public Course()
    {

    }

    public Course(String title)
    {
        this.title = title;
    }

//    Methods
    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public Instructor getInstructor()
    {
        return instructor;
    }

    public List<Review> getReviews()
    {
        return reviews;
    }

    public List<Student> getStudents()
    {
        return students;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    public void setReviews(List<Review> reviews)
    {
        this.reviews = reviews;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }

    public void addReview(Review theReview)
    {
        if(reviews == null)
        {
            reviews = new ArrayList<>();
        }
        reviews.add(theReview);
    }

    public void addStudents(Student theStudent)
    {
        if(students == null)
        {
            students = new ArrayList<>();
        }
        students.add(theStudent);
    }

//    Overrides
    @Override
    public String toString()
    {
        return "Course{" + "id=" + id + ", title='" + title + '\'' + '}';
    }
}
