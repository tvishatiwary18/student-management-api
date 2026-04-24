package com.tvisha.studentapp.dto;
public class StudentResponseDTO{
private Integer id;
private String name;
private Integer age;
public void setName(String name) {
       this.name = name;
}
public String getName()
{
    return name;
}
    public void setAge(Integer age) {
        this.age = age;
    }
public Integer getAge()
{
    return age;
}
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
public void getName(String name)
{
    this.name=name;
}
public void getAge(Integer age)
{
    this.age=age;
}
}