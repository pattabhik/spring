package com.pattabhi.library.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "member") // Maps to the "member" table
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing primary key
    private Long id;

    @Column(nullable = false, length = 255) // Maps to name column with NOT NULL constraint
    private String name;

    @Column(name = "member_id", unique = true, nullable = false, length = 20) // Unique and NOT NULL
    private String memberId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    // Optional: toString, equals, and hashCode methods
    @Override
    public String toString() {
        return "MemberEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}