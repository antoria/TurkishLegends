package model.entity;

public class Staff extends User
{
    private int salary;

    public Staff(){super();}

    public Staff(int id, String firstName, String lastName, String email, String password, int salary)
    {
        super(id, firstName, lastName, email, password);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
