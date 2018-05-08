package model.entity;

import java.sql.Date;

public class Demand
{
    private int id;
    private User user;
    private Kebab kebab;
    private Date date;
    // 0 waiting, 1 being prepared, 2 ready, 3 being delivered, 4 completed
    private int status;

    public static final int WAITING = 0;
    public static final int PREPARING = 1;
    public static final int READY = 2;
    public static final int DELIVERING = 3;
    public static final int COMPLETED = 4;

    public Demand(){}

    public Demand(int id, User user, Kebab kebab, Date date, int status) {
        this.id = id;
        this.user = user;
        this.kebab = kebab;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Kebab getKebab() {
        return kebab;
    }

    public void setKebab(Kebab kebab) {
        this.kebab = kebab;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("N°");
        sb.append(id);
        sb.append(" - ");
        sb.append(date);
        sb.append(" - ");
        switch(status)
        {
            case 0:
                    sb.append("waiting");
                    break;
            case 1:
                    sb.append("preparing");
                    break;
            case 2:
                    sb.append("ready");
                    break;
            case 3:
                    sb.append("delivering");
                    break;
            case 4:
                    sb.append("completed");
                    break;
            default:
                    break;
        }
        return sb.toString();
    }
}
