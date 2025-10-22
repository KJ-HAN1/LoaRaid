package com.loaraid.raidscheduler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "raids")
public class RaidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raid_id")
    private Long raidId;

    @Column(name = "raid_name")
    private String raidName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "difficulty_code", nullable = false)
    private ComonCodesEntity difficultyCode;

    @Column(name = "required_player_count")
    private Integer requiredPlayerCount;

    @Column(name = "gold_reward")
    private Integer goldReward;

    @OneToMany(mappedBy = "raid", fetch = FetchType.LAZY)
    private List<RaidApplicationsEntity> applications = new ArrayList<>();

    @OneToMany(mappedBy = "raid", fetch = FetchType.LAZY)
    private List<PartyEntity> parties = new ArrayList<>();
}
