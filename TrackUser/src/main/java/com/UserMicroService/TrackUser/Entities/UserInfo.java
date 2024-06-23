package com.UserMicroService.TrackUser.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    String email;

    @Column(name = "time_studied")
    Long time_studied = 0L;
}
