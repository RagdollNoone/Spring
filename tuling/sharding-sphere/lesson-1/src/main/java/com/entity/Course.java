package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;

public class Course {
    private Long id;

    @TableField("course_name")
    private String courseName;

    @TableField("user_id")
    private Long userId;

    @TableField("course_status")
    private String courseStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + id +
                ", courseName='" + courseName + '\'' +
                ", userId=" + userId +
                ", courseStatus='" + courseStatus + '\'' +
                '}';
    }
}
