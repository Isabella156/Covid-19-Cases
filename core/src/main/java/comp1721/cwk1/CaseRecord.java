package comp1721.cwk1;

import java.time.LocalDate;

public class CaseRecord {
    private LocalDate date;
    private int staffCases;
    private int studentCases;
    private int otherCases;
    // TODO: Write stub for constructor
    public CaseRecord (LocalDate d, int staff, int student, int other){
        if(staff < 0 || student < 0 || other < 0){
            throw new DatasetException("The number of cases can not be zero!");
        }else{
            this.date = d;
            this.staffCases = staff;
            this.studentCases = student;
            this.otherCases = other;
        }
    }

    // TODO: Write stubs for four getter methods

    public LocalDate getDate(){
        return this.date;
    }

    public int getStaffCases(){
        if(this.staffCases < 0){
            throw new DatasetException("The number of cases can not be less than zero!");
        }else{
            return this.staffCases;
        }
    }

    public int getStudentCases(){
        if(this.studentCases < 0){
            throw new DatasetException("The number of cases can not be less than zero!");
        }else{
            return this.studentCases;
        }
    }

    public int getOtherCases(){
        if(this.otherCases < 0){
            throw new DatasetException("The number of cases can not be less than zero!");
        }else{
            return this.otherCases;
        }
    }

    // TODO: Write stub for totalCases()
    public int totalCases(){
        return this.staffCases + this.studentCases + this.otherCases;
    }

    // TODO: Write stub for toString()
    public String toString(){
        String s1 = this.staffCases + "";
        String s2 = this.studentCases + "";
        String s3 = this.otherCases + "";
        String s4 = this.date + "";
        String str = s4 + ": " + s1 + " staff, " + s2 + " students, " + s3 + " other";
        return str;
    }
}
