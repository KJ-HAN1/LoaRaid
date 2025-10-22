package com.loaraid.raidscheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="characters")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long characterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_code", nullable = false)
    private ComonCodesEntity classCode;

    @Column(name = "item_level")
    private double itemLevel;

    @Column(name = "is_main")
    private Boolean isMain;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "character",fetch = FetchType.LAZY)
    private List<RaidApplicationsEntity> raidApplications = new ArrayList<>();

    @OneToMany(mappedBy = "character",fetch = FetchType.LAZY)
    private List<PartyMembersEntity> partyMembers = new ArrayList<>();

}
